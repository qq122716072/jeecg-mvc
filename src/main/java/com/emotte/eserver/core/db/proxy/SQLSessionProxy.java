package com.emotte.eserver.core.db.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.ConnectionManager;
import com.emotte.eserver.core.db.ResultHandler;
import com.emotte.eserver.core.db.SQLBuildScript_foreach;
import com.emotte.eserver.core.db.SQLBuildScript_if;
import com.emotte.eserver.core.db.SqlBuildHandler;
import com.emotte.eserver.core.db.annotation.Params;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.annotation.SelectKey;
import com.emotte.eserver.core.helper.LogHelper;

public class SQLSessionProxy implements InvocationHandler {
	@SuppressWarnings("rawtypes")
	private Class type;
	
	@SuppressWarnings("rawtypes")
	private SQLSessionProxy(Class type) {
		this.type = type;
	}
	@SuppressWarnings("rawtypes")
	public static Object getInstance (Class obj) {
		return Proxy.newProxyInstance(obj.getClassLoader(),new Class[]{obj}, new SQLSessionProxy(obj));
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		SQL sqlAnno = method.getAnnotation(SQL.class);
		// 解析方法参数注解（包装成Map）
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < parameterAnnotations.length; i++) {
			Annotation[] annotations = parameterAnnotations[i];
			if (annotations.length == 0) {
				params.add(args[i]);
				continue; 
			}
			Params paramAnno = (Params) annotations[0];
			map.put(paramAnno.value(), args[i]);
		}
		String sql = sqlAnno.sql();
		if (map.size() > 0) {
			params.add(map);
			args = params.toArray(new Object[params.size()]);
		}
		SQLTYPE type = sqlAnno.type();
		Class returnType = method.getReturnType();
		sql = SQLBuildScript_foreach.dealForeachScript(sql, args); // 处理foreach脚本
		sql = SQLBuildScript_if.dealIfScript(sql, args); // 处理if脚本
		Connection conn = ConnectionManager.getThreadLocalConnection(); // 从本地线程获得Connection
		switch (type) {
		case SELECT: {
			sql = SqlBuildHandler.rebuild(sql, args);
			LogHelper.info(getClass(), this.type.getSimpleName() + "." + method.getName() + "()[SQL]:" + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List list = ResultHandler.build(rs, sqlAnno.returnParam());
			if (returnType == List.class) {
				return list;
			} else {
				if (list.size() == 0) {
					return null;
				}
				return list.get(0);
			}
		}
		case INSERT: {
			Object id = null;
			Statement stat = conn.createStatement();
			SelectKey selectKey = sqlAnno.selectKey();
			if (!"".equals(selectKey.sql())) {
				String keySql = selectKey.sql();
				String prop = selectKey.prop();
				Object retype = selectKey.returnType();
				ResultSet rs = stat.executeQuery(keySql);
				while (rs.next() ) {
					if (retype == Long.class) {
						id = rs.getLong(1);
					} else if (retype == Integer.class) {
						id = rs.getInt(1);
					} else {
						id = rs.getString(1);
					}
					sql = SqlBuildHandler.replace(sql, "#{" + prop +"}", id);
				}
			}
			sql = SqlBuildHandler.rebuild(sql, args);
			LogHelper.info(getClass(), this.type.getSimpleName() + "." + method.getName() + "()[SQL]:" + sql);
			int i = stat.executeUpdate(sql);
			return id == null ? (i > 0) : (i > 0 ? id : null);
		}
		case UPDATE:
		case DELETE:
		default:{
			sql = SqlBuildHandler.rebuild(sql, args);
			LogHelper.info(getClass(), this.type.getSimpleName() + "." + method.getName() + "()[SQL]:" + sql);
			Statement ps = conn.createStatement();
			if (sql.contains(";")) {
				for (String str : sql.split(";")) {
					ps.addBatch(str);
				}
				int[] is = ps.executeBatch();
				int c = 0;
				for (int i : is) {
					c += i;
				}
				return c;
			} else {
				return ps.executeUpdate(sql);
			}
		}
		}
	}
	
}

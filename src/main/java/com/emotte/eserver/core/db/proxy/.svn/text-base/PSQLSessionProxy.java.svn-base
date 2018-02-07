package com.emotte.eserver.core.db.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.ConnectionManager;
import com.emotte.eserver.core.db.PSQLHandler;
import com.emotte.eserver.core.db.ResultHandler;
import com.emotte.eserver.core.db.SqlBuildHandler;
import com.emotte.eserver.core.db.annotation.Params;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.annotation.SelectKey;
import com.emotte.eserver.core.helper.LogHelper;

public class PSQLSessionProxy implements InvocationHandler {

	@SuppressWarnings("unchecked")
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.isAnnotationPresent(SQL.class)) {
			SQL sqlClass = method.getAnnotation(SQL.class);
			String sql = sqlClass.sql();
			SQLTYPE type = sqlClass.type();
			Class<?> returnType = method.getReturnType();
			Map<String,Object> agrsMap = new HashMap<String,Object>();
			Annotation[][] annotations= method.getParameterAnnotations();
			for(int i = 0; i < annotations.length ; i++ ){
				Annotation[] anns = annotations[i];
				if(anns.length > 0){
					for(Annotation ann : annotations[i]){
						if (ann instanceof Params) {
							Params params = (Params) ann;
							agrsMap.put(params.value(), args[i]);
						}
					}
				}else{
					if(args[i].getClass().isArray())
						continue;
					if(args[i].getClass().isPrimitive())
						continue;
					if(args[i].getClass().getName().startsWith("java.lang"))
						continue;
					if(args[i] instanceof List)
						continue;
					if (args[i] instanceof Map){
						agrsMap.putAll((Map<String,Object>) args[i]);
					}else{
						Object bean = args[i];
						Class<?> clazz = bean.getClass();
						Field[] fields = clazz.getDeclaredFields();
						for(Field f : fields){
							String methodstr = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
							try {
								Method getMethod = clazz.getDeclaredMethod(methodstr);
								agrsMap.put(f.getName(), getMethod.invoke(bean));
							} catch (Exception e) {
								LogHelper.info(getClass(), clazz + ":" + e.getMessage() + " method:" + methodstr );
							}
						}
					}
				}
			}
			
			Connection conn = ConnectionManager.getThreadLocalConnection(); // 从本地线程获得Connection
			switch (type) {
					case SELECT: {
						PSQLHandler phandler = new PSQLHandler(sql, args, agrsMap);
						String selSql = phandler.getSql().get(0);
						Object[] argobjs = phandler.getArgslist().get(0);
						PreparedStatement ps = conn.prepareStatement(selSql);
						StringBuilder sb = new StringBuilder("[");
						for (int i = 0; i < argobjs.length; i++) {
							Object o = argobjs[i];
							ps.setObject(i + 1, o);
							sb.append(o.toString() + ",");
						}
						sb.deleteCharAt(sb.length() - 1).append("]");
						LogHelper.info(getClass(),
								method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()[SQL]:" + selSql);
						LogHelper.info(getClass(), method.getDeclaringClass().getSimpleName() + "." + method.getName()
								+ "()[args]:" + sb.toString());
						ResultSet rs = ps.executeQuery();
						List<?> list = ResultHandler.build(rs, sqlClass.returnParam());
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
						SelectKey selectKey = sqlClass.selectKey();
						if (!"".equals(selectKey.sql())) {
							String keySql = selectKey.sql();
							String prop = selectKey.prop();
							Object retype = selectKey.returnType();
							ResultSet rs = stat.executeQuery(keySql);
							while (rs.next()) {
								if (retype == Long.class) {
									id = rs.getLong(1);
								} else if (retype == Integer.class) {
									id = rs.getInt(1);
								} else {
									id = rs.getString(1);
								}
								sql = SqlBuildHandler.replace(sql, "#{" + prop + "}", id);
							}
						}
						PSQLHandler phandler = new PSQLHandler(sql, args, agrsMap);
						String insertSql = phandler.getSql().get(0);
						Object[] argobjs = phandler.getArgslist().get(0);
						PreparedStatement ps = conn.prepareStatement(insertSql);
						StringBuilder sb = new StringBuilder("[");
						for (int i = 0; i < argobjs.length; i++) {
							Object o = argobjs[i];
							ps.setObject(i + 1, o);
							sb.append(o.toString() + ",");
						}
						sb.deleteCharAt(sb.length() - 1).append("]");
						LogHelper.info(getClass(),
								method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()[SQL]:" + insertSql);
						LogHelper.info(getClass(), method.getDeclaringClass().getSimpleName() + "." + method.getName()
								+ "()[args]:" + sb.toString());
						int size = ps.executeUpdate();
						return id == null ? (size > 0) : (size > 0 ? id : null);
					}
					case UPDATE:
					case DELETE:
					default: {
						PSQLHandler phandler = new PSQLHandler(sql, args, agrsMap);
						List<String> udSql = phandler.getSql();
						List<Object[]> argobjs = phandler.getArgslist();
						int size = 0;
						for (int i = 0; i < udSql.size(); i++) {
							String sonsql = udSql.get(i);
							Object[] sonargs = argobjs.get(i);
							PreparedStatement ps = conn.prepareStatement(sonsql);
							StringBuilder sb = new StringBuilder("[");
							for (int n = 0; n < sonargs.length; n++) {
								Object o = sonargs[n];
								ps.setObject(n + 1, o);
								sb.append(o.toString() + ",");
							}
							sb.deleteCharAt(sb.length() - 1).append("]");
							LogHelper.info(getClass(),
									method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()[SQL]:" + sonsql);
							LogHelper.info(getClass(), method.getDeclaringClass().getSimpleName() + "." + method.getName()
									+ "()[args]:" + sb.toString());
							size += ps.executeUpdate();
						}
						return size;
					}
				}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<?> clazz){
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz} , new PSQLSessionProxy());
	}

}

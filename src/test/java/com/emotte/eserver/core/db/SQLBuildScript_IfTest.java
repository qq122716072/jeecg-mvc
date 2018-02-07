package com.emotte.eserver.core.db;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.local.mapper.WrExampleMapper;
import com.emotte.eserver.core.db.local.model.Example;

public class SQLBuildScript_IfTest {
	
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, SecurityException {
		Class<?> clazz = WrExampleMapper.class;
		Method method = clazz.getMethod("multiUpdate", List.class);
		SQL sqlAnno = method.getAnnotation(SQL.class);
		String sql = sqlAnno.sql();
		try {
			List<Example> list = new ArrayList<Example>();
			Example example = new Example();
			example.setId(123l);
			example.setCreateBy(1l);
			list.add(example);
			Example example2 = new Example();
			example2.setId(123l);
			example2.setCreateBy(1l);
			list.add(example2);
			example.setCreateBy(2l);
			sql = SQLBuildScript_foreach.dealForeachScript(sql, list);
			sql = SQLBuildScript_if.dealIfScript(sql, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sql);
	}
	
}

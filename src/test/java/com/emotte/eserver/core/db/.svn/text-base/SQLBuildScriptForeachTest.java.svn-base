package com.emotte.eserver.core.db;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.local.mapper.TestMapper;
import com.emotte.eserver.core.db.local.model.Account;

public class SQLBuildScriptForeachTest {
	
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, SecurityException {
		Class<?> clazz = TestMapper.class;
		Method method = clazz.getMethod("updateAccountList", List.class);
		SQL sqlAnno = method.getAnnotation(SQL.class);
		String sql = sqlAnno.sql();
		List<Account> list = new ArrayList<Account>();
		for (int i = 0; i < 5; i++) {
			Account account = new Account();
			account.setId(Long.parseLong(i + ""));
			account.setName("name" + i);
			account.setBank("bank" + i);
			list.add(account);
		}
		
//		for (int i = 0; i < 5; i++) {
//			list.add(Long.parseLong(i+""));
//		}
		
//		for (int i = 0; i < 5; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("name", "name" + i);
//			map.put("bank", "bank" + i);
//			map.put("bankNum", Long.parseLong(i + ""));
//			list.add(map);
//		}
		
		sql = SQLBuildScript_foreach.dealForeachScript(sql, list);
		System.out.println(sql);
	}
	
}

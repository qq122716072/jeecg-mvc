package com.emotte;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import oracle.net.aso.a;

import com.emotte.eserver.core.db.SQLBuildScript_if;
import com.emotte.eserver.core.db.SqlBuildHandler;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.finance.mapper.TestMapper2;
import com.emotte.eserver.core.db.local.mapper.CommentMapper;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.model.Appraise;
import com.emotte.eserver.core.helper.StringHelper;

public class Eval {
	private static final String FIELD_REGEX = "#\\{.*?\\}";
	
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, SecurityException {
		Class<?> clazz = CommentMapper.class;
		Method method = clazz.getMethod("getCount", Appraise.class);
		SQL sqlAnno = method.getAnnotation(SQL.class);
		String sql = sqlAnno.sql();
		Account account = new Account();
		account.setName("123");
//		account.setId(123l);
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("isFisrt", "123");
		Appraise app = new Appraise();
		app.setIsFisrt(1);
		sql = SQLBuildScript_if.dealIfScript(sql, app);
		System.out.println(sql);
	}
	
	public static String dealIfScript (String s, Map<String, Object> map) throws ScriptException {
		Pattern p = Pattern.compile("@IF\\((.*?)\\)\\((.*?)\\)");
		Matcher m = p.matcher(s);
		while (m.find()) {
			String s1 = m.group();
			String s2 = m.group(1);
			String s3 = m.group(2);
			String re = replace(s2, map);
			if (ifScript(re)) {
				s = s.replace(s1, s3);
			} else {
				s = s.replace(s1, "");
			}
		}
        return s;
	}
	
	public static boolean ifScript (String s) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        return (Boolean) se.eval(s);
	}
	
	public static String replace (String s, Map<String, Object> map) throws ScriptException {
		String[] fields = StringHelper.findMatches(s, FIELD_REGEX);
		for (String field : fields) {
			Object value = map.get(field.replaceAll("#\\{(.*)\\}", "$1"));
			s = replace(s, field, value);
		}
        return s;
	}
	
	public static String replace(String sql, String field, Object value) {
		if (field == null) {
			field = StringHelper.findMatche(sql, FIELD_REGEX);
		}
		if (field == null) {
			return sql;
		}
		if (value == null) {
			sql = sql.replace(field, "null");
		} else if (value instanceof String) {
			sql = sql.replace(field, "\"" + value.toString() + "\"");
		} else if (value instanceof Number) {
			sql = sql.replace(field, value.toString());
		} else if (value instanceof Boolean) {
			sql = sql.replace(field, value.toString());
		} else {
			sql = sql.replace(field, value.toString());
		}
		return sql;
	}
}

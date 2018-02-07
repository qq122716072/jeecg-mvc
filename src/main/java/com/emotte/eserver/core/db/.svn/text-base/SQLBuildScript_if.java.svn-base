package com.emotte.eserver.core.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.emotte.eserver.core.db.exception.SqlBuildException;
import com.emotte.eserver.core.helper.ReflectHelper;
import com.emotte.eserver.core.helper.StringHelper;
import com.emotte.kernel.exception.BaseException;

/**
 * sql构建脚本
 * @author ChengJing
 * 2017年6月14日
 */
public class SQLBuildScript_if {
	private static final String FIELD_REGEX = "#\\{.*?\\}";
	
	/**
	 * 处理if脚本
	 * @param s
	 * @param map
	 * @return
	 * @throws ScriptException
	 * 2017年6月14日
	 */
	@SuppressWarnings("unchecked")
	public static String dealIfScript (String s, Object... args) throws ScriptException {
		if (args == null || args.length == 0) return s;
		Pattern p = Pattern.compile("@IF\\((.*?)\\)\\[(.*?)\\]");
		Matcher m = p.matcher(s);
		while (m.find()) {
			String s1 = m.group();
			String s2 = m.group(1);
			String s3 = m.group(2);
			for (Object object : args) {
				if (object == null) {
					s2 = replace(s2, null, object);
				} else if (object instanceof String) {
					s2 = replace(s2, null, object);
				} else if (object instanceof Number) {
					s2 = replace(s2, null, object);
				} else if (object instanceof Boolean) {
					s2 = replace(s2, null, object);
				} else if (object.getClass().isArray()) {
					s2 = replaceArray(s2, (Object[]) object);
				} else if (object instanceof ArrayList) {
					s2 = replaceList(s2, (List<?>) object);
				} else if (object instanceof Map) {
					s2 = replaceMap(s2, (Map<String, Object>) object);
				} else {
					s2 = replaceObject(s2, object);
				}
			}
			if (ifScript(s2)) {
				s = s.replace(s1, s3);
			} else {
				s = s.replace(s1, "");
			}
		}
        return s;
	}
	
	/**
	 * 处理foreach脚本
	 * 待开发
	 * @param s
	 * @param coll
	 * @return
	 * 2017年6月14日
	 */
	public static String dealForeachScript (String s, Collection<?> coll) {
		throw new BaseException("暂不支持该功能，待后续开发");
//		Pattern p = Pattern.compile("@IF\\((.*?)\\)\\((.*?)\\)");
//		Matcher m = p.matcher(s);
//		while (m.find()) {
//		}
//        return s;
	}
	
	/**
	 * java执行器，用于处理@IF()标签内的逻辑运算
	 * @param s
	 * @return
	 * @throws ScriptException
	 * 2017年6月14日
	 */
	private static boolean ifScript (String s) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        return (Boolean) se.eval(s);
	}
	
	/**
	 * 内容转换，将字符串中的替换符用map中的值替换掉
	 * @param s
	 * @param map
	 * @return
	 * @throws ScriptException
	 * 2017年6月14日
	 */
	private static String replaceMap (String s, Map<String, Object> map) throws ScriptException {
		String[] fields = StringHelper.findMatches(s, FIELD_REGEX);
		for (String field : fields) {
			Object value = map.get(field.replaceAll("#\\{(.*)\\}", "$1"));
			s = replace(s, field, value);
		}
        return s;
	}
	
	private static String replaceArray (String sql, Object[] objects) {
		for (Object object : objects) {
			sql = replace(sql, null, object);
		}
		return sql;
	}
	
	private static String replaceList (String sql, List<?> objects) {
		for (Object object : objects) {
			sql = replace(sql, null, object);
		}
		return sql;
	}
	
	private static String replaceObject (String sql, Object object) throws SqlBuildException {
		String[] fields = StringHelper.findMatches(sql, FIELD_REGEX);
		try {
			for (String field : fields) {
				Object value = ReflectHelper.getValueByFieldName(object, field.replaceAll("#\\{(.*)\\}", "$1"));
				sql = replace(sql, field, value);
			}
		} catch (Exception e) {
			throw new SqlBuildException(e);
		}
		return sql;
	}
	
	private static String replace(String sql, String field, Object value) {
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

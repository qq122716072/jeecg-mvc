package com.emotte.eserver.core.db;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import com.emotte.eserver.core.db.exception.SqlBuildException;
import com.emotte.eserver.core.helper.ReflectHelper;
import com.emotte.eserver.core.helper.StringHelper;

/**
 * sql构建脚本
 * @author ChengJing
 * 2017年6月14日
 */
public class SQLBuildScript_foreach {
	private static final String FIELD_REGEX = "#\\{.*?\\}";
	
	/**
	 * 处理foreach脚本
	 * @param s
	 * @param args
	 * @return
	 * @throws ScriptException
	 * 2017年6月14日
	 */
	public static String dealForeachScript (String s, Object... args) throws ScriptException {
		if (args == null || args.length == 0) return s;
		String regex = "@FOREACH\\((.*?)\\)\\[(.*?)\\]"; // 不内嵌@IF
		String regex2 = "@FOREACH\\((.*?)\\)\\[(.*)\\]"; // 内嵌@IF
		if (s.matches("@FOREACH\\((.*?)\\)\\[(.*@IF.*?)\\]")) { // 判断是否内嵌@IF
			regex = regex2;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		while (m.find()) {
			String s1 = m.group();
			String s2 = m.group(1);
			String s3 = m.group(2);
			s3 = dealScript(s2, s3, args);
			s = s.replace(s1, s3);
		}
        return s;
	}

	@SuppressWarnings({ "rawtypes" })
	private static String dealScript(String s2, String s3, Object... args) {
		String holder = null;
		holder = StringHelper.findMatche(s2, "'\\s*,\\s*'");
		if (holder != null && holder.length() > 0) {
			s2 = s2.replace(holder, "$1$");
		}
		String collection = null;
		String separate = null;
		String item = null;
		String[] ss = s2.split(",");
		for (String string : ss) {
			String str = string;
			if (holder != null) {
				str = string.trim().replace("$1$", holder);
			}
			if (str.contains("separate=")) {
				String strs = str.split("=")[1];
				separate = strs.trim().replace("'", "");
			}
			if (str.contains("item=")) {
				String strs = str.split("=")[1];
				item = strs.trim().replace("'", "");
			}
			if (str.contains("collection=")) {
				String strs = str.split("=")[1];
				collection = strs.trim().replace("'", "");
			}
		}
		StringBuffer sqlSB = new StringBuffer();
		Object obj = getCollection(collection, args);
		if (obj.getClass().isArray()) {
			for (Object o : (Object[])obj) {
				sqlSB.append(rebuild(s3, item, o));
//				if (";".equals(separate.trim())) {
//					sqlSB.append(separate + "\n");
//				} else {
//					sqlSB.append(separate + " ");
//				}
				sqlSB.append(separate + " ");
			}
		} else if (obj instanceof List) {
			for (Object o : (List)obj) {
				sqlSB.append(rebuild(s3, item, o));
//				if (";".equals(separate.trim())) {
//					sqlSB.append(separate + "\n");
//				} else {
//					sqlSB.append(separate + " ");
//				}
				sqlSB.append(separate + " ");
			}
		}
		if (sqlSB.length() > 0) {
			sqlSB.setLength(sqlSB.length() - separate.length() - 1);
		}
		return sqlSB.toString();
	}
	
	@SuppressWarnings("unchecked")
	private static String rebuild (String s3, String item, Object o) {
		String s = null;
		if (o == null) {
			s = replace(s3, null, o);
		} else if (o instanceof String) {
			s = replace(s3, null, o);
		} else if (o instanceof Number) {
			s = replace(s3, null, o);
		} else if (o instanceof Boolean) {
			s = replace(s3, null, o);
		} else if (o instanceof Map) {
			try {
				s = replaceMap(s3, item, (Map<String, Object>) o) + " ";
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		} else {
			s = replaceObject(s3, item, o) + " ";
		}
		return s;
	}
	
	@SuppressWarnings({"unchecked"})
	private static Object getCollection(String collection, Object... args) {
		Object obj = null;
		if (args instanceof String[] || args instanceof Number[]) {
			return args;
		}
		for (Object object : args) {
			if (object == null) {
			} else if (object instanceof String) {
			} else if (object instanceof Number) {
			} else if (object instanceof Boolean) {
			} else if (object.getClass().isArray()) {
				obj = object;
			} else if (object instanceof List) {
				obj = object;
			} else if (object instanceof Map) {
				obj = ((Map<String, Object>)object).get(collection.replaceAll("#\\{(.*)\\}", "$1"));
			}
		}
		return obj;
	}
	
	/**
	 * 内容转换，将字符串中的替换符用map中的值替换掉
	 * @param s
	 * @param map
	 * @return
	 * @throws ScriptException
	 * 2017年6月14日
	 */
	private static String replaceMap (String s, String item, Map<String, Object> map) throws ScriptException {
		String[] fields = StringHelper.findMatches(s, FIELD_REGEX);
		for (String field : fields) {
			Object value = map.get(field.replaceAll("#\\{(.*)\\}", "$1").replace(item + ".", ""));
			s = replace(s, field, value);
		}
        return s;
	}
	
	private static String replaceObject (String sql, String item, Object object) throws SqlBuildException {
		String[] fields = StringHelper.findMatches(sql, FIELD_REGEX);
		try {
			for (String field : fields) {
				Object value = ReflectHelper.getValueByFieldName(object, field.replaceAll("#\\{(.*)\\}", "$1").replace(item + ".", ""));
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
			sql = sql.replace(field, "'" + value.toString() + "'");
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

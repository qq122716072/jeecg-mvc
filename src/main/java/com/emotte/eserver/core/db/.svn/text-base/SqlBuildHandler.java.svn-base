package com.emotte.eserver.core.db;

import java.util.Map;

import com.emotte.eserver.core.db.exception.SqlBuildException;
import com.emotte.eserver.core.helper.ReflectHelper;
import com.emotte.eserver.core.helper.StringHelper;

public class SqlBuildHandler {
	
	private static final String FIELD_REGEX = "#\\{.*?\\}";

	@SuppressWarnings("unchecked")
	public static String rebuild(String sql, Object... args) throws SqlBuildException {
		if (args == null || args.length == 0) return sql;
		for (Object object : args) {
			if (object == null) {
				sql = replace(sql, null, object);
			} else if (object instanceof String) {
				sql = replace(sql, null, object);
			} else if (object instanceof Number) {
				sql = replace(sql, null, object);
			} else if (object instanceof Boolean) {
				sql = replace(sql, null, object);
			} else if (object.getClass().isArray()) {
				sql = arrayBuild(sql, (Object[]) object);
			} else if (object instanceof Map) {
				sql = mapBuild(sql, (Map<String, Object>) object);
			} else {
				sql = objectBuild(sql, object);
			}
		}
		return sql;
	}

	private static String arrayBuild (String sql, Object[] objects) {
		for (Object object : objects) {
			sql = replace(sql, null, object);
		}
		return sql;
	}
	private static String objectBuild(String sql, Object object) throws SqlBuildException {
		String[] fields = StringHelper.findMatches(sql, FIELD_REGEX);
		for (String field : fields) {
			try {
				Object value = ReflectHelper.getValueByFieldName(object, field.replaceAll("#\\{(.*)\\}", "$1"));
				sql = replace(sql, field, value);
			} catch (Exception e) {
	//			throw new SqlBuildException(e);
			}
		}
		return sql;
	}

	public static String mapBuild(String sql, Map<String, Object> object) {
		String[] fields = StringHelper.findMatches(sql, FIELD_REGEX);
		for (String field : fields) {
			Object value = object.get(field.replaceAll("#\\{(.*)\\}", "$1"));
			sql = replace(sql, field, value);
		}
		return sql;
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

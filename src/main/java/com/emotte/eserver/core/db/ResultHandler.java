package com.emotte.eserver.core.db;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emotte.eserver.core.helper.ReflectHelper;

public class ResultHandler {
	
	@SuppressWarnings("rawtypes")
	public static List build (ResultSet rs, Class t) throws Exception {
		List list = new ArrayList();
		if (t == Map.class || t == Object.class) {
			buildMap(rs, list);
		} else {
			buildObject(rs, list, t);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void buildMap (ResultSet rs, List list) throws Exception {
		ResultSetMetaData metaData = rs.getMetaData();
		int i = metaData.getColumnCount();
		while (rs.next()) {
			Map map =  new HashMap();
			for (int j = 1; j <= i; j++) {
				String label = metaData.getColumnName(j);
				Object value = rs.getObject(j);
				map.put(label, value);
			}
			list.add(map);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void buildObject (ResultSet rs, List list, Class t) throws Exception {
		ResultSetMetaData rsd = rs.getMetaData();
		int col = rsd.getColumnCount();
		List<Field[]> listField = new ArrayList<>();
		//Field[] fields = t.getDeclaredFields();
		
		for (Class<?> superClass = t.newInstance().getClass();superClass != Object.class;superClass = superClass.getSuperclass()) {
			 Field[] superFields = superClass.getDeclaredFields();
			 if(superFields != null && superFields.length > 0){
				 listField.add(superFields);
			 }
		}
		while (rs.next()) {
			Object obj = t.newInstance();
			for (int i = 1; i <= col; i++) {
				String column = rsd.getColumnName(i);
				// ORDER_ID -> order_id -> orderId
				Field field = null;
				for (int j = 0; j < listField.size(); j++) {
					Field[] fields = listField.get(j);
					for (Field field_ : fields) {
						String name = field_.getName();
						if (changeFeildName(column).equalsIgnoreCase(name)) {
							field = field_;
							break;
						}
					}
				}
				try {
					if (field != null) {
						String name = field.getName();
						Class type = field.getType();
						Object value = null;
						try {
							if (type == Long.class) {
								value = rs.getLong(column);
							} else if (type == Integer.class) {
								value = rs.getInt(column);
							} else if (type == Boolean.class) {
								value = rs.getBoolean(column);
							} else if (type == Float.class) {
								value = rs.getFloat(column);
							} else if (type == Double.class) {
								value = rs.getDouble(column);
							} else if (type == String.class) {
								value = rs.getString(column);
							} else {
								value = rs.getObject(column);
							}
							if(rs.wasNull()){
								value = null;
							}
						} catch (Exception e) {
						}
						ReflectHelper.setValueByFieldName(obj, name, value);
					}
				} catch (Exception e) {
					// do nothing
				}
			}
			list.add(obj);
		}
		
//		while (rs.next()) {
//			Object obj = t.newInstance();
//			Field[] fields = t.getDeclaredFields();
//			for (Field field : fields) {
//				String name = field.getName();
//				Class type = field.getType();
//				Object value = null;
//				try {
//					if (type == Long.class) {
//						value = rs.getLong(name);
//					} else if (type == Integer.class) {
//						value = rs.getInt(name);
//					} else if (type == Boolean.class) {
//						value = rs.getBoolean(name);
//					} else if (type == Float.class) {
//						value = rs.getFloat(name);
//					} else if (type == Double.class) {
//						value = rs.getDouble(name);
//					} else if (type == String.class) {
//						value = rs.getString(name);
//					} else {
//						value = rs.getObject(name.toUpperCase());
//					}
//				} catch (Exception e) {
//					// do nothing
//				}
//				if (null != value) {
//					ReflectHelper.setValueByFieldName(obj, name, value);
//				}
//			}
//			list.add(obj);
//		}
	}
	public static String changeFeildName (String column) {
//		String column = "ORDER_ID_AGREEMENT";
		column = column.toLowerCase();
		Pattern p = Pattern.compile("_(\\w)");
		Matcher m = p.matcher(column);
		while (m.find()) {
			String s = m.group(0);
			String s2 = m.group(1);
			column = column.replace(s, s2.toUpperCase());
		}
		return column;
	}
	
	public static void main(String[] args) {
		System.out.println(changeFeildName("ORDER_ID_AGREEMENT"));
	}
}

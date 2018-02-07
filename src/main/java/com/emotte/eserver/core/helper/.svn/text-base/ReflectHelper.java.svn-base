package com.emotte.eserver.core.helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 *	反射工具
 */
public class ReflectHelper {
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) throws NoSuchFieldException {
		NoSuchFieldException ex = new NoSuchFieldException();
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				ex = e;
			}
		}
		throw ex;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws NoSuchFieldException {
		Field field = getFieldByFieldName(obj, fieldName);
		@SuppressWarnings("rawtypes")
		Class type = field.getType();
		if(value == null){
			value = null;
		} else if (type == String.class) {
			value = value.toString();
		} else if (type == Long.class || type == long.class) {
			value = Long.parseLong(value.toString());
		} else if (type == Integer.class || type == int.class) {
			value = Integer.parseInt(value.toString());
		} else if (type == Boolean.class || type == boolean.class) {
			value = Boolean.parseBoolean(value.toString());
		} else if (type == Float.class || type == float.class) {
			value = Float.parseFloat(value.toString());
		} else if (type == Double.class || type == double.class) {
			value = Double.parseDouble(value.toString());
		}
		try {
			if (field.isAccessible()) {
				field.set(obj, value);
			} else {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 唤醒方法
	 * @param obj
	 * @param methodName
	 * @param params
	 * @param params
	 * @return
	 * 2016年10月20日
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeMethod(Object obj, String methodName, Object... params) throws NoSuchMethodException {
		Class<?>[] parameterTypes = null;
		if (null != params && params.length > 0) {
			parameterTypes = new Class<?>[params.length];
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				if (param != null) {
					parameterTypes[i] = params[i].getClass();
				} else {
					parameterTypes[i] = null;
				}
			}
		}
		Method method = getMethod(obj, methodName, parameterTypes);
		return invokeMethod(obj, method, params);
	}
	
	/**
	 * 唤醒方法
	 * @param obj
	 * @param method
	 * @param params
	 * @return
	 * 2016年10月20日
	 */
	public static Object invokeMethod(Object obj, Method method, Object... params) {
		Object value = null;
		if(method!=null){
			try {
				if (method.isAccessible()) {
					value = method.invoke(obj, params);
				} else {
					method.setAccessible(true);
					value = method.invoke(obj, params);
					method.setAccessible(false);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	/**
	 * 获得方法
	 * @param obj
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 * 2016年10月20日
	 * @throws NoSuchMethodException 
	 */
	public static Method getMethod (Object obj, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
		NoSuchMethodException ex = new NoSuchMethodException();
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				ex = e;
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		throw ex;
	}
	
	/**
	 * 填充对象
	 * @param obj
	 * @param params
	 * @throws NoSuchMethodException
	 * 2017年4月12日
	 */
	public static void fillingByMethod (Object obj, Map<String, ?> params) throws NoSuchMethodException {
		for (Entry<String, ?> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			ReflectHelper.invokeMethod(obj, buildSetMethod(key), value);
		}
	}
	
	/**
	 * 填充对象
	 * @param obj
	 * @param params
	 * @throws NoSuchFieldException 
	 * 2017年4月12日
	 */
	public static void fillingByField (Object obj, Map<String, ?> params) throws NoSuchFieldException {
		for (Entry<String, ?> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			ReflectHelper.setValueByFieldName(obj, key, value);
		}
	}
	
	/**
	 * 构建set方法
	 * @param key
	 * @return
	 * 2017年4月12日
	 */
	private static String buildSetMethod (String key) {
		StringBuffer sb = new StringBuffer();
		sb.append("set").append(key.substring(0, 1).toUpperCase()).append(key.substring(1));
		return sb.toString();
	}
}

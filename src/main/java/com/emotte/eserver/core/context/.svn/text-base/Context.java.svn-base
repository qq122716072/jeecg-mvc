package com.emotte.eserver.core.context;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.bean.BeanManager;
import com.emotte.eserver.core.db.ESessionFactory;
import com.emotte.eserver.core.db.ESessionManager;
import com.emotte.eserver.core.exception.BeanAlreadyExistsException;
import com.emotte.eserver.core.helper.LogHelper;

public class Context {
	
	private static Map<String, Object> container = new HashMap<String, Object>();
	private static Map<String, List<Object>> typeContainer = new HashMap<String, List<Object>>();

    /**
     * 加载Bean配置文件
     *
     * @param context
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static void initialize (String context) throws Exception {
		LogHelper.info(Context.class, "加载配置文件【"+context+"】");
		BeanManager.readBeans(context);
		ESessionManager.addFactory(getBean(ESessionFactory.class));
		LogHelper.info(Context.class, "配置文件【"+context+"】加载完毕");
	}
	
	public static Object getBean(String serviceName) {
		return container.get(serviceName);
	}
	
	@SuppressWarnings("rawtypes")
	public static List getBean(Class type) {
		return typeContainer.get(type.getName());
	}
	@SuppressWarnings("rawtypes")
	public static Object setBean (String serviceName, Class clazz, Object obj) {
//		serviceName = clazz.getPackage().getName() + "." + serviceName;
//		if (container.containsKey(serviceName)) throw new BeanAlreadyExistsException(serviceName, clazz);
//		Object old = container.put(serviceName, obj);
//		LogHelper.info(Context.class, "初始化【"+clazz.getName()+"】成功，服务名【"+serviceName+"】");
//		List<Object> list = typeContainer.get(clazz.getName());
//		if (list == null) {
//			list = new ArrayList<Object>();
//			typeContainer.put(clazz.getName(), list);
//		}
//		list.add(obj);
		return setBean(serviceName, clazz, obj, false);
	}
	
	@SuppressWarnings("rawtypes")
	public static Object setBean (String serviceName, Class clazz, Object obj, Boolean isXml) {
		if (!isXml) {
			serviceName = clazz.getPackage().getName() + "." + serviceName;
		}
		if (container.containsKey(serviceName)) {
			throw new BeanAlreadyExistsException(serviceName, clazz);
		}
		Object old = container.put(serviceName, obj);
		LogHelper.info(Context.class, "初始化【"+clazz.getName()+"】成功，服务名【"+serviceName+"】");
		List<Object> list = typeContainer.get(clazz.getName());
		if (list == null) {
			list = new ArrayList<Object>();
			typeContainer.put(clazz.getName(), list);
		}
		list.add(obj);
		return old;
	}
	/**
	 * 唤起
	 * @param serviceName
	 * @param version
	 * @param methodName
	 * @param params
	 * @exception Exception
	 * @return 返回字符串
	 * 2017年3月22日
	 */
	public static String invoke (String serviceName, String version, String methodName, Object... params) throws Exception {
		// 服务名称加版本号
		Object obj = Context.getBean(serviceName + "-" + version);
		Class<?>[] classes = new Class[params.length];
//		if (params.length == 0) {
//			Method method = obj.getClass().getDeclaredMethod(methodName);
//			return method.invoke(obj).toString();
//		}
		int c = 0;
		for (Object object : params) {
			classes[c++] = object.getClass();
		}
		Method method = obj.getClass().getDeclaredMethod(methodName, classes);
		return method.invoke(obj, params).toString();
	}
	
}

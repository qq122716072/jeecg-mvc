package com.emotte.eserver.core.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;
import org.dom4j.Node;

import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.ReflectHelper;

public class BeanHandler {

	/**
	 * 初始化Bean
	 *
	 * @param element
	 * @param root
	 * @return
	 * @throws Exception
	 */
	public static Object initBean (Element element, Element root) throws Exception {
		BeanHolder holder = BeanHolder.parseBeanElement(element);
		Object bean = Context.getBean(holder.getId());
		if (bean != null) {
			return bean;
		}
		String clazzStr = holder.getClazz();
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(clazzStr);
		Object instance = clazz.newInstance();
		ReflectHelper.fillingByField(instance, holder.getProperties());
		
		Map<String, String> refs = holder.getRefs();
		Map<String, Object> refsO = new HashMap<String, Object>();
		for (Entry<String, String> entry : refs.entrySet()) {
			String key = entry.getKey();
			String ref = entry.getValue();
			Object value = null;
			// 判断是否已经初始化
			if (Context.getBean(ref) == null) {
				Node ele = root.selectSingleNode("//bean[@id='"+ref+"']");
				// 迭代处理
				value = initBean((Element) ele, root);
			} else {
				value = Context.getBean(ref);
			}
			refsO.put(key, value);
		}
		// 初始化引用bean
		ReflectHelper.fillingByField(instance, refsO);
		// 调用初始化函数
		if (holder.getInitMethod() != null) {
			LogHelper.info("", holder.getInitMethod());
			ReflectHelper.invokeMethod(instance, holder.getInitMethod());
		}
		// 将生成的bean装入容器
		Context.setBean(holder.getId(), instance.getClass(), instance, true);
		return instance;
	}
	
}

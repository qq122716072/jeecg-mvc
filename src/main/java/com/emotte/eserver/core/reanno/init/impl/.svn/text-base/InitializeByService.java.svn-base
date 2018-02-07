package com.emotte.eserver.core.reanno.init.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.emotte.eserver.core.annotation.Component;
import com.emotte.eserver.core.annotation.EService;
import com.emotte.eserver.core.annotation.Service;
import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.db.annotation.Mapper;
import com.emotte.eserver.core.db.proxy.SQLSessionProxy;
import com.emotte.eserver.core.exception.InitException;
import com.emotte.eserver.core.helper.ClassHelper;
import com.emotte.eserver.core.helper.ReflectHelper;
import com.emotte.eserver.core.proxy.ServiceProxy;
import com.emotte.eserver.core.reanno.init.InitializeByAnnotation;
import com.emotte.kernel.exception.BaseException;

public class InitializeByService implements InitializeByAnnotation {
    
	public void init(String cla, ClassLoader classLoader) throws InitException, Exception {
		Class<?> clazz = null;
		if (null == classLoader) {
			clazz = Class.forName(cla);
		} else {
			clazz = Class.forName(cla, true, classLoader);
		}
		recursion(clazz, classLoader);
	}

	private Object recursion (Class<?> clazz, ClassLoader classLoader) throws Exception {
		if (clazz.isInterface() && clazz.getAnnotations().length == 0) {
			clazz = ClassHelper.getSubClass(clazz, classLoader);
		}
		if (clazz == null) return null;
		Service serviceAnno = clazz.getAnnotation(Service.class);
		Object service = null;
		if (serviceAnno != null) {
			String name = serviceAnno.value();
			String version = serviceAnno.version();
			if (StringUtils.isBlank(name)) {
				try {
					Type type = clazz.getGenericInterfaces()[0];
					name = type.toString();
					name = name.substring(name.lastIndexOf('.')+1);
					if (name == null) throw new BaseException();
				} catch (Exception e) {
					name = clazz.getSimpleName();
				}
				name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
			}
			service = Context.getBean(clazz.getPackage().getName() + "." + name + "-" + version);
			if (service != null) {
				return service;
			}
			if (clazz.isInterface()) {
				service = SQLSessionProxy.getInstance(clazz);
			} else {
				service = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					Resource resourceAnno = field.getAnnotation(Resource.class);
					Class<?> fieldType = field.getType();
					if (null != resourceAnno) {
						Object subObj = recursion(fieldType, classLoader);
						ReflectHelper.setValueByFieldName(service, field.getName(), subObj);
					}
				}
				// 服务名称加版本号
				service = ServiceProxy.getInstance(service);
			}
			Context.setBean(name + "-" + version, clazz, service);
		}
		Component componentAnno = clazz.getAnnotation(Component.class);
		if (componentAnno != null) {
			String name = componentAnno.value();
			String version = componentAnno.version();
			String method = componentAnno.initMethod(); // 初始化方法
			if (StringUtils.isBlank(name)) {
				name = clazz.getSimpleName();
				name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
			}
			service = Context.getBean(clazz.getPackage().getName() + "." + name + "-" + version);
			if (service != null) {
				return service;
			}
			service = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Resource resourceAnno = field.getAnnotation(Resource.class);
				Class<?> fieldType = field.getType();
				if (null != resourceAnno) {
					Object subObj = recursion(fieldType, classLoader);
					ReflectHelper.setValueByFieldName(service, field.getName(), subObj);
				}
			}
			if (!StringUtils.isBlank(method)) {
				ReflectHelper.invokeMethod(service, method); // 唤醒初始化方法
			}
			// 服务名称加版本号
//			service = ServiceProxy.getInstance(service);
			Context.setBean(name + "-" + version, clazz, service);
		}
		
		EService iserviceAnno = clazz.getAnnotation(EService.class);
		if (iserviceAnno != null) {
			String name = iserviceAnno.value();
			String version = iserviceAnno.version();
			if (StringUtils.isBlank(name)) {
				try {
					Type type = clazz.getGenericInterfaces()[0];
					name = type.toString();
					name = name.substring(name.lastIndexOf('.')+1);
					if (name == null) throw new BaseException();
				} catch (Exception e) {
					name = clazz.getSimpleName();
				}
				name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
			}
			service = Context.getBean(clazz.getPackage().getName() + "." + "e-" + name + "-" + version);
			if (service != null) {
				return service;
			}
			service = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Resource resourceAnno = field.getAnnotation(Resource.class);
				Class<?> fieldType = field.getType();
				if (null != resourceAnno) {
					Object subObj = recursion(fieldType, classLoader);
					ReflectHelper.setValueByFieldName(service, field.getName(), subObj);
				}
			}
			Context.setBean("e-" + name + "-" + version, clazz, service);
			ApiAnalysizer.operateDB(clazz,version,name);
		}
		
		Mapper anno = clazz.getAnnotation(Mapper.class);
		if (null != anno) {
			String name = anno.value();
			String version = anno.version();
			if (StringUtils.isBlank(name)) {
				name = clazz.getSimpleName();
				name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
			}
			service = Context.getBean(clazz.getPackage().getName() + "." + name + "-" + version);
			if (service != null) {
				return service;
			}
			if (clazz.isInterface()) {
				service = SQLSessionProxy.getInstance(clazz);
			}
			Context.setBean(name + "-" + version, clazz, service);
		}
		return service;
	}
}

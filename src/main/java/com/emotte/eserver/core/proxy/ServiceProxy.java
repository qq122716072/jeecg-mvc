package com.emotte.eserver.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.emotte.eserver.core.db.ConnectionManager;
import com.emotte.eserver.core.db.ESessionFactory;
import com.emotte.eserver.core.db.ESessionManager;

public class ServiceProxy implements InvocationHandler {
	
	private Object obj;
	private ServiceProxy(Object obj) {
		this.obj = obj;
	}
	
	public static Object getInstance (Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new ServiceProxy(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ESessionFactory factory = ESessionManager.getFactory(obj.getClass().getName());
		ConnectionManager.createConnection(factory);
		ConnectionManager.beginTransaction();
		Object result = null;
		try {
			result = method.invoke(obj, args);
			ConnectionManager.commit();
		} catch (InvocationTargetException e) {
			ConnectionManager.rollBack();
			throw e.getCause();
		} finally {
			ConnectionManager.closeConnection();
		}
		return result;
	}
}

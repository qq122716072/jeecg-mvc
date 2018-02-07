package com.emotte.eserver.core.exception;

import com.emotte.kernel.exception.BaseException;

public class BeanAlreadyExistsException extends BaseException {

	/**
	 * TODO
	 * 2017年10月20日
	 */
	private static final long serialVersionUID = 1L;
	
	public BeanAlreadyExistsException(Throwable e) {
		super(e);
	}
	
	public BeanAlreadyExistsException(String beanName, Class<?> clazz) {
//		super("the beanName [" + beanName + "] alread exists, you can not set the name of class ["+clazz.getCanonicalName()+"] to context.");
		super("[" + beanName + "]已经存在，不能将类[" + clazz.getCanonicalName() + "]的名称存储到容器中");
	}
	
	public static void main(String[] args) {
		BeanAlreadyExistsException e = new BeanAlreadyExistsException("serviceA", String.class);
		System.out.println(e.getMessage());
	}
	
	

}

package com.emotte.eserver.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2017年12月4日
 * @author yj
 * 接口文档api
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Api {
	public enum REQUESTYPE {POST,GET};
	public enum OPERATIONTYPE {UPDATE,INSERT,NO};
	/**
	 * 模块名称
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String module() default "";
	/**
	 * 参数示例
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String paramsExamp() default "";
	/**
	 * 必填字段
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String paramNeeds() default "";
	/**
	 * ip 多个已;分隔
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String ip() default "" ;
	/**
	 * 终端 多个已,分隔
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String terminal() default "";
	/**
	 * 是否测试(true:是(默认) false：否)
	 * yj
	 * 2017年12月4日	
	 * @return
	 * boolean
	 */
	boolean isTest() default true;
	/**
	 * 接口是否启用(true:是  false：否(默认))
	 * yj
	 * 2017年12月4日	
	 * @return
	 * boolean
	 */
	boolean isEnabled() default false;
	/**
	 * 返回示例
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String returnSample() default "";
	/**
	 * 返回说明
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String returnExplain() default "";
	/**
	 * 接口的请求方式
	 * yj
	 * 2017年12月4日	
	 * @return
	 * REQUESTYPE
	 */
	REQUESTYPE requestType()  default REQUESTYPE.POST;
	/**
	 * 操作类型（插入，更新，不做任何操作）
	 * yj
	 * 2017年12月4日	
	 * @return
	 * OPERATIONTYPE
	 */
	OPERATIONTYPE operation() default OPERATIONTYPE.INSERT;
	/**
	 * 操作开发人 （必填）
	 * yj
	 * 2017年12月4日	
	 * @return
	 * String
	 */
	String empolder();
	
}

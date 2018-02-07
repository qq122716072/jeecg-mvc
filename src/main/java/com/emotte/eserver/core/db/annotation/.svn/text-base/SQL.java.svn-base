package com.emotte.eserver.core.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SQL {
	public enum SQLTYPE{ SELECT, INSERT, UPDATE, DELETE};
	String sql();
	SQLTYPE type();
	Class<?> returnParam() default Object.class;
	/**
	 * 查询键
	 * @return
	 * 2017年5月27日
	 */
	SelectKey selectKey() default @SelectKey(sql="", prop="");
}

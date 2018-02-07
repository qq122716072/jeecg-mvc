package com.emotte.eserver.core.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SelectKey {
	String sql();
	Class<?> returnType() default Long.class;
	/**
	 * 属性
	 * @return
	 * 2017年5月27日
	 */
	String prop();
}

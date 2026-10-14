package com.ywjc.datadd.core.repository.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询对象所用注解
 * 
 * @author liu_k
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryField {

	MatchType matchType() default MatchType.EQUAL;
	String fieldName() default "";
	
}

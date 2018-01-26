package com.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** 
 * @Description controller和方法上的注解
 * @author tomlxq
 * @date 2017年8月30日下午5:16:05
 * 
 */  
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

	/**
	 * 表示访问该方法的url
	 * @return
	 */
	String value() default "";
}

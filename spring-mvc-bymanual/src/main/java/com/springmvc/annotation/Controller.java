package com.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Description controller注册的注解
 * @author tomlxq
 * @date 2017年8月30日下午5:12:55
 * 
 */  
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

	/**
	 * 表示给controller注册别名
	 * @return
	 */
	String value() default "";
}

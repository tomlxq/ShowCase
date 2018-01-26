package com.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** 
 * @Description 用作请求传参数的别名
 * @author tomlxq
 * @date 2017年8月31日下午2:31:42
 * 
 */  
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {

	/**
	 * 表示参数的别名，必填
	 * @return
	 */
	String value();
}

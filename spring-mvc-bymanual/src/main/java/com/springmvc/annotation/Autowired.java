package com.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Description 自动注入注解（如果不加别名自动通过接口类型注入实现类）
 * @author tomlxq
 * @date 2017年8月30日下午5:12:40
 * 
 */  
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {

	/**
	 * 表示给filed注入的bean的name
	 * @return
	 */
	String value() default "";
}

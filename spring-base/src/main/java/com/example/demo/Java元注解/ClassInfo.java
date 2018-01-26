package com.example.demo.Java元注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tom on 2018/1/26.
 */
// 适用类、接口（包括注解类型）或枚举
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassInfo {
    String value() default "";
}
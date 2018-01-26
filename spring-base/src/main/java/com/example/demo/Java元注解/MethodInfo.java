package com.example.demo.Java元注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by tom on 2018/1/26.
 */
// 适用方法
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodInfo {
    String name() default "long";
    String data() default "";
    int age() default 27;
}
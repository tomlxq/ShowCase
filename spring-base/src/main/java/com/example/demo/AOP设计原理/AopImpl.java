package com.example.demo.AOP设计原理;

/**
 * Created by tom on 2018/1/25.
 */
public class AopImpl implements Aop {
    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
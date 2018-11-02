package com.example.demo.proxy.cglib;




import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TomCglibProxy implements MethodInterceptor {
    public Object getInstance(Class o) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(o);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是Cglib媒婆");
        Object oo = methodProxy.invokeSuper(o, objects);
        System.out.println("我是Cglib媒婆，帮找到爱");
        return oo;
    }
};


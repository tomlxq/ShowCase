package com.example.demo.AOP设计原理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by tom on 2018/1/25.
 */
public class AopProxy implements InvocationHandler {
    private Aop target;
    public AopProxy(Aop target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前输出");
        Object object = method.invoke(target, args);
        System.out.println("方法执行后输出");
        return object;
    }
    public  Aop createProxy(){
        return (Aop) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

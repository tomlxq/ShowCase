package com.example.demo.pattern.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyLove implements InvocationHandler {
    Object target;

    public Object getInstance(Person target) {
        this.target=target;
        System.out.println(this.target.getClass());
       return Proxy.newProxyInstance( this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆");
       Object o=method.invoke(target,args);
        System.out.println("我是媒婆，帮找到爱");
       return o;
    }
}

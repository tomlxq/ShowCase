package com.example.demo.proxy.jdk;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TomProxyLove implements TomInvocationHandler {
    Person target;

    public Object getInstance(Person target) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        this.target=target;
        System.out.println(this.target.getClass());
        return TomProxy.newProxyInstance(new TomClassLoader()/* this.target.getClass().getClassLoader()*/, this.target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆");
        Object o=method.invoke(this.target,args);
        System.out.println("我是媒婆，帮找到爱");
        return o;
    }
}

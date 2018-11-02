package com.example.demo.proxy.customer;

import java.lang.reflect.Method;

public interface  TomInvocationHandler {
      Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}

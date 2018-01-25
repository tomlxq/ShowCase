package com.example.demo.FactoryBean与BeanFactory;

/**
 * Created by tom on 2018/1/25.
 */
public class HelloWorldServiceImpl implements HelloWorldService{
    @Override
    public void sayHello() {
        System.out.println("Hello world!s");
    }
}

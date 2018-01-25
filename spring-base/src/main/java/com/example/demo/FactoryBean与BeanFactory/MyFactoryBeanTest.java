package com.example.demo.FactoryBean与BeanFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tom on 2018/1/25.
 */
public class MyFactoryBeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("FactoryBeanConfig.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("fbHelloWorldService");
        helloWorldService.sayHello();
    }
}

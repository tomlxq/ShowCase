package com.example.demo.FactoryBean与BeanFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tom on 2018/1/25.
 */
public class FactoryBeanTest {
    public static void main(String[] args){
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("BeanConfig.xml");
        Object school=  cpxa.getBean("factoryBeanPojo");
        FactoryBeanPojo factoryBeanPojo= (FactoryBeanPojo) cpxa.getBean("&factoryBeanPojo");
        System.out.println(school.getClass().getName());
        System.out.println(factoryBeanPojo.getClass().getName());
    }
}
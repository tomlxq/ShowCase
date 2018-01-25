package com.example.demo.Spring_IoC原理理解;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by tom on 2018/1/25.
 */
public class TestAnimal {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//new FileSystemXmlApplicationContext("applicationContext.xml");

        Animal animal = (Animal) context.getBean("animal");
        animal.say();
    }
}
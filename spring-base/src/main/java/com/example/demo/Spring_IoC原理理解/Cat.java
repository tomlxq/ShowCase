package com.example.demo.Spring_IoC原理理解;

/**
 * Created by tom on 2018/1/25.
 */
public class Cat implements Animal{

    private String name;

    @Override
    public void say() {
        System.out.println("I am " + name + "!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
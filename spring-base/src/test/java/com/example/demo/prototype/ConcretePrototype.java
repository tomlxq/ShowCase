package com.example.demo.prototype;

import org.assertj.core.util.Lists;

import java.util.List;

public class ConcretePrototype extends Prototype {
    List<String> list = Lists.newArrayList();
    private int age;
    private String name;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

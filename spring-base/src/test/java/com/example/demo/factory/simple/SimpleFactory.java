package com.example.demo.factory.simple;

/**
 * 简单工厂：一个工厂做所有的事情，可以造奔驰，宝马，奥迪（感觉很混乱，没有一个标准）
 */
public class SimpleFactory {
    public Car getCar(String name) {
        if ("Audi".equals(name)) {
            return new Audi();
        } else if ("Benz".equals(name)) {
            return new Benz();
        } else if ("Bmw".equals(name)) {
            return new Bmw();
        } else {
            System.out.println("没有这样的产品！");
            return null;
        }
    }
}

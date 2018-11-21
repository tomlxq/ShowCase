package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Car;

public abstract class AbstractFactory {
    protected abstract Car getCar();

    public Car getCar(String name) {
        if ("Audi".equals(name)) {
            return new AudiFactory().getCar();
        } else if ("Benz".equals(name)) {
            return new BenzFactory().getCar();
        } else if ("Bmw".equals(name)) {
            return new BmwFactory().getCar();
        } else {
            System.out.println("没有这样的产品！");
            return null;
        }
    }
}

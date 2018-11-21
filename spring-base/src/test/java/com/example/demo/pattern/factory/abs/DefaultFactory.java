package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Car;

public class DefaultFactory extends AbstractFactory {
    AudiFactory factory = new AudiFactory();

    @Override
    public Car getCar() {
        return factory.getCar();
    }
}

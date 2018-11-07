package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Car;

public class DefaultFactory extends AbstractFactory {
    AudiFactory factory = new AudiFactory();

    @Override
    public Car getCar() {
        return factory.getCar();
    }
}

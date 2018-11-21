package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Audi;
import com.example.demo.pattern.factory.simple.Car;

public class AudiFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

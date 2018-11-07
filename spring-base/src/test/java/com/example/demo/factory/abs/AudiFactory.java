package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Audi;
import com.example.demo.factory.simple.Car;

public class AudiFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

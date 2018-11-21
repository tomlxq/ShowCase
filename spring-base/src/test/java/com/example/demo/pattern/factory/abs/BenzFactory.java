package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Benz;
import com.example.demo.pattern.factory.simple.Car;

public class BenzFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}

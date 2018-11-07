package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Benz;
import com.example.demo.factory.simple.Car;

public class BenzFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}

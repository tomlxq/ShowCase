package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Bmw;
import com.example.demo.pattern.factory.simple.Car;

public class BmwFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}

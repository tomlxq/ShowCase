package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Bmw;
import com.example.demo.factory.simple.Car;

public class BmwFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}

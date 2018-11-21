package com.example.demo.pattern.factory.method;

import com.example.demo.pattern.factory.simple.Bmw;
import com.example.demo.pattern.factory.simple.Car;

public class BmwFactory implements Factory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}

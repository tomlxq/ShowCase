package com.example.demo.pattern.factory.method;

import com.example.demo.pattern.factory.simple.Benz;
import com.example.demo.pattern.factory.simple.Car;

public class BenzFactory implements Factory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}

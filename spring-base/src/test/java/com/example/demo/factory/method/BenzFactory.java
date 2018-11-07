package com.example.demo.factory.method;

import com.example.demo.factory.simple.Benz;
import com.example.demo.factory.simple.Car;

public class BenzFactory implements Factory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}

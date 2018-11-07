package com.example.demo.factory.method;

import com.example.demo.factory.simple.Bmw;
import com.example.demo.factory.simple.Car;

public class BmwFactory implements Factory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}

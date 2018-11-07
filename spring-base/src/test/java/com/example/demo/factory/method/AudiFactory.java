package com.example.demo.factory.method;

import com.example.demo.factory.simple.Audi;
import com.example.demo.factory.simple.Car;

public class AudiFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

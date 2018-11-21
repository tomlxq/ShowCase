package com.example.demo.pattern.factory.method;

import com.example.demo.pattern.factory.simple.Audi;
import com.example.demo.pattern.factory.simple.Car;

public class AudiFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

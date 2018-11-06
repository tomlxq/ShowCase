package com.example.demo.factory.simple;

import org.junit.Test;

public class SimpleFactoryTest {
    @Test
    public void test() {
        Car car = new SimpleFactory().getCar("Audi");
        System.out.println(car.getName());
        car = new SimpleFactory().getCar("Benz");
        System.out.println(car.getName());
    }
}

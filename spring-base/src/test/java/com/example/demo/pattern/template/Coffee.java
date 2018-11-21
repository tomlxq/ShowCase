package com.example.demo.pattern.template;

public class Coffee extends Beverage {
    @Override
    protected void addIngredients() {
        System.out.println("放入白糖");
    }

    @Override
    protected void putBeverage() {
        System.out.println("放入咖啡");
    }
}

package com.example.demo.template;

public class Tea extends Beverage {
    @Override
    protected void addIngredients() {
        System.out.println("放入柠檬");
    }

    @Override
    protected void putBeverage() {
        System.out.println("放入茶叶");
    }
}

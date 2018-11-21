package com.example.demo.pattern.template;

public abstract class Beverage {
    //制作一杯饮料
    public final void create() {
        //烧开水
        boilWater();
        //准备一个杯子放饮料，饮料未知
        putBeverage();
        //冲泡饮料
        brewedBeverage();
        //放一些附料
        addIngredients();
    }

    protected abstract void addIngredients();

    private void brewedBeverage() {
        System.out.println("冲泡饮料");
    }

    protected abstract void putBeverage();

    private void boilWater() {
        System.out.println("烧开水");
    }
}

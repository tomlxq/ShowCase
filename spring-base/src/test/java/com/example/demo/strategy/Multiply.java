package com.example.demo.strategy;

public class Multiply extends Calculator implements ICalculator {
    @Override
    public void calculator() {
        int[] ary = split("10*2", "\\*");
        System.out.println("10*2=" + (ary[0] * ary[1]));
    }
}

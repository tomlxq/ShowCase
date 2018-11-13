package com.example.demo.strategy;

public class Plus extends Calculator implements ICalculator {
    @Override
    public void calculator() {
        int[] ary = split("2+8", "\\+");
        System.out.println("2+8=" + (ary[0] + ary[1]));
    }
}

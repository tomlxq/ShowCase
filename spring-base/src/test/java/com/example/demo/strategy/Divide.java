package com.example.demo.strategy;

public class Divide extends Calculator implements ICalculator {
    @Override
    public void calculator() {
        int[] ary = split("10/8", "/");
        System.out.println("10/8=" + (ary[0] / ary[1]));
    }
}

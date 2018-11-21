package com.example.demo.pattern.strategy;

public class Divide extends Calculator implements ICalculator {
    @Override
    public void calculator(String s) {
        int[] ary = split(s, "/");
        System.out.println(s + "=" + (ary[0] / ary[1]));
    }
}

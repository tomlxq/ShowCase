package com.example.demo.strategy;

public abstract class Calculator {
    int[] split(String exp, String sep) {
        String[] ary = exp.split(sep);
        int[] ret = new int[2];
        ret[0] = Integer.parseInt(ary[0]);
        ret[1] = Integer.parseInt(ary[1]);
        return ret;
    }
}

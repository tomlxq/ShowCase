package com.example.demo.pattern.strategy;

import org.junit.Test;

public class TestStrategy {
    @Test
    public void testCalculator() {
        ICalculator plus = new Plus();
        plus.calculator("2+8");
        ICalculator minus = new Minus();
        minus.calculator("10-8");
        ICalculator multiply = new Multiply();
        multiply.calculator("10*2");
        ICalculator divide = new Divide();
        divide.calculator("10/4");
    }
}

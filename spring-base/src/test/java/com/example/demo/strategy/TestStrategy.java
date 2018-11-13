package com.example.demo.strategy;

import org.junit.Test;

public class TestStrategy {
    @Test
    public void testCalculator() {
        Plus plus = new Plus();
        plus.calculator();
        Minus minus = new Minus();
        minus.calculator();
        Multiply multiply = new Multiply();
        multiply.calculator();
        Divide divide = new Divide();
        divide.calculator();
    }
}

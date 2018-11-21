package com.example.demo.pattern.template;

import org.junit.Test;

public class TestTemplate {
    @Test
    public void testMachine() {
        Coffee c = new Coffee();
        c.create();
        Tea tea = new Tea();
        tea.create();
    }
}

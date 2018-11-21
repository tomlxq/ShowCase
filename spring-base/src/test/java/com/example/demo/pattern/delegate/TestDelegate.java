package com.example.demo.pattern.delegate;

public class TestDelegate {
    public static void main(String[] args) {
        /**
         * 干活是你的，功劳是我的
         */
        Manager manager = new Manager(new ExecutorA());
        manager.doing();
    }

}

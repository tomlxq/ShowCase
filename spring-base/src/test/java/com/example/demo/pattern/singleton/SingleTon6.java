package com.example.demo.pattern.singleton;

public class SingleTon6 {
    private SingleTon6() {
        System.out.println("test SingleTon6 " + System.currentTimeMillis());
    }

    public static final SingleTon6 getInstance() {
        return SingleTonFactory.INSTANCE;
    }

    private static class SingleTonFactory {
        private static final SingleTon6 INSTANCE = new SingleTon6();
    }
}

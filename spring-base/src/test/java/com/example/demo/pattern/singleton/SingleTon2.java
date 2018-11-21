package com.example.demo.pattern.singleton;

public class SingleTon2 {
    private static SingleTon2 instance = null;

    private SingleTon2() {
    }

    /**
     * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，如果我们把它放入多线程的环境下，肯定就会出现问题了，如何解决？
     * 我们首先会想到对getInstance方法加synchronized关键字
     *
     * @return
     */
    public static synchronized SingleTon2 getInstance() {
        if (instance == null) {
            instance = new SingleTon2();
        }
        return instance;
    }

    public Object readResolve() {
        return instance;
    }
}

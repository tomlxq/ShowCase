package com.example.demo.pattern.singleton;

public class TestInstance {

    public static void main(String[] args) {
        SingleTon1.getInstance();
        SingleTon1.getInstance();
        // SingleTon6.getInstance();
        //SingleTon6.getInstance();
        MyThread thread1 = new MyThread("test1");
        MyThread thread2 = new MyThread("test2");
        thread2.start();
        thread1.start();
    }
}

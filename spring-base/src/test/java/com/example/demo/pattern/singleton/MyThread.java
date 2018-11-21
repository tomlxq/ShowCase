package com.example.demo.pattern.singleton;

class MyThread extends Thread {
    MyThread(String s) {
        System.out.println(s);
    }

    @Override
    public void run() {
        System.out.println(SingleTon6.getInstance().hashCode());
    }
}
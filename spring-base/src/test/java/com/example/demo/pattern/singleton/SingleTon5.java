package com.example.demo.pattern.singleton;

public class SingleTon5 {
    protected static SingleTon5 instance = null;

    private SingleTon5() {

    }

    public static SingleTon5 getInstance() {
        if (instance == null) {
            sysInit();
        }
        return instance;
    }

    /**
     * 因为我们只需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，单独为创建加synchronized关键字
     */
    private static synchronized void sysInit() {
        if (instance == null) {
            instance = new SingleTon5();
        }
    }

    public Object readResolve() {
        return instance;
    }
}

package com.example.demo.pattern.singleton;

public class SingleTon3 {
    private static SingleTon3 instance = null;

    private SingleTon3() {
    }

    /**
     * 但是，synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，因为每次调用getInstance()，都要对对象上锁，
     * 事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了，所以，这个地方需要改进。
     *
     * @return
     */
    public static SingleTon3 getInstance() {

        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new SingleTon3();
                }
            }
        }
        return instance;
    }

    public Object readResolve() {
        return instance;
    }
}

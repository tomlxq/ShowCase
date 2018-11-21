package com.example.demo.pattern.singleton;

public class SingleTon4 {
    /**
     * 私有构造方法，防止被实例化
     */
    private SingleTon4() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static SingleTon4 getInstance() {
        return SingleTonFactory.INSTANCE;
    }

    /**
     * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
     *
     * @return
     */
    public Object readResolve() {
        return getInstance();
    }

    /**
     * 此处使用一个内部类来维护单例
     */
    private static class SingleTonFactory {
        private static SingleTon4 INSTANCE = new SingleTon4();
    }
}

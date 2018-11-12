package com.example.demo.singleton;

public class SingleTon1 {
    /**
     * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
     */
    private static SingleTon1 instance = null;

    /**
     * 私有构造方法，防止被实例化
     */
    private SingleTon1() {
    }

    /**
     * 静态工程方法，创建实例
     *
     * @return
     */
    public static SingleTon1 getInstance() {
        if (instance == null) {
            System.out.println("first instance");
            instance = new SingleTon1();
        }
        return instance;
    }

    /**
     * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
     *
     * @return
     */
    public Object readResolve() {
        return instance;
    }
}

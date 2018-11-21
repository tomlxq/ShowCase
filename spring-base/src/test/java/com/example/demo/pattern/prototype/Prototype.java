package com.example.demo.pattern.prototype;

/**
 * 过程不同，但结果相同
 */
public class Prototype implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

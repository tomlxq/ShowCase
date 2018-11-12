package com.example.demo.prototype;

public class Prototype implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

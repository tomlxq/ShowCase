package com.example.demo.prototype;

import java.io.Serializable;

public class GoldRingedStaff implements Serializable {
    private float height = 100;
    private float diameter = 10;

    public void growth() {
        this.height *= 2;
        this.diameter *= 2;
    }

    public void shrink() {
        this.height /= 2;
        this.diameter /= 2;
    }
}



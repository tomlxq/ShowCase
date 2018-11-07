package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Sender;

public interface Provider {
    Sender produce();
}
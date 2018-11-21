package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Sender;

public interface Provider {
    Sender produce();
}
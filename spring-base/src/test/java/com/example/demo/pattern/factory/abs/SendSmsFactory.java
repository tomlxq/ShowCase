package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.Sender;
import com.example.demo.pattern.factory.simple.SmsSender;

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
package com.example.demo.factory.abs;

import com.example.demo.factory.simple.Sender;
import com.example.demo.factory.simple.SmsSender;

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
package com.example.demo.pattern.factory.method;

import com.example.demo.pattern.factory.simple.MailSender;
import com.example.demo.pattern.factory.simple.Sender;
import com.example.demo.pattern.factory.simple.SmsSender;

public class SendFactory2 {
    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}


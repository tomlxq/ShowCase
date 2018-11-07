package com.example.demo.factory.method;

import com.example.demo.factory.simple.MailSender;
import com.example.demo.factory.simple.Sender;
import com.example.demo.factory.simple.SmsSender;

public class SendFactory2 {
    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}


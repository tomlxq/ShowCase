package com.example.demo.pattern.factory.abs;

import com.example.demo.pattern.factory.simple.MailSender;
import com.example.demo.pattern.factory.simple.Sender;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
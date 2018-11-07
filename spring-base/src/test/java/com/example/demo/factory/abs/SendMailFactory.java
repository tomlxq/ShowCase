package com.example.demo.factory.abs;

import com.example.demo.factory.simple.MailSender;
import com.example.demo.factory.simple.Sender;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
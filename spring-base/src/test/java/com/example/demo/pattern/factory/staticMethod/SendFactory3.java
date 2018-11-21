package com.example.demo.pattern.factory.staticMethod;

import com.example.demo.pattern.factory.simple.MailSender;
import com.example.demo.pattern.factory.simple.Sender;
import com.example.demo.pattern.factory.simple.SmsSender;

public class SendFactory3 {
    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}

package com.example.demo.pattern.factory;

import com.example.demo.pattern.factory.abs.DefaultFactory;
import com.example.demo.pattern.factory.abs.Provider;
import com.example.demo.pattern.factory.abs.SendMailFactory;
import com.example.demo.pattern.factory.method.*;
import com.example.demo.pattern.factory.simple.Car;
import com.example.demo.pattern.factory.simple.SendFactory;
import com.example.demo.pattern.factory.simple.Sender;
import com.example.demo.pattern.factory.simple.SimpleFactory;
import com.example.demo.pattern.factory.staticMethod.SendFactory3;
import org.junit.Test;

public class FactoryTest {
    @Test
    public void test() {
        /**
         * 简单工厂：一个工厂做所有的事情，可以造奔驰，宝马，奥迪（感觉很混乱，没有一个标准）
         */
        SimpleFactory simpleFactory = new SimpleFactory();
        Car car = simpleFactory.getCar("Audi");
        System.out.println(car.getName());
        car = simpleFactory.getCar("Benz");
        System.out.println(car.getName());
        car = simpleFactory.getCar("Bmw");
        System.out.println(car.getName());

        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("sms");
        sender.Send();


        Sender sender3 = SendFactory3.produceMail();
        sender3.Send();
        /**
         * 工厂方法：定义一个造车工厂接口，奔驰工厂、宝马工厂、奥迪工厂实现造车接口（需要用户去关心使用哪个工厂，使用哪个工厂都需要手动去new）
         */
        Factory factory = new AudiFactory();
        System.out.println(factory.getCar().getName());
        factory = new BenzFactory();
        System.out.println(factory.getCar().getName());
        factory = new BmwFactory();
        System.out.println(factory.getCar().getName());

        SendFactory2 factory2 = new SendFactory2();
        Sender sender2 = factory2.produceMail();
        sender2.Send();


        /**
         * 抽象工厂：实现了解耦
         */
        DefaultFactory factory1 = new DefaultFactory();
        System.out.println(factory1.getCar().getName());
        System.out.println(factory1.getCar("Benz").getName());
        System.out.println(factory1.getCar("Bmw").getName());


        Provider provider = new SendMailFactory();
        Sender sender4 = provider.produce();
        sender4.Send();
    }
}

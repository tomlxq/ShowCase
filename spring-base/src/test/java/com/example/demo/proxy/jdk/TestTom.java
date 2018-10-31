package com.example.demo.proxy.jdk;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestTom {
    @Test
    public  void testTom() throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
       Person p=(Person) new ProxyLove().getInstance(new Tom());
        System.out.println(p.getClass());
        p.findLove();
       byte[] data= ProxyGenerator.generateProxyClass("$Proxy",new Class[]{Person.class});
       FileUtils.writeByteArrayToFile(new File("./$proxy.class"),data);


        Person p2=(Person) new TomProxyLove().getInstance(new Tom());
        System.out.println(p2.getClass());
        p2.findLove();

    }
}

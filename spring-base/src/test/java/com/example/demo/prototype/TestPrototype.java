package com.example.demo.prototype;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class TestPrototype {
    Logger logger = LoggerFactory.getLogger(TestPrototype.class);

    @Test
    public void test() throws CloneNotSupportedException {
        ConcretePrototype cp = new ConcretePrototype();
        cp.setAge(18);
        cp.setName("Tom");
        cp.setList(Arrays.asList("jack"));
        ConcretePrototype cp3 = cp;
        ConcretePrototype cp2 = (ConcretePrototype) cp.clone();
        logger.info("" +
                "{} {} {} {}", cp3.hashCode(), cp.hashCode(), JSON.toJSONString(cp2), cp2.hashCode());


        TheGreatestSage theGreatestSage = new TheGreatestSage();
        theGreatestSage.change();
    }
}

package com.example.demo;

import com.example.demo.FactoryBean与BeanFactory.HelloWorldService;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by tom on 2018/1/25.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath*:/FactoryBeanConfig.xml")//@ContextConfiguration(classes = { MyFactoryBeanConfig.class })
public class MyFactoryBeanTest {

    @Autowired
    private ApplicationContext context;

    /**
     * 测试验证FactoryBean原理，代理一个servcie在调用其方法的前后，打印日志亦可作其他处理
     * 从ApplicationContext中获取自定义的FactoryBean
     * context.getBean(String beanName) ---> 最终获取到的Object是FactoryBean.getObejct(),
     * 使用Proxy.newInstance生成service的代理类
     */
    @Test
    public void testFactoryBean() {
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("fbHelloWorldService");
        //helloWorldService.getBeanName();
        helloWorldService.sayHello();
    }
}
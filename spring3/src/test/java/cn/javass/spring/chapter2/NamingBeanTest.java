package cn.javass.spring.chapter2;

import cn.javass.spring.chapter2.helloworld.HelloApi;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
从定义来看，name或id如果指定它们中的一个时都作为“标识符”，那为什么还要有id和name同时存在呢？这是因为当使用基于XML的配置元数据时，在XML中id是一个真正的XML id属性，因此当其他的定义来引用这个id时就体现出id的好处了，可以利用XML解析器来验证引用的这个id是否存在，从而更早的发现是否引用了一个不存在的bean，而使用name，则可能要在真正使用bean时才能发现引用一个不存在的bean。
●Bean命名约定：Bean的命名遵循XML命名规范，但最好符合Java命名规范，由“字母、数字、下划线组成“，而且应该养成一个良好的命名习惯， 比如采用“驼峰式”，即第一个单词首字母开始，从第二个单词开始首字母大写开始，这样可以增加可读性。
*/


public class NamingBeanTest {
    
    @Test
    public void test1() {
        //不指定id，只配置必须的全限定类名，由IoC容器为其生成一个标识，客户端必须通过接口“T getBean(Class<T> requiredType)”获取Bean；
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean1.xml");
        //根据类型获取bean
        HelloApi helloApi = beanFactory.getBean(HelloApi.class);
        helloApi.sayHello();
    }

    @Test
    public void test2() {
        //指定id，必须在Ioc容器中唯一；
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean2.xml");
        //根据id获取bean
        HelloApi bean = beanFactory.getBean("bean", HelloApi.class);
        bean.sayHello();
    }

    @Test
    public void test3() {
        //指定name，这样name就是“标识符”，必须在Ioc容器中唯一；
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean3.xml");
        //根据name获取bean
        HelloApi bean = beanFactory.getBean("bean", HelloApi.class);
        bean.sayHello();
    }

    @Test
    public void test4() {
        //指定id和name，id就是标识符，而name就是别名，必须在Ioc容器中唯一；
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean4.xml");
        //根据id获取bean
        HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);
        bean1.sayHello();
        //根据别名获取bean
        HelloApi bean2 = beanFactory.getBean("alias1", HelloApi.class);
        bean2.sayHello();
        //根据id获取bean
        HelloApi bean3 = beanFactory.getBean("bean3", HelloApi.class);
        bean3.sayHello();
        
        String[] bean3Alias = beanFactory.getAliases("bean3");
        //因此别名不能和id一样，如果一样则由IoC容器负责消除冲突
        Assert.assertEquals(0, bean3Alias.length);

    }
    
    @Test
    public void test5() {
        //指定多个name，多个name用“，”、“；”、“ ”分割，第一个被用作标识符，其他的（alias1、alias2、alias3）是别名，所有标识符也必须在Ioc容器中唯一；
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean5.xml");
        //根据id获取bean
        HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);
        bean1.sayHello();
        //2根据别名获取bean
        HelloApi alias11 = beanFactory.getBean("alias11", HelloApi.class);
        alias11.sayHello();
        //验证确实是四个别名        
        String[] bean1Alias = beanFactory.getAliases("bean1");
        System.out.println("=======namingbean5.xml bean1 别名========");
        for(String alias : bean1Alias) {
            System.out.println(alias);
        }
        System.out.println("=======namingbean5.xml bean1 别名========");
        Assert.assertEquals(4, bean1Alias.length);

        //根据id获取bean
        HelloApi bean2 = beanFactory.getBean("bean2", HelloApi.class);
        bean2.sayHello();
        ////2根据别名获取bean
        HelloApi alias21 = beanFactory.getBean("alias21", HelloApi.class);
        alias21.sayHello();
        //验证确实是两个别名
        String[] bean2Alias = beanFactory.getAliases("bean2");
        System.out.println("=======namingbean5.xml bean2 别名========");
        for(String alias : bean2Alias) {
            System.out.println(alias);
        }
        System.out.println("=======namingbean5.xml bean2 别名========");
        Assert.assertEquals(2, bean2Alias.length);
        
    }
    
    @Test
    public void test6() {
        //使用<alias>标签指定别名，别名也必须在IoC容器中唯一
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/namingbean6.xml");
        //根据id获取bean
        HelloApi bean = beanFactory.getBean("bean", HelloApi.class);
        bean.sayHello();

        //根据别名获取bean
        HelloApi alias1 = beanFactory.getBean("alias1", HelloApi.class);
        alias1.sayHello();
        HelloApi alias2 = beanFactory.getBean("alias2", HelloApi.class);
        alias2.sayHello();
        
        String[] beanAlias = beanFactory.getAliases("bean");
        System.out.println("=======namingbean6.xml bean 别名========");
        for(String alias : beanAlias) {
            System.out.println(alias);
        }
        System.out.println("=======namingbean6.xml bean 别名========");
        Assert.assertEquals(2, beanAlias.length);

    }
    
}

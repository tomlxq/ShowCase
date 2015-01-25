package cn.javass.spring.chapter3;

import cn.javass.spring.chapter2.helloworld.HelloApi;
import cn.javass.spring.chapter3.bean.NavigationA;
import cn.javass.spring.chapter3.bean.NavigationC;
import cn.javass.spring.chapter3.bean.SetTestBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DependencyInjectTest {

    @Test
    public void ConstructorDependencyInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/constructorDependencyInject.xml");

        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();

        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();

        //获取根据参数名字依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();

    }

    @Test
    public void staticFactoryDependencyInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/staticFactoryDependencyInject.xml");

        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();

        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();

        //获取根据参数名字依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();

    }

    @Test
    public void instanceFactoryDependencyInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/instanceFactoryDependencyInject.xml");

        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();

        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();

        //获取根据参数名字依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();

    }

    @Test
    public void setterDependencyInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/setterDependencyInject.xml");

        HelloApi bean = beanFactory.getBean("bean", HelloApi.class);
        bean.sayHello();
    }
//=====================依赖注入扩展部分=================================

    //注入Boolean值
    @Test
    public void booleanInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/booleanInject.xml");

        BooleanTestBean bean1 = beanFactory.getBean("bean1", BooleanTestBean.class);
        System.out.println(bean1.isSuccess());

        BooleanTestBean bean2 = beanFactory.getBean("bean2", BooleanTestBean.class);
        System.out.println(bean2.isSuccess());

        BooleanTestBean bean3 = beanFactory.getBean("bean3", BooleanTestBean.class);
        System.out.println(bean3.isSuccess());

        BooleanTestBean bean4 = beanFactory.getBean("bean4", BooleanTestBean.class);
        System.out.println(bean4.isSuccess());
    }

    @Test
    public void testCollectionInject() {
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("chapter3/listInject.xml");
        SetTestBean listBean = beanFactory.getBean("setBean", SetTestBean.class);
        System.out.println(listBean.getValues().size());
        Assert.assertEquals(3, listBean.getValues().size());
    }

    @Test
    public void testBeanInject() {
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("chapter3/beanInject.xml");
//通过构造器方式注入
        HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);
        bean1.sayHello();
//通过setter方式注入
        HelloApi bean2 = beanFactory.getBean("bean2", HelloApi.class);
        bean2.sayHello();
    }

@Test
public void testLocalAndparentBeanInject() {
//初始化父容器
ApplicationContext parentBeanContext =
new ClassPathXmlApplicationContext("chapter3/parentBeanInject.xml");
//初始化当前容器
ApplicationContext beanContext = new ClassPathXmlApplicationContext(
new String[] {"chapter3/localBeanInject.xml"}, parentBeanContext);
HelloApi bean1 = beanContext.getBean("bean1", HelloApi.class);
 bean1.sayHello();//该Bean引用local bean
 HelloApi bean2 = beanContext.getBean("bean2", HelloApi.class);
 bean2.sayHello();//该Bean引用parent bean
 }

@Test
public void testInnerBeanInject() {
ApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/innerBeanInject.xml");
HelloApi bean = context.getBean("bean", HelloApi.class);
bean.sayHello();
}

    //对象图导航
@Test
public void testNavigationBeanInject() {
ApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/navigationBeanInject.xml");
NavigationA navigationA = context.getBean("a", NavigationA.class);
navigationA.getNavigationB().getNavigationC().sayNavigation();
navigationA.getNavigationB().getList().get(0).sayNavigation();
navigationA.getNavigationB().getMap().get("key").sayNavigation();
 navigationA.getNavigationB().getArray()[0].sayNavigation();
 ((NavigationC)navigationA.getNavigationB().getProperties().get("1"))
 .sayNavigation();
 }
}

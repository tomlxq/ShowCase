package cn.javass.spring.chapter3;

import cn.javass.spring.chapter3.bean.ListTestBean;
import cn.javass.spring.chapter3.bean.SetTestBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/19  20:39
 */
public class ListInjectTest {
    @Test
    public void testListInject() {
       BeanFactory beanFactory =
          new ClassPathXmlApplicationContext("chapter3/listInject.xml");
    ListTestBean listBean = beanFactory.getBean("listBean", ListTestBean.class);
    System.out.println(listBean.getValues().size());
    Assert.assertEquals(3, listBean.getValues().size());
    }
    @Test
    public void testSetInject() {
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("chapter3/listInject.xml");
        SetTestBean listBean = beanFactory.getBean("setBean", SetTestBean.class);
        System.out.println(listBean.getValues().size());
        Assert.assertEquals(3, listBean.getValues().size());
    }
}

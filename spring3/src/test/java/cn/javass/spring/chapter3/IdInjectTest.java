package cn.javass.spring.chapter3;

import cn.javass.spring.chapter3.bean.IdRefTestBean;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/19  20:31
 */
public class IdInjectTest {
    public static Logger logger = Logger.getLogger(IdInjectTest.class);
    @Test
    public void setterDependencyInjectTest() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/idRefInject.xml");

        IdRefTestBean bean = beanFactory.getBean("idrefBean1", IdRefTestBean.class);
        logger.debug("idrefBean1 : "+bean.getId());
         bean = beanFactory.getBean("idrefBean2", IdRefTestBean.class);
        logger.debug("idrefBean2 : "+bean.getId()); ;
    }
}

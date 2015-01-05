package com.greentea.multilang;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  10:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring-common.xml","classpath:/spring-servlet.xml" }) //特别注入加载顺序
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)  //记得要在XML文件中声明事务哦~~~我是采用注解的方式
@Transactional
public class TestBase extends  AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    protected WebApplicationContext wac;
    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected MockMvc mockMvc;
    //protected ServletContext servletContext;
    @Override
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
    @Before
    public void setup() {
        // webAppContextSetup 注意上面的static import
        // webAppContextSetup 构造的WEB容器可以添加fileter 但是不能添加listenCLASS
        // WebApplicationContext context =
        // ContextLoader.getCurrentWebApplicationContext();
        // 如果控制器包含如上方法 则会报空指针
        //this.servletContext=this.wac.getServletContext();
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
}

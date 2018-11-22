package com.example.demo.aspect.transaction;


import com.example.demo.TransactionAppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TransactionAppConfig.class})
public class TransactionProxyTest {

    private static Logger logger = LoggerFactory.getLogger(TransactionProxyTest.class);
    // @Autowired
    //AccountServiceInterface accountService;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void noTransaction() {

        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml");
        AccountServiceNoImpl accountService = applicationContext.getBean(AccountServiceNoImpl.class);
        logger.info("noTransaction {}", accountService.getClass().getName());
        //applicationContext.close();
    }

    @Test
    public void transactionCGLIB() {
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
        AccountServiceNoImpl accountService = applicationContext.getBean(AccountServiceNoImpl.class);
        String accountServiceClassName = accountService.getClass().getName();
        logger.info("transactionCGLIB {}", accountServiceClassName);
        //com.example.demo.aspect.transaction.AccountServiceNoImpl$$EnhancerBySpringCGLIB$$30b8e04a
        Assert.assertTrue(accountServiceClassName.contains("EnhancerBySpringCGLIB"));
        // applicationContext.close();
    }

    @Test
    public void transactionDynamicProxy() {
        //  ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
        AccountServiceInterface accountService = applicationContext.getBean(AccountServiceInterface.class);
        String accountServiceClassName = accountService.getClass().getName();
        logger.info("transactionDynamicProxy {}", accountServiceClassName);
        Assert.assertTrue(accountServiceClassName.contains("$Proxy"));
        //  applicationContext.close();
    }

    @Test
    public void accountCreationDynamicProxy() {
        // ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
        AccountServiceInterface accountService = applicationContext.getBean(AccountServiceInterface.class);
        Account account = new Account();
        account.setId(10);
        account.setCashBalance(500);
        accountService.create(account);
        //  applicationContext.close();
    }

}
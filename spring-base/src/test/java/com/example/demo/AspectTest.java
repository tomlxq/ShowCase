package com.example.demo;

import com.example.demo.aspect.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AspectTest {
    @Autowired
    public MemberService memberService;
    Logger logger = LoggerFactory.getLogger(AspectTest.class);

    @Test
    public void testAdd() throws Exception {
        String name = memberService.addMember();
        logger.debug("{}", name);
    }
}

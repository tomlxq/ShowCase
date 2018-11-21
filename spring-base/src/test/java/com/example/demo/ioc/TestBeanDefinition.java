package com.example.demo.ioc;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBeanDefinition {
    Logger logger = LoggerFactory.getLogger(TestBeanDefinition.class);
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    @Test
    public void test() {
        beanDefinitionNames.addAll(Arrays.asList(StringUtils.tokenizeToStringArray("zhangsan,lishi,wangwu", ",")));
        logger.debug("{}", JSON.toJSONString(beanDefinitionNames));
        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
        beanNames.addAll(Arrays.asList(StringUtils.tokenizeToStringArray("jack,rose", ",")));
        logger.debug("{}", JSON.toJSONString(beanNames));
    }
}

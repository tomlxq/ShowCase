package com.tom.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/9  20:32
 */
@Configuration
@ComponentScan(basePackages = "com.tom.mvc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
})
//AppConfig.java：等价于spring-config.xml
public class AppConfig {
}

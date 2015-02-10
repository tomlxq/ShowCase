package com.tom.mvc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/10  20:50
 */
public abstract class AbstractClientTest {

    static RestTemplate restTemplate;
    ObjectMapper objectMapper;//JSON
    Jaxb2Marshaller marshaller;//XML
    String baseUri = "http://localhost:8080/users";

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper(); //需要添加jackson jar包

        marshaller = new Jaxb2Marshaller(); //需要添加jaxb2实现（如xstream）
        marshaller.setPackagesToScan(new String[]{"com.tom"});
        marshaller.afterPropertiesSet();

        restTemplate = new RestTemplate();
    }
}
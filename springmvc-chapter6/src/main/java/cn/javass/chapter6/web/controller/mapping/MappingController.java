package cn.javass.chapter6.web.controller.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * URL请求映射 
 *
 */
@Controller
public class MappingController {
    
    //普通URL路径映射
    //如 @RequestMapping(value={"/test1", "/user/create"}) 组合使用是或的关系，
    // 即“/test1”或“/user/create”请求URL路径都可以映射到@RequestMapping指定的功能处理方法。
    @RequestMapping(value={"/test1", "/user/create"}) //多个URL路径可以映射到同一个处理器的功能处理方法
    public String test11() {
        System.out.println("/test1  or /user/create  matches");
        return "success";
    }
    //普通URL路径映射
    //{×××}占位符， 请求的URL可以是 “/users/123456”或“/users/abcd”，通过6.6.5讲的通过@PathVariable可以提取URI模板模式中的{×××}中的×××变量。
    @RequestMapping(value="/users/{userId}/topics/{topicId}") //这样也是可以的，请求的URL可以是“/users/123/topics/123”。
    public String test12() {
        System.out.println("/users/{userId}/topics/{topicId} matches");
        return "success";
    }
    
    //URI模板模式映射
    @RequestMapping(value="/users/{userId}")
    public String test21() {
        System.out.println("/users/{userId}  matches");
        return "success";
    }
    
    //URI模板模式映射
    @RequestMapping(value="/users/{userId}/create")  //这样也是可以的，请求的URL可以是“/users/123/create”
    public String test22() {
        System.out.println("/users/{userId}/create  matches");
        return "success";
    }
    
    //Ant风格的URL路径映射
    @RequestMapping(value="/users/**")  //可以匹配“/users/abc/abc”，但“/users/123”将会被【URI模板模式映射中的“/users/{userId}”模式优先映射到】
    public String test31() {
        System.out.println("/users/**  matches");
        return "success";
    }
    
    //Ant风格的URL路径映射
    @RequestMapping(value="/product?") //可匹配“/product1”或“/producta”，但不匹配“/product”或“/productaa”;
    public String test32() {
        System.out.println("/product?  matches");
        return "success";
    }
    
    //Ant风格的URL路径映射
    @RequestMapping(value="/product*") //可匹配“/productabc”或“/product”，但不匹配“/productabc/abc”;
    public String test33() {
        System.out.println("/product*  matches");
        return "success";
    }
    //Ant风格的URL路径映射
    @RequestMapping(value="/product/*") //可匹配“/product/abc”，但不匹配“/productabc”;
    public String test34() {
        System.out.println("/product/*  matches");
        return "success";
    }
    
    @RequestMapping(value="/products/**/{productId}") //可匹配“/products/abc/abc/123”或“/products/123”，也就是Ant风格和URI模板变量风格可混用;
    public String test35() {
        System.out.println("/products/**/{productId}  matches");
        return "success";
    }
    //从Spring3.0开始支持正则表达式风格的URL路径映射，格式为{变量名:正则表达式}，这样我们就可以通过6.6.5讲的通过@PathVariable提取模式中的{×××：正则表达式匹配的值}中的×××变量了
    //正则表达式风格的URL路径映射是一种特殊的URI模板模式映射：
    //URI模板模式映射是{userId}，不能指定模板变量的数据类型，如是数字还是字符串；
    //正则表达式风格的URL路径映射，可以指定模板变量的数据类型，可以将规则写的相当复杂。
    @RequestMapping(value="/products/{categoryCode:\\d+}-{pageNumber:\\d+}") //可以匹配“/products/123-1”，但不能匹配“/products/abc-1”，这样可以设计更加严格的规则。
    public String test41() {
        System.out.println("/products/{categoryCode:\\d+}-{pageNumber:\\d+}  matches");
        return "mappingSuccess";
    }
    
    
}

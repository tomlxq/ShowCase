package cn.javass.chapter6.web.controller.consumesproduces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*消费的数据，如JSON数据、XML数据都是由我们读取请求的InputStream并根据需要自己转换为相应的模型数据，比较麻烦；
        生产的数据，如JSON数据、XML数据都是由我们自己先把模型数据转换为json/xml等数据，然后输出响应流，也是比较麻烦的。

        Spring提供了一组注解（@RequestBody、@ResponseBody）和一组转换类（HttpMessageConverter）来完成我们遇到的问题*/
@Controller
@RequestMapping(value="/narrow", produces="text/html")     //①类级别的映射
public class NarrowController {

//只有生产者/消费者 模式 是 覆盖，其他的使用方法是继承，如headers、params等都是继承
    @RequestMapping(produces="application/xml") //②此时方法级别的映射将覆盖类级别的 而不是继承
    public String test1() {
        System.out.println("application/xml");
        return "success";
    }
    
    
    @RequestMapping(produces={"text/html", "application/json"}) //组合使用是或的关系  将匹配“Accept:text/html”或“Accept:application/json”。
    public String test2() {
        System.out.println("text/html or application/json");
        return "success";
    }
    
    @RequestMapping(produces={"text/*"}) 
    public String test3() {
        System.out.println("text/*");
        return "success";
    }
    @RequestMapping(produces={"*/*"}) 
    public String test4() {
        System.out.println("*/*");
        return "success";
    }
    
    
    
}

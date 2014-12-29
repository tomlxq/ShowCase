package cn.javass.chapter6.web.controller.method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
提示：

        1、一般浏览器只支持GET、POST请求方法，如想浏览器支持PUT、DELETE等请求方法只能模拟，稍候章节介绍。

        2、除了GET、POST，还有HEAD、OPTIONS、PUT、DELETE、TRACE。

        3、DispatcherServlet默认开启对 GET、POST、PUT、DELETE、HEAD的支持；

        4、如果需要支持OPTIONS、TRACE，请添加DispatcherServlet在web.xml的初始化参数：dispatchOptionsRequest 和 dispatchTraceRequest 为true。
*/

@Controller
@RequestMapping("/customers/**")                                  // ①处理器的通用映射前缀（父路径）：表示该处理器只处理匹配“/customers/**”的请求；
public class RequestMethodController {
    @RequestMapping(value="/create", method = RequestMethod.GET)  // ②对类级别的@RequestMapping进行窄化，表示showForm可处理匹配“/customers/**/create”且请求方法为“GET”的请求；
    public String showForm() {
        System.out.println("===============GET");
        return "customer/create";        
    }
    @RequestMapping(value="/create", method = RequestMethod.POST) //③对类级别的@RequestMapping进行窄化，表示submit可处理匹配“/customers/**/create”且请求方法为“POST”的请求。
    public String submit() {
        System.out.println("================POST");
        return "redirect:/success";        
    }
    
    @RequestMapping(value="/methodOr", method = {RequestMethod.POST, RequestMethod.GET})  //即请求方法可以是 GET 或 POST。
    public String or() {
        System.out.println("================GET or POST");
        return "redirect:/success";        
    }
}

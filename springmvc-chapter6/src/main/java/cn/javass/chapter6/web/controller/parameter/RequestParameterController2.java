package cn.javass.chapter6.web.controller.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parameter2")                      //①处理器的通用映射前缀
public class RequestParameterController2 {

    @RequestMapping(params="submitFlag=create", method=RequestMethod.GET)  //②表示请求中有“submitFlag=create”请求参数且请求方法为“GET”即可匹配，如请求URL为http://×××/parameter2?submitFlag=create；
    public String showForm() {
        System.out.println("===============showForm");
        return "parameter/create";        
    }
    @RequestMapping(params="submitFlag=create", method=RequestMethod.POST)   //③表示请求中有“submitFlag=create”请求参数且请求方法为“POST”即可匹配；
    public String submit() {
        System.out.println("===============submit");
        return "redirect:/success";        
    }
    
    
    //请求参数submitFlag 不等于 create
    @RequestMapping(params="submitFlag!=create", method=RequestMethod.GET)  //表示请求中的参数“submitFlag!=create”且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?submitFlag=abc”。
    public String error() {
        System.out.println("================error");
        return "redirect:/success";        
    }
    
    
    
    
}

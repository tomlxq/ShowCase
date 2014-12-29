package cn.javass.chapter6.web.controller.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parameter1")                                  //①处理器的通用映射前缀
public class RequestParameterController1 {

    @RequestMapping(params="create", method=RequestMethod.GET)  //表示请求中有“create”的参数名且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?create”；
    public String showForm() {
        System.out.println("===============showForm");
        return "parameter/create";        
    }
    @RequestMapping(params="create", method=RequestMethod.POST)  //③进行类级别的@RequestMapping窄化 表示请求中有“create”的参数名且请求方法为“POST”即可匹配；
    public String submit() {
        System.out.println("================submit");
        return "redirect:/success";        
    }
    
    //请求参数不包含 create参数名
    @RequestMapping(params="!create", method=RequestMethod.GET)  //表示请求中没有“create”参数名且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?abc”。
    public String error() {
        System.out.println("================error");
        return "redirect:/success";        
    }
}

package cn.javass.chapter6.web.controller.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parameter3")                      //①处理器的通用映射前缀
public class RequestParameterController3 {

    @RequestMapping(params={"test1", "test2=create"})  //②表示请求中的有“test1”参数名 且 有“test2=create”参数即可匹配，如可匹配的请求URL“http://×××/parameter3?test1&test2=create。
    public String test() {
        System.out.println("===============test1 and test2=create");
        return "parameter/create";        
    }
}

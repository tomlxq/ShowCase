package cn.javass.chapter6.web.controller.paramtype;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/method/param/annotation")
public class CookieValueTypeController {
    //@CookieValue用于将请求的Cookie数据映射到功能处理方法的参数上。
    @RequestMapping(value="/cookie")
    public String test(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
        //如上配置将自动将JSESSIONID值入参到sessionId参数上，defaultValue表示Cookie中没有JSESSIONID时默认为空。
        System.out.println(sessionId);
        
        return "success";
    }
    
    @RequestMapping(value="/cookie2")
    //传入参数类型也可以是javax.servlet.http.Cookie类型。
    //测试代码在CookieValueTypeController中。@CookieValue也拥有和@RequestParam相同的三个参数，含义一样。

    public String test2(@CookieValue(value="JSESSIONID", defaultValue="") Cookie sessionId) {
        
        System.out.println(sessionId.getName());
        
        return "success";
    }


}

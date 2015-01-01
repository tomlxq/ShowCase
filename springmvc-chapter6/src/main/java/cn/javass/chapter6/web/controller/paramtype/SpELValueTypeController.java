package cn.javass.chapter6.web.controller.paramtype;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/method/param/annotation")
public class SpELValueTypeController {
    
    @RequestMapping(value="/value")
    public String test(@Value("#{systemProperties['java.vm.version']}") String jvmVersion) {
        //@Value用于将一个SpEL表达式结果映射到到功能处理方法的参数上。
        System.out.println(jvmVersion);
        
        return "success";
    }


}

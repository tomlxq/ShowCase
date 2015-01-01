package cn.javass.chapter6.web.controller.paramtype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/method/param/annotation")
public class PathVariableTypeController {
    //@PathVariable绑定URI模板变量值
    //@PathVariable用于将请求URL中的模板变量映射到功能处理方法的参数上
    //如请求的URL为“控制器URL/users/123/topics/456”，则自动将URL中模板变量{userId}和{topicId}绑定到通过@PathVariable注解的同名参数上，即入参后userId=123、topicId=456
    @RequestMapping(value="/users/{userId}/topics/{topicId}")
    public String test(
            @PathVariable(value="userId") int userId, 
            @PathVariable(value="topicId") int topicId) {
        
        System.out.println(userId + ", " + topicId);
        
        return "success";
    }

}

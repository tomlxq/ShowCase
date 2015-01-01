package cn.javass.chapter6.web.controller.paramtype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/method/param/annotation")
public class RequestParamController {

    @RequestMapping("/requestparam1")
    public String requestparam1(@RequestParam String username) {//@RequestParam用于将请求参数区数据映射到功能处理方法的参数上。
        System.out.println(username);
        return "success";
    }
    //即通过@RequestParam("username")明确告诉Spring Web MVC使用username进行入参。
    @RequestMapping("/requestparam2")
    public String requestparam2(@RequestParam("username") String username) {
        System.out.println(username);
        return "success";
    }
    
    @RequestMapping("/requestparam3")
    public String requestparam3(@RequestParam(value="username", required=true) String username) {
        System.out.println(username);
        return "success";
    }
    //@RequestParam注解主要有哪些参数：
    //value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；

    //required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；

    //defaultValue：默认值，表示如果请求中没有同名参数时的默认值，默认值可以是SpEL表达式，如“#{systemProperties['java.vm.version']}”。


    @RequestMapping("/requestparam4")
    public String requestparam4(@RequestParam(value="username", required=false) String username) {
        //表示请求中可以没有名字为username的参数，如果没有默认为null，此处需要注意如下几点：
        //原子类型：必须有值，否则抛出异常，如果允许空值请使用包装类代替。
        //Boolean包装类型类型：默认Boolean.FALSE，其他引用类型默认为null。
        System.out.println(username);
        return "success";
    }
    
    @RequestMapping("/requestparam5")
    public String requestparam5(@RequestParam(value="username", required=true, defaultValue="zhang") String username) {
        //表示如果请求中没有名字为username的参数，默认值为“zhang”。
        System.out.println(username);
        return "success";
    }
    
    @RequestMapping("/requestparam51")
    public String requestparam51(@RequestParam(value="username", required=true, defaultValue="#{systemProperties['java.vm.version']}") String username) {
        System.out.println(username);
        return "success";
    }
    
    @RequestMapping("/requestparam6")
    public String requestparam6(@RequestParam(value="role") String roleList) {
        //如果请求中有多个同名的应该如何接收呢？如给用户授权时，可能授予多个权限
        System.out.println(roleList);
        return "success";
    }
    
    @RequestMapping("/requestparam7")
    public String requestparam7(@RequestParam(value="role") String[] roleList) {
        //如果请求参数类似于url?role=admin&rule=user，则实际roleList参数入参的数据为“admin,user”，即多个数据之间使用“，”分割
        System.out.println(Arrays.toString(roleList));
        return "success";
    }
    
    @RequestMapping("/requestparam8")
    public String requestparam8(@RequestParam(value="list") List<String> list) {
        System.out.println(list);
        return "success";
    }
    
    
    @RequestMapping("/requestparam9")
    public String requestparam9(@RequestParam(value="list") List<String> list) {
        System.out.println(list);
        return "success";
    }
    
}

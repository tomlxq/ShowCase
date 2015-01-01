package cn.javass.chapter6.web.controller.header;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*浏览器：建议chrome最新版本；
        插件：ModHeader
        安装地址：https://chrome.google.com/webstore/detail/idgpnmonknjnojddfkpgkljpfnnfcklj

        插件安装步骤：
        1、打开https://chrome.google.com/webstore/detail/idgpnmonknjnojddfkpgkljpfnnfcklj*/
@Controller
public class RequestHeaderController {

    @RequestMapping(value="/header/test1", headers = "Accept") //表示请求的URL必须为“/header/test1” 且 请求头中必须有Accept参数才能匹配。

    public String test11() {
        System.out.println("=========Accept");
        return "success";
    }


    @RequestMapping(value="/header/test1", headers = "abc") //表示请求的URL必须为“/header/test1”且 请求头中必须有abc参数才能匹配

    public String test12() {
        System.out.println("=========abc");
        return "success";
    }
    
    @RequestMapping(value="/header/test2", headers = "!abc")  //表示请求的URL必须为“/header/test2”且 请求头中必须没有abc参数才能匹配。（将Modify Header的abc参数值删除即可）。

    public String test2() {
        System.out.println("=========!abc");
        return "success";
    }
    
    @RequestMapping(value="/header/test3", headers = "Content-Type=application/json")  //表示请求的URL必须为“/header/test3” 且 请求头中必须有“Content-Type=application/json”参数即可匹配。（将Modify Header的Content-Type参数值改为“application/json”即可）；
    public String test31() {
        System.out.println("=========Content-Type=text/json");
        return "success";
    }
    
    @RequestMapping(value="/header/test4", headers = "Accept=application/json") //表示请求的URL必须为“/header/test4” 且 请求头中必须有“Accept =application/json”参数即可匹配。（将Modify Header的Accept参数值改为“application/json”即可）；
    public String test32() {
        System.out.println("=========Accept=text/json");
        return "success";
    }
    
    @RequestMapping(value="/header/test5", headers = "Accept=text/*") //表示请求的URL必须为“/header/test5” 且 请求头中必须有如“Accept=text/plain”参数即可匹配。（将Modify Header的Accept参数值改为“text/plain”即可）；
    public String test33() {
        System.out.println("=========Accept=text/*");
        return "success";
    }
    
    @RequestMapping(value="/header/test6", headers = "Accept=*/*") //表示请求的URL必须为“/header/test6” 且 请求头中必须有任意Accept参数即可匹配。（将Modify Header的Accept参数值改为“text/html”或“application/xml”等都可以）。
    public String test34() {
        System.out.println("=========Accept=*/*");
        return "success";
    }
    
    
    @RequestMapping(value="/header/test7", headers = "Accept!=text/vnd.wap.wml") //表示请求的URL必须为“/header/test7” 且 请求头中必须有“Accept”参数但值不等于“text/vnd.wap.wml”即可匹配。
    public String test4() {
        System.out.println("=========Accept!=text/vnd.wap.wml");
        return "success";
    }
    
    @RequestMapping(value="/header/test8", headers = {"Accept!=text/vnd.wap.wml", "abc=123"})  //表示请求的URL必须为“/header/test8” 且 请求头中必须有“Accept”参数但值不等于“text/vnd.wap.wml”且 请求中必须有参数“abc=123”即可匹配。
    public String test5() {
        System.out.println("=========Accept!=text/vnd.wap.wml , abc=123");
        return "success";
    }
 //   注：Accept:text/html,application/xhtml+xml,application/xml;q=0.9,**/*//*;q=0.8

  //  如果您的请求中含有Accept：**/*//*，则可以匹配功能处理方法上的如“text/html”、“text*//*”，“application/xml”等。*/

    
    
    
}

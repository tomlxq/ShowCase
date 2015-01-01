package cn.javass.chapter6.web.controller.consumesproduces.contenttype;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResponseContentTypeController {
//
    @RequestMapping("/response/ContentType")
    public void response1(HttpServletResponse response) throws IOException {
        //告诉客户端响应体媒体类型为html，编码为utf-8，大家可以通过chrome工具查看响应头为“Content-Type:text/html;charset=utf-8”，还一个“Content-Length:36”表示响应体大小。
        //①表示响应的内容区数据的媒体类型为html格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("text/html;charset=utf-8");
        //②写出响应体内容
        response.getWriter().write("<font style='color:red'>hello</font>");
    }
//大家可以下载chrome的JSONView插件来以更好看的方式查看json数据，安装地址：https://chrome.google.com/webstore/detail/chklaanhfefbnpoihckbnefhakgolnmc
   //Accept：用来指定什么媒体类型的响应是可接受的，即告诉服务器我需要什么媒体类型的数据，此时服务器应该根据Accept请求头生产指定媒体类型的数据。
    //大家可以下载chrome的JSONView插件来以更好看的方式查看json数据，安装地址：https://chrome.google.com/webstore/detail/chklaanhfefbnpoihckbnefhakgolnmc
    @RequestMapping(value = "/response/ContentType", headers = "Accept=application/json")
    public void response2(HttpServletResponse response) throws IOException {
        //①表示响应的内容区数据的媒体类型为json格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/json;charset=utf-8");
        //②写出响应体内容
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        response.getWriter().write(jsonData);//服务器根据请求头“Accept=application/json”生产json数据。
    }
    
    @RequestMapping(value = "/response/ContentType", headers = "Accept=application/xml")  //请求头为“Accept=application/xml”，响应体数据为xml。
    public void response3(HttpServletResponse response) throws IOException {
        //①表示响应的内容区数据的媒体类型为xml格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/xml;charset=utf-8");
        //②写出响应体内容
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xmlData += "<user><username>zhang</username><password>123</password></user>";
        response.getWriter().write(xmlData);
    }
    
}

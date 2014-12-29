package cn.javass.chapter6.web.controller.consumesproduces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProducesController {
    //①客户端—发送请求—服务器：客户端通过请求头Content-Type指定内容体的媒体类型（即客户端此时是生产者），服务器根据Content-Type消费内容体数据（即服务器此时是消费者）；
    //①服务器端可以通过指定【headers = "Content-Type=application/json"】来声明可处理（可消费）的媒体类型，即只消费Content-Type指定的请求内容体数据；
   // ②服务器—发送请求—客户端：服务器生产响应头Content-Type指定的响应体数据（即服务器此时是生产者），客户端根据Content-Type消费内容体数据（即客户端此时是消费者）。

    @RequestMapping(value = "/produces", produces = "application/json")
    public void response2(HttpServletResponse response) throws IOException {
        //①表示响应的内容区数据的媒体类型为json格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/json;charset=utf-8");
        //②写出响应体内容
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        response.getWriter().write(jsonData);
    }
    
    @RequestMapping(value = "/produces", produces = "application/xml")
    public void response3(HttpServletResponse response) throws IOException {
        //①表示响应的内容区数据的媒体类型为xml格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/xml;charset=utf-8");
        //②写出响应体内容
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xmlData += "<user><username>zhang</username><password>123</password></user>";
        response.getWriter().write(xmlData);
    }
    
}

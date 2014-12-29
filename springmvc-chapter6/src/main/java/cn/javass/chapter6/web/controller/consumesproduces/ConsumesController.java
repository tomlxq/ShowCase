package cn.javass.chapter6.web.controller.consumesproduces;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javass.chapter6.model.UserModel;

@Controller
public class ConsumesController {
    /*一、Media Type：

    互联网媒体类型，一般就是我们所说的MIME类型，用来确定请求的内容类型或响应的内容类型
    常见媒体类型：



text/html ： HTML格式          text/plain ：纯文本格式             text/xml ：XML格式

image/gif ：gif图片格式          image/jpeg ：jpg图片格式          image/png：png图片格式



application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）。

multipart/form-data ： 当你需要在表单中进行文件上传时，就需要使用该格式；



application/xhtml+xml ：XHTML格式               application/xml     ： XML数据格式

application/atom+xml  ：Atom XML聚合格式    application/json    ： JSON数据格式

application/pdf       ：pdf格式                        application/msword  ： Word文档格式

application/octet-stream ： 二进制流数据（如常见的文件下载）。



在如tomcat服务器的 “conf/web.xml”中指定了扩展名到媒体类型的映射，在此我们可以看到服务器支持的媒体类型。


    */

    //Content-Type 请求的内容类型
    @RequestMapping(value = "/consumes", consumes = {"application/json"})
    //此处使用consumes来指定功能处理方法能消费的媒体类型，其通过请求头的“Content-Type”来判断。
    //此种方式相对使用@RequestMapping的“headers = "Content-Type=application/json"”更能表明你的目的
    public String test(HttpServletRequest request) throws IOException {
        //表示请求的内容区数据为json数据
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];
        is.read(bytes);
        //得到请求中的内容区数据（以CharacterEncoding解码）
        //此处得到数据后你可以通过如json-lib转换为其他对象
        String jsonStr = new String(bytes, request.getCharacterEncoding());
        System.out.println("json data：" + jsonStr);
        return "success";
    }


}

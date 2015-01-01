package cn.javass.chapter6.web.controller.consumesproduces.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*Media Type：
        互联网媒体类型，一般就是我们所说的MIME类型，用来确定请求的内容类型或响应的内容类型。
        写道
        媒体类型格式：type/subtype(;parameter)?
        type主类型，任意的字符串，如text，如果是*号代表所有；
        subtype 子类型，任意的字符串，如html，如果是*号代表所有；
        parameter 可选，一些参数，如Accept请求头的q参数， Content-Type的 charset参数。

        详见http://tools.ietf.org/html/rfc2616#section-3.7
        常见媒体类型：

        text/html ： HTML格式          text/plain ：纯文本格式             text/xml ：XML格式
        image/gif ：gif图片格式          image/jpeg ：jpg图片格式          image/png：png图片格式

        application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）。
        multipart/form-data ： 当你需要在表单中进行文件上传时，就需要使用该格式；

        application/xhtml+xml ：XHTML格式               application/xml     ： XML数据格式
        application/atom+xml  ：Atom XML聚合格式    application/json    ： JSON数据格式
        application/pdf       ：pdf格式                        application/msword  ： Word文档格式
        application/octet-stream ： 二进制流数据（如常见的文件下载）。

        在如tomcat服务器的 “conf/web.xml”中指定了扩展名到媒体类型的映射，在此我们可以看到服务器支持的媒体类型。*/
@Controller
public class RequestContentTypeController {

    
    @RequestMapping(value = "/request/ContentType", method = RequestMethod.GET)
    public String showForm() throws IOException {
        //form表单，使用application/x-www-form-urlencoded编码方式提交表单
        return "consumesproduces/Content-Type";
    }
    /*
    * 只对请求头为“Content-Type:application/x-www-form-urlencoded”的请求进行处理（即消费请求内容区数据）;
      request.getContentType()：可以得到请求头的内容区数据类型（即Content-Type头的值）
      request.getCharacterEncoding()：如“Content-Type:application/json;charset=GBK”,则得到的编码为“GBK”，否则如果你设置过滤器（CharacterEncodingFilter）则得到它设置的编码，否则返回null。
      request.getParameter()：因为请求的内容区数据为application/x-www-form-urlencoded格式的数据，因此我们可以通过request.getParameter()得到相应参数数据。

request中设置请求头“Content-Type:application/json;charset=GBK”表示请求的内容区数据为json类型数据，且内容区的数据以GBK进行编码；
*/
    //request中设置请求头“Content-Type: application/x-www-form-urlencoded”表示请求的数据为key/value数据
    @RequestMapping(value = "/request/ContentType", method = RequestMethod.POST, 
                    headers = "Content-Type=application/x-www-form-urlencoded")
    public String request1(HttpServletRequest request) throws IOException {
        //①得到请求的内容区数据的类型
        String contentType = request.getContentType(); 
        System.out.println("========ContentType:" + contentType);
        //②得到请求的内容区数据的编码方式，如果请求中没有指定则为null
        //注意，我们的CharacterEncodingFilter这个过滤器设置了编码(UTF-8)
        //编码只能被指定一次，即如果客户端设置了编码，则过滤器不会再设置
        String characterEncoding = request.getCharacterEncoding();
        System.out.println("========CharacterEncoding:" + characterEncoding);
        
        //③表示请求的内容区数据为form表单提交的参数，此时我们可以通过request.getParameter得到数据（key=value）
        System.out.println(request.getParameter("realname"));
        System.out.println(request.getParameter("username"));
        return "success";
        
    }
    //
    @RequestMapping(value = "/request/ContentType", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public String request2(HttpServletRequest request) throws IOException {        
        //表示请求的内容区数据为json数据
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];//可以得到请求头的内容区数据的长度；
        is.read(bytes);
        //得到请求中的内容区数据（以CharacterEncoding解码）
        //此处得到数据后你可以通过如json-lib转换为其他对象
        String jsonStr = new String(bytes, request.getCharacterEncoding());//如“Content-Type:application/json;charset=GBK”,则得到的编码为“GBK”，否则如果你设置过滤器（CharacterEncodingFilter）则得到它设置的编码，否则返回null。
        System.out.println("json data：" + jsonStr);
        return "success";
    }
    
    
    
    
}

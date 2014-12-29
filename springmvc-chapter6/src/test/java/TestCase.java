import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * Created by tom on 2014/12/29.
 */
public class TestCase  {
    @Test
    public  void jsonRequest() throws IOException, URISyntaxException {
        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/response/ContentType";
        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/json");
        //③发送请求并得到响应
        ClientHttpResponse response = request.execute();
        //④得到响应体的编码方式
        Charset charset = response.getHeaders().getContentType().getCharSet();
        //⑤得到响应体的内容
        InputStream is = response.getBody();
        byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
        is.read(bytes);
        String jsonData = new String(bytes, charset);
        System.out.println("charset : " + charset + ", json data : " + jsonData);
    }
    @Test
    public  void xmlRequest() throws IOException, URISyntaxException {
        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/response/ContentType";
        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/xml");
        //③发送请求并得到响应
        ClientHttpResponse response = request.execute();
        //④得到响应体的编码方式
        Charset charset = response.getHeaders().getContentType().getCharSet();
        //⑤得到响应体的内容
        InputStream is = response.getBody();
        byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
        is.read(bytes);
        String xmlData = new String(bytes, charset);
        System.out.println("charset : " + charset + ", xml data : " + xmlData);
    }
  /*  目前我们可以使用如上三种方式实现来告诉服务器我们需要什么类型的数据，
    但麻烦的是现在有三种实现方式，难道我们为了支持三种类型的数据就要分别进行三种实现吗？
    当然不要这么麻烦，后续我们会学ContentNegotiatingViewResolver，它能帮助我们做到这一点

    请求阶段：客户端是生产者【生产Content-Type媒体类型的请求内容区数据】，服务器是消费者【消费客户端生产的Content-Type媒体类型的请求内容区数据】；

响应阶段：服务器是生产者【生产客户端请求头参数Accept指定的响应体数据】，客户端是消费者【消费服务器根据Accept请求头生产的响应体数据】。

    */

}

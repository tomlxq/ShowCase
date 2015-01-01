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

/*实际项目使用Accept请求头是比较麻烦的，现在大多数开放平台（国内的新浪微博、淘宝、腾讯等开放平台）使用如下两种方式：
        扩展名：如response/ContentType.json response/ContentType.xml方式，使用扩展名表示需要什么类型的数据；
        参数：如response/ContentType?format=json response/ContentType?format=xml，使用参数表示需要什么类型的数据；

        也就是说，目前我们可以使用如上三种方式实现来告诉服务器我们需要什么类型的数据，但麻烦的是现在有三种实现方式，
        难道我们为了支持三种类型的数据就要分别进行三种实现吗？当然不要这么麻烦，后续我们会学ContentNegotiatingViewResolver，它能帮助我们做到这一点。*/
public class TestCase  {
    @Test
    public  void testContentType() throws IOException, URISyntaxException {

        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/request/ContentType";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置请求头的内容类型头和内容编码（GBK）
        request.getHeaders().set("Content-Type", "application/json;charset=gbk");
        //③以GBK编码写出响应内容体
        //使用Spring提供的Http客户端API SimpleClientHttpRequestFactory创建了请求并设置了请求的Content-Type和编码并在响应体中写回了json数据
        // （即生产json类型的数据），此处是硬编码，实际工作可以使用json-lib等工具进行转换。
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        request.getBody().write(jsonData.getBytes("gbk"));

        //④发送请求并得到响应
        ClientHttpResponse response = request.execute();
        System.out.println(response.getStatusCode());
    }

  /*  当你有如下Accept头：
①Accept：text/html,application/xml,application/json
      将按照如下顺序进行produces的匹配 ①text/html ②application/xml ③application/json
②Accept：application/xml;q=0.5,application/json;q=0.9,text/html
      将按照如下顺序进行produces的匹配 ①text/html ②application/json ③application/xml
      q参数为媒体类型的质量因子，越大则优先权越高(从0到1)
③Accept：*\/*,text\/*,text/html
      将按照如下顺序进行produces的匹配 ①text/html ②text/* ③*\/*

    */
  @Test
    public  void testProduces() throws IOException, URISyntaxException {

        jsonRequest();
        xmlRequest();
    }
    private static void xmlRequest() throws IOException, URISyntaxException {
        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/response/ContentType";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/xml");  //表示客户端只接受（即只消费）xml格式的响应数据；

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

    private static void jsonRequest() throws IOException, URISyntaxException {
        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/response/ContentType";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/json");  //表示客户端只接受（即只消费）json格式的响应数据；

        //③发送请求并得到响应
        ClientHttpResponse response = request.execute();
           //可以得到响应头，从而可以得到响应体的内容类型和编码、内容长度。
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
    public  void testConsumes() throws IOException, URISyntaxException {

        //请求的地址
        String url = "http://localhost:8080/springmvc-chapter6/consumes";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);
        //②设置请求头的内容类型头和内容编码（GBK）
        request.getHeaders().set("Content-Type", "application/json;charset=gbk");
        //③以GBK编码写出响应内容体
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        request.getBody().write(jsonData.getBytes("gbk"));

        //④发送请求并得到响应
        ClientHttpResponse response = request.execute();
        System.out.println(response.getStatusCode());
    }
}

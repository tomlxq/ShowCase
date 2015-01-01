URL路径映射 

@RequestMapping(value="/users/**")：可以匹配“/users/abc/abc”，但“/users/123”将会被【URI模板模式映射中的“/users/{userId}”模式优先映射到】【详见4.14的最长匹配优先】。

 

@RequestMapping(value="/product?")：可匹配“/product1”或“/producta”，但不匹配“/product”或“/productaa”;

@RequestMapping(value="/product*")：可匹配“/productabc”或“/product”，但不匹配“/productabc/abc”;

@RequestMapping(value="/product/*")：可匹配“/product/abc”，但不匹配“/productabc”;

@RequestMapping(value="/products/**/{productId}")：可匹配“/products/abc/abc/123”或“/products/123”，也就是Ant风格和URI模板变量风格可混用;

 

此处需要注意的是【4.14中提到的最长匹配优先】，Ant风格的模式请参考4.14。

6.5.1.4、正则表达式风格的URL路径映射 

从Spring3.0开始支持正则表达式风格的URL路径映射，格式为{变量名:正则表达式}，这样我们就可以通过6.6.5讲的通过@PathVariable提取模式中的{×××：正则表达式匹配的值}中的×××变量了。

 

@RequestMapping(value="/products/{categoryCode:\\d+}-{pageNumber:\\d+}")：可以匹配“/products/123-1”，但不能匹配“/products/abc-1”，这样可以设计更加严格的规则。

 

正则表达式风格的URL路径映射是一种特殊的URI模板模式映射：

URI模板模式映射是{userId}，不能指定模板变量的数据类型，如是数字还是字符串；

正则表达式风格的URL路径映射，可以指定模板变量的数据类型，可以将规则写的相当复杂。

 

6.5.1.5、组合使用是“或”的关系 

如 @RequestMapping(value={"/test1", "/user/create"}) 组合使用是或的关系，即“/test1”或“/user/create”请求URL路径都可以映射到@RequestMapping指定的功能处理方法。

 
http请求信息包含六部分信息：
①请求方法，如GET或POST，表示提交的方式；
②URL，请求的地址信息；
③协议及版本；
④请求头信息（包括Cookie信息）；
⑤回车换行（CRLF）；
⑥请求内容区（即请求的内容或数据），如表单提交时的参数数据、URL请求参数（?abc=123 ？后边的）等。
想要了解HTTP/1.1协议，请访问http://tools.ietf.org/html/rfc2616。
那此处我们可以看到有①、②、④、⑥一般是可变的，因此我们可以这些信息进行请求到
处理器的功能处理方法的映射，因此请求的映射分为如下几种：
URL路径映射：使用URL映射请求到处理器的功能处理方法；
请求方法映射限定：如限定功能处理方法只处理GET请求；
请求参数映射限定：如限定只处理包含“abc”请求参数的请求；
请求头映射限定：如限定只处理“Accept=application/json”的请求。


①客户端—发送请求—服务器：客户端通过请求头Content-Type指定内容体的媒体类型（即客户端此时是生产者），服务器根据Content-Type消费内容体数据（即服务器此时是消费者）；
②服务器—发送请求—客户端：服务器生产响应头Content-Type指定的响应体数据（即服务器此时是生产者），客户端根据Content-Type消费内容体数据（即客户端此时是消费者）。
请求阶段：客户端是生产者【生产Content-Type媒体类型的请求内容区数据】，服务器是消费者【消费客户端生产的Content-Type媒体类型的请求内容区数据】；
响应阶段：服务器是生产者【生产客户端请求头参数Accept指定的响应体数据】，客户端是消费者【消费服务器根据Accept请求头生产的响应体数据】。


http://jinnianshilongnian.iteye.com/blog/1695047

http://jinnianshilongnian.iteye.com/blog/1723270
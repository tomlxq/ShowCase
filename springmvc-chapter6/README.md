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

 

以上URL映射的测试类为：cn.javass.chapter6.web.controller.mapping.MappingController.java。


http://jinnianshilongnian.iteye.com/blog/1695047
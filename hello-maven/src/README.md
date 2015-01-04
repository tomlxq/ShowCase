ShowCase
========
/*
use mysql;
UPDATE user SET Password = password('#X^48') WHERE User = 'photograph' ;
GRANT ALL PRIVILEGES ON photograph.* TO 'photograph'@'%' IDENTIFIED BY '#X^48' WITH GRANT OPTION;
GRANT ALL ON mysql.proc TO 'photograph'@'%';
flush privileges;

-- select user,host from user;　
delete from user where user.user='';

CREATE DATABASE IF NOT EXISTS photograph;
USE photograph;
*/
========
maven安装配置

在Windows上安装Maven
echo %JAVA_HOME%
java -version

Maven的下载页面：http://maven.apache.org/download.html
M2_HOME，变量值为Maven的安装目录D:\bin\apache-maven-3.0
Path的变量，在变量值的末尾加上%M2_HOME%\bin;
设置MAVEN_OPTS的值为：-Xms128m -Xmx512m


运行如下命令检查Maven的安装情况
echo %M2_HOME%
mvn -v

修改库位置:
$M2_HOME/conf/settings.xml
<localRepository>${M2_HOME}/repository</localRepository>

http://www.cnblogs.com/dcba1112/archive/2011/05/01/2033805.html


父子上下文(WebApplicationContext)

如果你使用了listener监听器来加载配置，一般在Struts+Spring+Hibernate的项目中都是使用listener监听器的。如下
<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>


Spring会创建一个WebApplicationContext上下文，称为父上下文（父容器） ，保存在 ServletContext中，key是WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE的值。
可以使用Spring提供的工具类取出上下文对象：WebApplicationContextUtils.getWebApplicationContext(ServletContext);

DispatcherServlet是一个Servlet,可以同时配置多个，每个 DispatcherServlet有一个自己的上下文对象（WebApplicationContext），称为子上下文（子容器），子上下文可以访问父上下文中的内容，但父上下文不能访问子上下文中的内容。 它也保存在 ServletContext中，key是"org.springframework.web.servlet.FrameworkServlet.CONTEXT"+Servlet名称。当一个Request对象产生时，会把这个子上下文对象（WebApplicationContext）保存在Request对象中，key是DispatcherServlet.class.getName() + ".CONTEXT"。
可以使用工具类取出上下文对象：RequestContextUtils.getWebApplicationContext(request);


说明 ：Spring 并没有限制我们，必须使用父子上下文。我们可以自己决定如何使用。

方案一，传统型：
父上下文容器中保存数据源、服务层、DAO层、事务的Bean。
子上下文容器中保存Mvc相关的Action的Bean.
事务控制在服务层。
由于父上下文容器不能访问子上下文容器中内容，事务的Bean在父上下文容器中，无法访问子上下文容器中内容，就无法对子上下文容器中Action进行AOP（事务）。
当然，做为“传统型”方案，也没有必要这要做。

方案二，激进型：
Java世界的“面向接口编程”的思想是正确的，但在增删改查为主业务的系统里，Dao层接口，Dao层实现类，Service层接口，Service层实现类，Action父类，Action。再加上众多的O(vo\po\bo)和jsp页面。写一个小功能 7、8个类就写出来了。 开发者说我就是想接点私活儿，和PHP，ASP抢抢饭碗，但我又是Java程序员。最好的结果是大项目能做好，小项目能做快。所以“激进型”方案就出现了-----没有接口、没有Service层、还可以没有众多的O(vo\po\bo)。那没有Service层事务控制在哪一层？只好上升的Action层。
本文不想说这是不是正确的思想，我想说的是Spring不会限制你这样做。
由于有了父子上下文，你将无法实现这一目标。解决方案是只使用子上下文容器，不要父上下文容器 。所以数据源、服务层、DAO层、事务的Bean、Action的Bean都放在子上下文容器中。就可以实现了，事务（注解事务）就正常工作了。这样才够激进。
总结：不使用listener监听器来加载spring的配置文件，只使用DispatcherServlet来加载spring的配置，不要父子上下文，只使用一个DispatcherServlet，事情就简单了，什么麻烦事儿也没有了。

Java--大项目能做好--按传统方式做，规规矩矩的做，好扩展，好维护。
Java--小项目能做快--按激进方式做，一周时间就可以出一个版本，先上线接受市场(用户)的反馈，再改进，再反馈，时间就是生命(成本)。


七、如何访问到静态的文件，如jpg,js,css？


如何你的DispatcherServlet拦截"*.do"这样的有后缀的URL，就不存在访问不到静态资源的问题。
如果你的DispatcherServlet拦截"/"，为了实现REST风格，拦截了所有的请求，那么同时对*.js,*.jpg等静态文件的访问也就被拦截了。
我们要解决这个问题。

目的：可以正常访问静态文件，不可以找不到静态文件报404。

方案一：激活Tomcat的defaultServlet来处理静态文件
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.jpg</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.js</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.css</url-pattern>
</servlet-mapping>
要配置多个，每种文件配置一个
要写在DispatcherServlet的前面， 让 defaultServlet先拦截请求，这样请求就不会进入Spring了，我想性能是最好的吧。
Tomcat, Jetty, JBoss, and GlassFish 自带的默认Servlet的名字 -- "default"
Google App Engine 自带的 默认Servlet的名字 -- "_ah_default"
Resin 自带的 默认Servlet的名字 -- "resin-file"
WebLogic 自带的 默认Servlet的名字  -- "FileServlet"
WebSphere  自带的 默认Servlet的名字 -- "SimpleFileServlet"


方案二： 在spring3.0.4以后版本提供了mvc:resources ，  使用方法：
<!-- 对静态资源文件的访问 -->
<mvc:resources mapping="/images/**" location="/images/" />

/images/**映射到ResourceHttpRequestHandler进行处理，location指定静态资源的位置.可以是web application根目录下、jar包里面，这样可以把静态资源压缩到jar包中。cache-period 可以使得静态资源进行web cache

如果出现下面的错误，可能是没有配置<mvc:annotation-driven />的原因。
报错WARNING: No mapping found for HTTP request with URI [/mvc/user/findUser/lisi/770] in DispatcherServlet with name 'springMVC'

使用<mvc:resources/>元素,把mapping的URI注册到SimpleUrlHandlerMapping的urlMap中,
key为mapping的URI pattern值,而value为ResourceHttpRequestHandler,
这样就巧妙的把对静态资源的访问由HandlerMapping转到ResourceHttpRequestHandler处理并返回,所以就支持classpath目录,jar包内静态资源的访问.
另外需要注意的一点是,不要对SimpleUrlHandlerMapping设置defaultHandler.因为对static uri的defaultHandler就是ResourceHttpRequestHandler,
否则无法处理static resources request.


方案三 ，使用<mvc:default-servlet-handler/>
<mvc:default-servlet-handler/>
会把"/**" url,注册到SimpleUrlHandlerMapping的urlMap中,把对静态资源的访问由HandlerMapping转到org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler处理并返回.
DefaultServletHttpRequestHandler使用就是各个Servlet容器自己的默认Servlet.


补充说明：多个HandlerMapping的执行顺序问题：
DefaultAnnotationHandlerMapping的order属性值是：0
<mvc:resources/>自动注册的 SimpleUrlHandlerMapping的order属性值是： 2147483646

<mvc:default-servlet-handler/>自动注册 的SimpleUrlHandlerMapping 的order属性值是： 2147483647


spring会先执行order值比较小的。当访问一个a.jpg图片文件时，先通过 DefaultAnnotationHandlerMapping 来找处理器，一定是找不到的，因为我们没有叫a.jpg的Action。然后再按order值升序找，由于最后一个 SimpleUrlHandlerMapping 是匹配 "/**"的，所以一定会匹配上，就可以响应图片。

访问一个图片，还要走层层匹配。不知性能如何？
最后再说明一下，方案二、方案三 在访问静态资源时，如果有匹配的(近似)总拦截器，就会走拦截器。如果你在拦截中实现权限检查，要注意过滤这些对静态文件的请求。
如何你的DispatcherServlet拦截 *.do这样的URL后缀，就不存上述问题了。还是有后缀方便。

八、请求如何映射到具体的Action中的方法？
方案一：基于xml配置映射，可以利用SimpleUrlHandlerMapping、BeanNameUrlHandlerMapping进行Url映射和拦截请求。
配置方法略。

方案二：基于注解映射，可以使用DefaultAnnotationHandlerMapping。
<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">  </bean>

但前面我们配置了<mvc:annotation-driven />，他会自动注册这个bean,就不须要我们显示的注册这个bean了。  

九、Spring中的拦截器：
Spring为我们提供了：
org.springframework.web.servlet.HandlerInterceptor接口，
org.springframework.web.servlet.handler.HandlerInterceptorAdapter适配器，
实现这个接口或继承此类，可以非常方便的实现自己的拦截器。

有以下三个方法：

Action之前执行:
 public boolean preHandle(HttpServletRequest request,
   HttpServletResponse response, Object handler);

生成视图之前执行
 public void postHandle(HttpServletRequest request,
   HttpServletResponse response, Object handler,
   ModelAndView modelAndView);

最后执行，可用于释放资源
 public void afterCompletion(HttpServletRequest request,
   HttpServletResponse response, Object handler, Exception ex)


分别实现预处理、后处理（调用了Service并返回ModelAndView，但未进行页面渲染）、返回处理（已经渲染了页面）
在preHandle中，可以进行编码、安全控制等处理；
在postHandle中，有机会修改ModelAndView；
在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
参数中的Object handler是下一个拦截器。

如何使用拦截器？
自定义一个拦截器，要实现HandlerInterceptor接口：
public class MyInteceptor implements HandlerInterceptor {
    略。。。
}

Spring MVC并没有总的拦截器，不能对所有的请求进行前后拦截。
Spring MVC的拦截器，是属于HandlerMapping级别的，可以有多个HandlerMapping ，每个HandlerMapping可以有自己的拦截器。
当一个请求按Order值从小到大，顺序执行HandlerMapping接口的实现类时，哪一个先有返回，那就可以结束了，后面的HandlerMapping就不走了，本道工序就完成了。就转到下一道工序了。
拦截器会在什么时候执行呢？ 一个请求交给一个HandlerMapping时，这个HandlerMapping先找有没有处理器来处理这个请求，如何找到了，就执行拦截器，执行完拦截后，交给目标处理器。
如果没有找到处理器，那么这个拦截器就不会被执行。

在spring MVC的配置文件中配置有三种方法：
方案一，（近似）总拦截器，拦截所有url
<mvc:interceptors>
  <bean class="com.app.mvc.MyInteceptor" />
 </mvc:interceptors>
为什么叫“近似”，前面说了，Spring没有总的拦截器。
<mvc:interceptors/>会为每一个HandlerMapping，注入一个拦截器。总有一个HandlerMapping是可以找到处理器的，最多也只找到一个处理器，所以这个拦截器总会被执行的。起到了总拦截器的作用。
如果是REST风格的URL，静态资源也会被拦截。


方案二， （近似） 总拦截器， 拦截匹配的URL。
<mvc:interceptors >
  <mvc:interceptor>
        <mvc:mapping path="/user/*" /> <!-- /user/*  -->
        <bean class="com.mvc.MyInteceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
就是比 方案一多了一个URL匹配。
如果是REST风格的URL，静态资源也会被拦截。



方案三,HandlerMappint上的拦截器。
如果是REST风格的URL，静态资源就不会被拦截。因为我们精准的注入了拦截器。
<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">
 <property name="interceptors">
     <list>
         <bean class="com.mvc.MyInteceptor"></bean>
     </list>
 </property>
</bean>
  如果使用了<mvc:annotation-driven />， 它会自动注册DefaultAnnotationHandlerMapping 与AnnotationMethodHandlerAdapter 这两个bean,所以就没有机会再给它注入interceptors属性，就无法指定拦截器。
当然我们可以通过人工配置上面的两个Bean，不使用 <mvc:annotation-driven />，就可以 给interceptors属性 注入拦截器了。

其实我也不建议使用 <mvc:annotation-driven />，而建议手动写详细的配置文件，来替代 <mvc:annotation-driven />，这就控制力就强了。


十一、如何实现全局的异常处理？
在spring MVC的配置文件中：
<!-- 总错误处理-->
 <bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
  <property name="defaultErrorView">
    <value>/error/error</value>
  </property>
  <property name="defaultStatusCode">
    <value>500</value>
  </property>
 <property name="warnLogCategory">
    <value>org.springframework.web.servlet.handler.SimpleMappingExceptionResolver</value>
  </property>
 </bean>

这里主要的类是SimpleMappingExceptionResolver类，和他的父类AbstractHandlerExceptionResolver类。
具体可以配置哪些属性，我是通过查看源码知道的。
你也可以实现HandlerExceptionResolver接口，写一个自己的异常处理程序。spring的扩展性是很好的。


通过SimpleMappingExceptionResolver我们可以将不同的异常映射到不同的jsp页面（通过exceptionMappings属性的配置）。

同时我们也可以为所有的异常指定一个默认的异常提示页面（通过defaultErrorView属性的配置），如果所抛出的异常在exceptionMappings中没有对应的映射，则Spring将用此默认配置显示异常信息。
注意这里配置的异常显示界面均仅包括主文件名，至于文件路径和后缀已经在viewResolver中指定。如/error/error表示/error/error.jsp


十二、如何把全局异常记录到日志中？
在前的配置中，其中有一个属性warnLogCategory，值是“SimpleMappingExceptionResolver类的全限定名”。我是在SimpleMappingExceptionResolver类父类AbstractHandlerExceptionResolver类中找到这个属性的。查看源码后得知：如果warnLogCategory不为空，spring就会使用apache的org.apache.commons.logging.Log日志工具，记录这个异常，级别是warn。
值：“org.springframework.web.servlet.handler.SimpleMappingExceptionResolver”，是“SimpleMappingExceptionResolver类的全限定名”。这个值不是随便写的。  因为我在log4j的配置文件中还要加入log4j.logger.org.springframework.web.servlet.handler.SimpleMappingExceptionResolver=WARN，保证这个级别是warn的日志一定会被记录，即使log4j的根日志级别是ERROR。

十四、转发与重定向
可以通过redirect/forward:url方式转到另一个Action进行连续的处理。
可以通过redirect:url 防止表单重复提交 。
写法如下：
return "forward:/order/add";
return "redirect:/index.jsp";


带参数重定向--RedirectAttributes
用户保存或修改后，为了防止用户刷新浏览器(F5)导致表单重复提交，一般在保存或修改操作之后会redirect到一个结果页面（不是forward），同时携带参数，如操作成功的提示信息。因为是Redirect，Request里的attribute不会传递过去。Spring在3.1才提供了这个能力--RedirectAttributes。 反复按F5，操作成功的提示信息也不会再次出来（总共只出现一次），效果很理想。

public String save(@ModelAttribute("group") Group group, RedirectAttributes redirectAttributes) {
 accountManager.saveGroup(group);
 redirectAttributes.addFlashAttribute("message", "操作成功");
 return "redirect:/account/group/";
}


 十五、处理ajax请求

1、引入下面两个jar包，我用的是1.7.2，好像1.4.2版本以上都可以，下载地址： http://wiki.fasterxml.com/JacksonDownload
jackson-core-asl-1.7.2.jar
jackson-mapper-asl-1.7.2.jar

2、spring的配置文件中要有这一行，才能使用到spring内置支持的json转换。如果你手工把POJO转成json就可以不须要使用spring内置支持的json转换。
<mvc:annotation-driven />


十七、如何取得Spring管理的bean （请用第3种方法）
1、servlet方式加载时，
【web.xml】
<servlet>
<servlet-name>springMVC</servlet-name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>classpath*:/springMVC.xml</param-value>
</init-param>
<load-on-startup>1</load-on-startup>
</servlet>
 spring容器放在ServletContext中的key是org.springframework.web.servlet.FrameworkServlet.CONTEXT.springMVC
注意后面的springMVC，是你的servlet-name配置的值，注意适时修改。
ServletContext sc=略
WebApplicationContext attr = (WebApplicationContext)sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.springMVC");


2、listener方式加载时：
【web.xml】
<context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>/WEB-INF/applicationContext</param-value>
</context-param>

<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
 【jsp/servlet】可以这样取得
ServletContext context = getServletContext();
WebApplicationContext applicationContext  = WebApplicationContextUtils .getWebApplicationContext(context);

3、通用的方法来了，神器啊，前的  1、2两种方法并不通用，可以抛弃了。
在配置文件中加入：
<!-- 用于持有ApplicationContext,可以使用SpringContextHolder.getBean('xxxx')的静态方法得到spring bean对象 -->
<bean class="com.xxxxx.SpringContextHolder" lazy-init="false" />


十九、 <mvc:annotation-driven /> 到底做了什么工作


一句 <mvc:annotation-driven />实际做了以下工作：（不包括添加自己定义的拦截器）
我们了解这些之后，对Spring3 MVC的控制力就更强大了，想改哪就改哪里。
<!-- 注解请求映射  -->
    <bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">
  <property name="interceptors">
      <list>
       <ref bean="logNDCInteceptor"/>   <!-- 日志拦截器，这是你自定义的拦截器 -->
       <ref bean="myRequestHelperInteceptor"/>   <!-- RequestHelper拦截器，这是你自定义的拦截器-->
       <ref bean="myPermissionsInteceptor"/>  <!-- 权限拦截器，这是你自定义的拦截器-->
       <ref bean="myUserInfoInteceptor"/>  <!-- 用户信息拦截器，这是你自定义的拦截器-->
      </list>
  </property>
 </bean>
 <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
  <property name="messageConverters">
   <list>
    <ref bean="byteArray_hmc" />
    <ref bean="string_hmc" />
    <ref bean="resource_hmc" />
    <ref bean="source_hmc" />
    <ref bean="xmlAwareForm_hmc" />
    <ref bean="jaxb2RootElement_hmc" />
    <ref bean="jackson_hmc" />
   </list>
  </property>
 </bean>
 <bean id="byteArray_hmc" class="org.springframework.http.converter.ByteArrayHttpMessageConverter" /><!-- 处理.. -->
 <bean id="string_hmc" class="org.springframework.http.converter.StringHttpMessageConverter" /><!-- 处理.. -->
 <bean id="resource_hmc" class="org.springframework.http.converter.ResourceHttpMessageConverter" /><!-- 处理.. -->
 <bean id="source_hmc" class="org.springframework.http.converter.xml.SourceHttpMessageConverter" /><!-- 处理.. -->
 <bean id="xmlAwareForm_hmc" class="org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter" /><!-- 处理.. -->
 <bean id="jaxb2RootElement_hmc" class="org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter" /><!-- 处理.. -->
 <bean id="jackson_hmc" class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter" /><!-- 处理json-->



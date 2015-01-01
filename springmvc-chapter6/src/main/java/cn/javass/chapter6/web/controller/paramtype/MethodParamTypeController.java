package cn.javass.chapter6.web.controller.paramtype;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import cn.javass.chapter6.model.UserModel;

@Controller
@RequestMapping("/method/param")
public class MethodParamTypeController {
    
    //获取Servlet API 相关的请求/响应 Spring Web MVC框架会自动帮助我们把相应的Servlet请求/响应（Servlet API）作为参数传递过来
    @RequestMapping("/requestOrResponse")
    public String requestOrResponse(
        ServletRequest servletRequest, HttpServletRequest httpServletRequest,
        ServletResponse servletResponse, HttpServletResponse httpServletResponse
    ) {
        
        return "success";
    }
    
    //获取 请求/响应的 内容区数据（以字节流的形式获取）
    //requestBodyIn：获取请求的内容区字节流，等价于request.getInputStream();
    //responseBodyOut：获取相应的内容区字节流，等价于response.getOutputStream()。
    @RequestMapping("/inputOrOutBody")
    public void inputOrOutBody(InputStream requestBodyIn, OutputStream responseBodyOut)
        throws IOException {
        responseBodyOut.write("success".getBytes());
    }
    
    //获取 请求/响应的 内容区数据（以字符流的形式获取）
    //reader：获取请求的内容区字符流，等价于request.getReader();
    //writer：获取相应的内容区字符流，等价于response.getWriter()。
    @RequestMapping("/readerOrWriterBody")
    public void readerOrWriteBody(Reader reader, Writer writer)
        throws IOException {
        writer.write("hello");
    }

    //WebRequest是Spring Web MVC提供的统一请求访问接口，不仅仅可以访问请求相关数据（如参数区数据、请求头数据，但访问不到Cookie区数据），还可以访问会话和上下文中的数据；NativeWebRequest继承了WebRequest，并提供访问本地Servlet API的方法。
    @RequestMapping("/webRequest")
    public String webRequest(WebRequest webRequest, NativeWebRequest nativeWebRequest) {
        System.out.println(webRequest.getParameter("test"));//①得到请求参数test的值  ① webRequest.getParameter：访问请求参数区的数据，可以通过getHeader()访问请求头数据；
        //② webRequest.setAttribute/getAttribute：到指定的作用范围内取/放属性数据，Servlet定义的三个作用范围分别使用如下常量代表：
        //SCOPE_REQUEST ：代表请求作用范围；
        //SCOPE_SESSION ：代表会话作用范围；
        //SCOPE_GLOBAL_SESSION ：代表全局会话作用范围，即ServletContext上下文作用范围。
        webRequest.setAttribute("name", "value", WebRequest.SCOPE_REQUEST);//②
        System.out.println(webRequest.getAttribute("name", WebRequest.SCOPE_REQUEST));
        //③ nativeWebRequest.getNativeRequest/nativeWebRequest.getNativeResponse：得到本地的Servlet API。
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);//③
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        return "success";
    }
    
    @RequestMapping("/session")
    public String session(HttpSession session) {
        System.out.println(session);
        //此处的session永远不为null。
        //注意：session访问不是线程安全的，如果需要线程安全，需要设置AnnotationMethodHandlerAdapter或RequestMappingHandlerAdapter的synchronizeOnSession属性为true，即可线程安全的访问session。
        return "success";
    }
    
   //Spring Web MVC能够自动将请求参数绑定到功能处理方法的命令/表单对象上。
    @RequestMapping(value = "/commandObject", method = RequestMethod.GET)
    public String toCreateUser(HttpServletRequest request, UserModel user) {
        return "customer/create";
    }
    
    @RequestMapping(value = "/commandObject", method = RequestMethod.POST)
    public String createUser(HttpServletRequest request, UserModel user) {
        //如果提交的表单（包含username和password文本域），将自动将请求参数绑定到命令对象user中去
        System.out.println(user);
        return "success";
    }
    //Spring Web MVC 提供Model、Map或ModelMap让我们能去暴露渲染视图需要的模型数据
    @RequestMapping(value = "/model")
    public String createUser(Model model, Map model2, ModelMap model3) {
        //虽然此处注入的是三个不同的类型（Model model, Map model2, ModelMap model3），但三者是同一个对象
        //AnnotationMethodHandlerAdapter和RequestMappingHandlerAdapter将使用BindingAwareModelMap作为模型对象的实现，即此处我们的形参（Model model, Map model2, ModelMap model3）都是同一个BindingAwareModelMap实例。
        model.addAttribute("a", "a");
        model2.put("b", "b");
        model3.put("c", "c");
        System.out.println(model == model2);
        System.out.println(model2 == model3);
        return "success";
    }
    
    
    @RequestMapping(value = "/mergeModel")
    public ModelAndView mergeModel(Model model) {
        model.addAttribute("a", "a");//①添加模型数据
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("a", "update");//②在视图渲染之前更新③处同名模型数据
        model.addAttribute("a", "new");//③修改①处同名模型数据
        //视图页面的a将显示为"update" 而不是"new"
        //从代码中我们可以总结出功能处理方法的返回值中的模型数据（如ModelAndView）会 合并 功能处理方法形式参数中的模型数据（如Model），但如果两者之间有同名的，返回值中的模型数据会覆盖形式参数中的模型数据。
        return mv;
    }
    
    @RequestMapping(value = "/error1")
    public String error1(UserModel user, BindingResult result) {
        return "success";
    }
    
    @RequestMapping(value = "/error2")
    public String error2(UserModel user, BindingResult result, Model model) {
        return "success";
    }
    
    @RequestMapping(value = "/error3")
    public String error3(UserModel user, Errors errors) {
        return "success";
    }
    //以上代码都能获取错误对象。
    //如下代码在spring3.1之前将抛出"Errors/BindingResult argument declared without preceding model attribute."异常
    //而spring3.1开始如下代码能正常工作
    @RequestMapping(value = "/error4")
    public String error4(UserModel user, Model model, Errors errors) {
        return "success";
    }

    //java.util.Locale：得到当前请求的本地化信息，默认等价于ServletRequest.getLocale()，如果配置LocaleResolver解析器则由它决定Locale，后续介绍；
    //java.security.Principal：该主体对象包含了验证通过的用户信息，等价于HttpServletRequest.getUserPrincipal()。
    @RequestMapping(value = "/other")
    public String other(Locale locale, Principal principal) {
        System.out.println(locale);
        System.out.println(principal);
        return "success";
    }
    
}

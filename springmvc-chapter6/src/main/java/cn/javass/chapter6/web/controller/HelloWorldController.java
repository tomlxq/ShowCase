package cn.javass.chapter6.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@RequestMapping
@Controller
public class HelloWorldController {
    //放置@Controller或@RequestMapping，即可把一个POJO类变身为处理器；
	//@RequestMapping(value = "/hello") 请求URL(/hello) 到 处理器的功能处理方法的映射；模型数据和逻辑视图名的返回
    @RequestMapping(value = "/hello")
    public ModelAndView helloWorld() {
		//1、收集参数
		//2、绑定参数到命令对象
		//3、调用业务对象
		//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		//添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		return mv;
	}
}

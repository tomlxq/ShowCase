package cn.javass.chapter4.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class HelloWorldCacheController extends AbstractController {
	/*从如上代码我们可以看出：

	1、继承AbstractController

	2、实现handleRequestInternal方法即可。*/

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//点击后再次请求当前页面
		resp.getWriter().write("<a href=''>this</a>");
		
		return null;
	}
}

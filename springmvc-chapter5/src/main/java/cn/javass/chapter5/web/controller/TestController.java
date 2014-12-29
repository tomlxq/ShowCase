package cn.javass.chapter5.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class TestController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("===========TestController");
		Thread.currentThread().sleep(1000);
		return new ModelAndView("test");
	}
}

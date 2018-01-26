package com.springmvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springmvc.annotation.Autowired;
import com.springmvc.annotation.Controller;
import com.springmvc.annotation.RequestMapping;
import com.springmvc.annotation.RequestParam;
import com.springmvc.service.TestService;
import com.springmvc.service.TestService2;

/** 
 * @Description 测试控制器类
 * @author com.springmvc
 * @date 2017年8月31日下午6:57:35
 * 
 */  
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired("testServiceImpl")
	private TestService testService;
	
	@Autowired
	private TestService2 testService2;
	
	@RequestMapping("/doTest")
	public void test(HttpServletRequest request, HttpServletResponse response, @RequestParam("param") String param){
		String result = testService.test();
		try {
			response.getWriter().println("do service result:" + result + ",param value:" + (param == null ? "null" : param));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/doTest2")
	public void test2(HttpServletRequest request, HttpServletResponse response){
		String result = testService2.test2();
		try {
			response.getWriter().println("do service2 result:" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

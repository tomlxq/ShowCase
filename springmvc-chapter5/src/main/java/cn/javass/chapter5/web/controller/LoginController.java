package cn.javass.chapter5.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller {
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if("GET".equalsIgnoreCase(req.getMethod())) {//1、请求是get方法表示到登录页面
            return new ModelAndView("login");
        }
        
        //2、非get请求表示提交登录
        String username = ServletRequestUtils.getStringParameter(req, "username");
        if("zhang".equals(username)) {//2.1、用户名为zhang时表示登录成功
            //TODO 登录成功添加用户编号到cookie/session 而不是设置标志
            //2.2、即session中有username表示登录成功
            req.getSession().setAttribute("username", username);
            return new ModelAndView("loginSuccess");
        }
  
        //3、登录失败，再返回到登录界面 登录界面应该显示错误信息
        return new ModelAndView("login");
    }

}

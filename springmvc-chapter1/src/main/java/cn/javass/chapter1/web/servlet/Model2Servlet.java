package cn.javass.chapter1.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.javass.chapter1.javabean.UserBean;

public class Model2Servlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	    throws ServletException, IOException {
        doPost(req, resp);//为了简单，直接委托给doPost进行处理
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        String submitFlag = req.getParameter("submitFlag");
        if("toLogin".equals(submitFlag)) {
            toLogin(req, resp);
            return;
        } else if("login".equals(submitFlag)) {
            login(req, resp);
            return;
        }
        toLogin(req, resp); //默认到登录页面
    }
    private void toLogin(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
    	//此处和JSP视图技术紧密耦合，更换其他视图技术几乎不可能
        req.getRequestDispatcher("/model2/login.jsp").forward(req, resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) 
        throws IOException, ServletException {
    	//1收集参数
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	//2验证并封装参数(重复的步骤)
    	UserBean user = new UserBean();
    	user.setUsername(username);
    	user.setPassword(password);
    	//3调用javabean对象（业务方法）
    	if(user.login()) {
    		//4根据返回值选择下一个页面
    		resp.sendRedirect(req.getContextPath() + "model2/success.jsp");
    	} else {
    		//登陆失败再返回登陆页面 并显示上次输入的用户名
    		
    		//将视图要显示的模型数据放在请求里传递给视图，视图再来展示
    		//此处也可以看出和Servlet API紧密耦合，更换视图技术也需要重新设置这些数据
    		req.setAttribute("user", user);
    		toLogin(req, resp);
    		return;
    	}
    }
}
<%@page import="cn.javass.chapter1.javabean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<%
    String submitFlag = request.getParameter("submitFlag");
    if("login".equals(submitFlag)) {//登录
        //1收集参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2验证并封装参数
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        //33调用javabean对象（业务方法）
        if(user.login()) {
        	//4根据返回值选择下一个页面
            out.write("login success");
        } else {
            out.write("login fail");
        }
    } else {
%>

                <form action="" method="post">
                    <input type="hidden" name="submitFlag" value="login"/>
                    
                    username:<input type="text" name="username"/><br/>
                    password:<input type="password" name="password"/><br/>
                    <input type="submit" value="login"/>
                </form>

<%         } %>

</body>
</html>
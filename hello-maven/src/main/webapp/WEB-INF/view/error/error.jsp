<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 2015/1/4
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
<h1>出错了</h1>
<%
  Exception e = (Exception)request.getAttribute("exception");
  out.print(e.getMessage());
%>
</body>
</html>

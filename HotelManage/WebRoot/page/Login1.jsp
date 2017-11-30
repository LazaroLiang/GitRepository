<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三峡大学酒店管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%= path %>/js/testLogin.js"></script>
  </head>
  
  <body>
   <!--This is my JSP page. <br>-->
   <center>
   	   <h2><font color="red">欢迎使用三峡大学酒店管理系统</font></h2><br>
	   <form action="CheckLoginServelet" method="post">
	   	用户名：<input type="text" name="userid"><br>
		密&nbsp;&nbsp;&nbsp;码:<input type="password" name="userpass"><br>
	   	<input type="submit" value="登陆">
	   	<input type="reset" value="重置">
	   </form>
   </center>
  </body>
</html>

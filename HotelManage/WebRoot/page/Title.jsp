<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache, must-revalidate, no-store"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css" media="all">
		.allow-float {clear:none!important;}
		.stop-float {clear:both!important;}
		.float-left {float:left;}
	</style>
	<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/ext-all.css" /> 
	
 	<script type="text/javascript" src="<%= path %>/extjs/ext-base.js"></script>
 	<!-- ENDLIBS -->
    <script type="text/javascript" src="<%= path %>/extjs/ext-all.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/RoomDataView.js"></script>
	
  </head>
  
  <body> 
  </body>
</html>

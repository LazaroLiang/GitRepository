<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三峡大学酒店管理系统首页</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache, must-revalidate, no-store"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 取得session中的用户名 -->
	<%
		HttpSession ses=request.getSession();
		String sesname= (String)ses.getAttribute("username") ;	
		int sesID=(Integer)ses.getAttribute("userID");
		int sesType=(Integer)ses.getAttribute("userType");
		//getAttribute("value");
		
	%>
	 <script>
		var namese = '<%=sesname %>';
		var idse='<%=sesID %>';
		var userType='<%=sesType %>';
	</script>
	<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/ext-all.css" /> 
	<link rel="stylesheet" type="text/css" href="<%= path %>/js/frontdesk/RoomDataView.css"/>
	
 	<script type="text/javascript" src="<%= path %>/extjs/ext-base.js"></script>
 	<!-- ENDLIBS -->
    <script type="text/javascript" src="<%= path %>/extjs/ext-all.js"></script>
       
	<script type="text/javascript" src="<%= path %>/js/FrontDesk.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/MainMenuTree.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/RoomDataView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/RoomView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/MainView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/MainHeader.js"></script>
	<script type="text/javascript" src="<%= path %>/js/UserView/UserInfoWin.js"></script>
	<script type="text/javascript" src="<%= path %>/js/GuestView/GuestView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/RoomManagerView/RoomManagerView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/CheckInForm.js"></script>
	<script type="text/javascript" src="<%= path %>/js/frontdesk/BalanceForm.js"></script>
	<script type="text/javascript" src="<%= path %>/js/OrderManagerView/OrderManagerView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/BalanceManagerView/BalanceManagerView.js"></script>
	<script type="text/javascript" src="<%= path %>/js/UserView/UserManagerView.js"></script>	
	
  </head>
  
  <body> 
  </body>
</html>

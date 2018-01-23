<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%
	request.setCharacterEncoding("UTF-8");
  Object result = request.getAttribute("result");
	String id = (String)request.getAttribute("id");
	String name = (String)request.getAttribute("name");
	String address = (String)request.getAttribute("address");
	Object yesno = request.getAttribute("yesno");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>PAGE</title>
</head>
<body>
  <ul>
    <li>id:<%= id%></li>
    <li>name:<%= name%></li>
    <li>address:<%= address%></li>
    <li>yesno:<%= yesno%></li>
  </ul>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
<%

//스크립틀릿 : 지역변수
request.setCharacterEncoding("UTF-8");

String userId = request.getParameter("id"); //name=""이랑 같아야됨 
String password = request.getParameter("passwd");


//호스트명 7
String hostValue = request.getHeader("host");
//설정된 언어
String alValue = request.getHeader("accept-language");





%>  
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object(내장객체)</title>
</head>
<body>


	
	<p>아이디:<%=userId %></p>
	<p>비밀번호:<%=password %></p>
	<p>호스트명:<%=hostValue %></p>
	<p>설정된 언어:<%=alValue %></p>


</body>
</html>
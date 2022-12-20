<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--
ExceptionDesc
UserNotFoundException

web.xml
  <error-page>
  	<error-code>500</error-code> 
  	<location>/erros/error500.jsp</location>
  </error-page>
   <error-page>
  	<error-code>404</error-code> 
  	<location>/erros/error404.jsp</location>
  </error-page>
  <error-page>
  	<exception-type>java.lang.NullPointerException</exception-type> 
  	<location>/errors/errorNull.jsp</location>
  </error-page>
  
errors > error404, error500, error404 , errorNull
  
  

  -->
  
  
<h4>예외처리 테스트 </h4> 
<%

	if(1==1)
		throw new IOException("강제발생예외");


%> 




</body>
</html>
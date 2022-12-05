<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- prefix : 접두어   -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<%
		request.setCharacterEncoding("UTF-8");
	
		//요청 URI : response01_process.jsp?id=a001&passwd = java
		String userId = request.getParameter("id");
		String password = request.getParameter("passwd");
	%>
	
	<c:set var="id" value="<%= userId %>" />
	<c:set var="passwd" value="<%= password %>" />
	
	<!--  서로 변수 자체가 다른거임 자바의 변수 userId/password, 일반 jsp용 변수passwd,id   -->
	아이디: ${id}, 비밀번호: ${passwd} <br />
	
	
	<script>
	let varId = "${id}";
	let passwd = "${passwd}";
	
	if(varId == "a001"&&passwd == "java") {
		location.href="response01_sucess.jsp";
	}else {
		location.href="response01_fail.jsp";
	}
	</script>
	
	
	<% 	
	
		// userID가 a001이고 동시에 password가 java이라면 
		// response01_success.jsp로 페이지 이동
		// 아니면 
		// response01_failed.jsp로 페이지 이동 
		/* if(userId.equals("a001") && password.equals("java")) {
			response.sendRedirect("response01_sucess.jsp");
		}else {
			response.sendRedirect("response01_fail.jsp");
		}  */
	
	%>
	

	
	
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object</title>
</head>
<body>

	<%
		//설정한 URL 페이지로 강제 이동 
		response.sendRedirect("http://www.google.com");
	
		/* while(true) { */
			out.print("개똥이");
			out.print("개똥이");
			out.print("개똥이");
		/* } */
	
	%>



</body>
</html>
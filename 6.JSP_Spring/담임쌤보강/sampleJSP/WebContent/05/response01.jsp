<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Objects</title>
</head>
<body>

	<!-- 
		요청 URI : response01_process.jsp?id=a001&passwd = java
		post방식이므로 파라미터 값 숨겨져서 전송됨 
	
	 -->



	<form action="response01_process.jsp" method="post">
		<p>아이디 : <input type="text" name="id" required /></p>
		<p>비밀번호 : <input type="password" name="passwd" required /></p>
		<p><input type="submit" value="전송" /></p>
	</form>


</body>
</html>
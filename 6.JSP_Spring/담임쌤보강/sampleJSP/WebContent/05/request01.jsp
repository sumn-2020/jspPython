<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object(내장객체)</title>
</head>
<body>

	<!-- 
		
		URI : request01_process.jsp?id=a001&passwd=java
		URI : request01_process.jsp
		요청파라미터: id=a001&passwd=java <= request 객체가 갖고있음 

	 -->

	<form action="request01_process.jsp" method="post">
		<p>	아이디: <input type="text" name="id"  /></p>
		<p>	비밀번호: <input type="text" name="passwd"  /></p>
		<p>	<input type="submit" name="전송"  /></p>
	</form>

</body>
</html>
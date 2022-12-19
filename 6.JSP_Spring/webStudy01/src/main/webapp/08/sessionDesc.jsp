<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>session(HttpSession)</h4>

<h4 id="timerArea"></h4>
<pre>
	(웹)세션이란? 
		: 어플리케이션 서버를 사용하기 시작한 순간부터 사용 종료까지의 기한.
		
	세션 시작(생성) : 클라이언트의 최초 요청(재전성되는 아이디가 없는 요청 - 재전송되는 아이디가 없을때 최초요청으로 봄)으로 인해 발생. -> 식별자가 부여된 세션이 새로 생성.
				   -> 요청에 대한 응답이 전송될 때 응답 "헤더"에 포함되어 클라이언트로 전송. 
				   
		세션 아이디: <%=session.getId() %>
		세션의 생성시점 : <%=new Date(session.getCreationTime()) %>
		마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
		timeout : <%=session.getMaxInactiveInterval() %>s (초단위)		
			
		세션 유지(tracking mode) : 세션의 식별자(세션 아이디) 재전송 구조. (클라이언트가 세션아이디 가지고 있다가 서버에게 다시 재전송하는 방법)
		1) COOKIE
		2) URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">URL트래킹모드</a>
		3) SSL(Secure Socket Layer)
		=> COOKIE, URL : 세션아이디가 브라우저상단에 그대로 노출되기 때문에 보안에 취약함. 그래서 나온게 SSL
		<!-- jsessionid  : 세션파라미터  -->
		
			
	세션 종료(만료)
		1) 세션의 아이디가 재전송되지 않을 때  (ex) 세션과 관련된 쿠키 삭제 됐을 경우
		2) 브라우저가 종료될 때 
		3) session timeout 이내에 새로운 요청을 통해 아이디가 재전송되지 않을 때
		4) session invalidation(명시적인 로그아웃) : 로그아웃 버튼을 눌렀을 때 
		
	
	////////////////////////////////////////////////////////////////
	
	
	쿠키: "클라이언트" 사이드에 저장되는 데이터
	세션 : "서버" 사이드에 저장되는 데이터 -> 클라이언트의 최초요청에 의해서 세션이 생성됨 
	        각 세션에는 각각 id가 부여됨 -> 세션ID : 여러개의 요청이 있을 때 각 요청에 id가 부여되어 저장됨
	       
	       
	클라이언트              -> req (id포함해서 던져줌)   -> 서버(세션)
	서버(세션)       -> resp(id포함해서 던져줌)  -> 클라이언트(쿠키)
	클라이언트(쿠키)  -> req (id포함해서 던져줌)   -> 서버(세션)
	
	세션 만료: 클라이언트 속 쿠키 삭제 / 톰캣의 만료시간(30분 후)에 자동으로 세션 종료됨
	web.xml에서 따로 지정할수도있음
 	D:\A_TeachingMaterial\6.JSP_Spring\workspace\webStudy01\src\main\webapp\WEB-INF
	<!--    <session-config>
		  <session-timeout>2</session-timeout> session timetout 2분으로 설정 
	  </session-config>  -->
	
</pre>

<script>


	//custom.js
	$("#timerArea").sessionTimer(120);
	//sessionTimer 바꿔보기 
	//4분이면 240
	//3분이면 140... 



</script>

</body>
</html>
<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>EL 기본 객체</h4>
<pre>

<!-- 	1~5 : 전부  MAP
	6 : MAP 아님 -->


	1. scope 객체(Map&lt;String, Object&gt;): pageScope, requestScope, sessionScope, applicationScope
	
	2. 파라미터 객체 : param(Map&lt;String, String&gt;), paramValues(Map&lt;String, String[]&gt;)
	
	** 파라미터 값 가져오기 
	<a href="?name1=value1&name2=value2">TEST</a>
	<%=request.getParameter("name1") %>,
	${param.name1 }, ${param['name1'] } =>  \${request } 이런식으로 바로 접근 못하기 때문에 param을 사용해서 접근
	

	** 하나의 이름으로 두개 이상의 파라미터 값을 꺼내고 싶을 경우 :
 	<a href="?name11=value1&name11=value2">TEST2</a> 
	<%-- <%=request.getParameterValues("name11")[1] %>,  --%>
	${paramValues.name11[1]  },  => 배열의 형태로 출력됨
	${paramValues['name11'][1] } => 배열의 형태로 출력됨 
	 


	3. 요청 헤더 객체 : header(Map&lt;String, String&gt;), headerValues(Map&lt;String, String[]&gt;)
	 <%=request.getHeader("user-agent") %>,
	 ${header.user-agent } ,
	 ${header['user-agent'] }
	
	
	
	
	4. 쿠키 객체 : cookie(Map&lt;String, Cookie&gt;) <%-- <% Cookie test; test.getMaxAge() %> --%>
		<%=request.getCookies() %>
		${cookie.JSESSIONID.getValue() }, => f12 application > cookie속에서 (JSESSIONID의 값) 가져오기
		${cookie.JSESSIONID.value },
		${cookie['JSESSIONID'].value }
		${cookie['JSESSIONID']['value'] }
	
	
	

	
	5. 컨텍스트 파라미터 객체 : initParam(Map&lt;String, String&gt;)
<!-- 	
	web.xml <= 컨텍스트 파라미터위치    
<context-param>
  	<param-name>imageFolder</param-name>
  	<param-value>d:/contents/images</param-value>
 </context-param> 
  
 -->
  
  	<%=application.getInitParameter("imageFolder") %>
  	${initParam.imageFolder },
  	${initParam['imageFolder'] }
	

	
	6. pageContext : 
		${pageContext.request.contextPath }, 
		${pageContext.session.id } => 바로 \${session } 이런식으로 못씀 pageContext를 통해서 session에 접근할 수 있음 
		
	

</pre>


<!-- 
문제
현재 요청의 파라미터 중 (sample) 이라는 이름의 파라미터 값을 출력하라(EL).
단 해당 파라미터가 없는 경우, ("SAMPLE")이라는 기본값을 사용함 
-->

${not empty param['sample'] ? param.sample : "SAMPLE" }

<!-- request.getParameter("sample") 이게 있을수도 있고 없을수도 있을 때는 =>  이때는 optional.ofNullable 사용! --> 
<%=Optional.ofNullable(request.getParameter("sample")).orElse("SAMPLE") %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>기본객체(내장객체)</h4>
<pre>

	<!-- ★★★★★  -->
	request(HttpServletRequest) : 클라이언트와 그로부터 발생한 요청에 대한 정보를 캡슐화한 객체 
	response(HttpServletResponse) : 서버에서 클라이언트로 전송되는 응답에 대한 정보를 캡슐화한 객체 
	out(JspWriter) : == response.getWriter(), response body에 컨텐츠를 기록 (버퍼를 제어)할 때 활용
	session(HttpSession) : 한 클라이언트와 하나의 브라우저를 대상으로 생성되는 한 세션에 대한 정보를 캡슐화한 객체 
	application(ServletContext): 하나의 컨텍스트와 서버에 대한 정보를 캡슐화한 객체 
	
	page(Object) == this, custom tag 작성시 활용됨
	config(ServletConfig) : 현재 서블릿의 설정 정보를 캡슐화한 객체.
	
	<!-- ★★★★★ el ${} 에서는 pageContext만 쓸수있으면 나머지들은 사용못함-->
	pageContext(PageContext) : 현재 JSP페이지에 대한 모든 정보를 캡슐화한 객체 , 가장 먼저 생성됨, 모든 기본객체들이 pageContext에서 파생됨 
	ex) ${pageContext.request.contextPath}


	exception : 발생한 에러(예외)에 대한 정보를 캡슐화한 객체 , 상단 page 지시자<!-- isErrorPage="true" -->의 isErrorpage가 활성화된 경우 




</pre>


</body>
</html>
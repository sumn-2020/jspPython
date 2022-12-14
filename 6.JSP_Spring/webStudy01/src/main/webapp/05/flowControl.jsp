<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%-- <jsp:include page="/includee/preScript.jsp"></jsp:include> --%>
<%@ include file="/includee/preScript.jsp" %>
<%=varOnPre %> <!-- @ include : 이 방법으로 쓰면 옆에처럼 쓸수있음  -->

</head>
<body>

<!-- preScript.jsp : 소스 불러오는 코드   -->
<!--  flowControl.jsp -->
<!--  인클루드 : standard.jsp   -->


flowControl.jsp

<h4>흐름제어방법</h4>
<pre>

	Http : Connectless, Stateless
	A -> B 이동방식



	1. 요청 분기(request dispatch) : A를 대상으로 한 최초의 요청이 계속 유지됨
		=> 서버안에서만 이동함(request.getContentxtPath로 경로 잡아줄 필요 없음)
		1) forward(Model2구조에서 많이 사용됨) : A(request 처리, controller) -> B(response 생성, view)에서 이동 후의 최종 응답은 B에서 전송
		2) include(페이지 모듈화) : A -> B -> A로 다시 돌아감. 최종 응답에는 A+B의 모든 데이터가 포함됨
			내포되는 시점과 내포되는 대상에 따라 분류됨
			- 정적 내포 : 컴파일 전에 소스가 파싱되는 단계에서 소스파일이 내포됨.
			- 동적 내포 : 실행시에 실행의 결과 데이터가 내포됨.
	    <%
	    	request.setAttribute("attr1", new Date());
	    	String path = "/02/standard.jsp";

//			request.getRequestDispatcher(path).forward(request, response);
//			request.getRequestDispatcher(path).include(request, response);
			pageContext.include(path); //코드가 있는 그 자리에 고대로 넣는거 


		%> 
	2. Redirect : 
		A   -> response body가 없고, Line(302) + Header(Location)로만 구성된 응답이 전송
			-> Location 방향으로 새로운 요청을 전송함
			-> B에서 Body를 가진 최종 응답이 전송됨. 
		<%--
			session.setAttribute("attr2", "세션속성");
			String location = request.getContextPath() + path;  //클라이언트가 사용하기 때문에 request.getContextPath()로 경로 잡아줘야됨
			//response.sendRedirect(location+"?param1="+request.getParameter("param1"));	
			response.sendRedirect(location);	
		--%>


</pre>
	
	
</body>
</html>
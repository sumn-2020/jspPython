<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public String sample = "SAMPLE";
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>pageContext(pageContext)</h4>

** CAC(Context Aware Computing)와 비슷 - 상황인식컴퓨팅(휴대폰이 놓은 장소에 따라 작업수행)



<pre>
	: 하나의  JSP페이지와 관련된 모든 정보(기본 객체)를 가진 객체 
	
	어떤 경우에 활용되는가?
	1. EL에서 주로 기본객체를 확보할 때 사용
	   ex) <%=request.getContextPath() %>,
           <%=((HttpServletRequest) pageContext.getRequest()).getContextPath() %>
           , ${pageContext.request.contextPath }
           
           
    2. 에러 데이터 확보할 때 사용
    3. 흐름제어 (요청분기): forward/include
    4. 영역 제어(★★★★★)
    	<%
    		//pageContext.setAttribute(name, value, scope)
    	%>	
</pre>


<h4>Scope</h4>
<pre>

 	Servlet[JSP] Container : 서블릿 객체나 JSP객체의 모든 관리 권한을 가진 주체(IoC[Inversion of Control] : 제어권이 역전돼서 제어권이 개발자한테 있는게 아니라 container의 서블릿에 제어권이 넘어갔음) 
	
	Scope :  웹 어플리케이션에서 데이터를 공유하기 위해 사용되는 저장공간(Map%lt;String, Object&gt;).
	Attribute :  scope를 통해 공유되는 데이터(String name/Object value).
	
	:Scope라는 저장공간을 소유한 기본 객체의 생명주기와 동일함.
	[scope의 종류]
	page scope : pageContext의 소유공간, 현재 페이지에서만 공유 가능 영역
	request scope : 해당 영역의 소유 요청객체가 소멸될 때 함꼐 소멸됨
	session scope : 해당 영역을 소유한 세션 객체와 생명주기 동일
	application scope : ServletContext와 동일한 생명주기를 가짐
	
	setAttribute(name, value), get Attribute(name), removeAttribute(name)


	<%
	   //attrView.jsp 와 분리
	   
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
	
	
		pageContext.setAttribute("sample", "페이지샘풀");
		pageContext.setAttribute("sample", "요청샘풀", PageContext.REQUEST_SCOPE);
		//PageContext.REQUEST_SCOPE = request.setAttrbute
		
		
		
		
		//1. forward
//		String viewName = "/09/attrView.jsp";
//		request.getRequestDispatcher(viewName).forward(request, response);
	
		
		//2. include
//		String viewName = "/09/attrView.jsp";
//		request.getRequestDispatcher(viewName).include(request, response);
	
		//3. redirect
 		String viewName = "/09/attrView.jsp";
		String location = request.getContextPath() + viewName;
//		response.sendRedirect(location);	
	 
		
	%>
	


</pre>
<h4>공유된 속성 데이터들</h4>
<pre>

	<%-- sample : ${sample } --%>
	sample : ${ requestScope.sample }



	page scope : <%=pageContext.getAttribute("pageAttr") %> , ${pageAttr}
	request scope : <%=request.getAttribute("requestAttr") %>, ${requestAttr}
	session scope : <%=session.getAttribute("sessionAttr") %>, ${sessionAttr}
	<%
		//flash Attribute 방식 
		session.removeAttribute("sessionAttr");
	%>
	application scope : <%=application.getAttribute("applicationAttr") %>, ${sessionAttr}


</pre>



</body>
</html>
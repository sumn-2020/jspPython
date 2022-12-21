<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!-- 1단계: 커스텀 태그 로딩   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
    
 <!--  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- gugudan.jsp  -->


<h4>JSTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그들 중에서 많이 활용될 수 있는 것들을 모아놓은 라이브러리
	&lt;prefix:tagname attr_name="attr_value" &gt;
	
	1. 커스텀 태그 로딩 : taglib directive (제일 상단 참고)
	2. prefix를 통한 태그 접근
	
	** core 태그 종류
	1. EL 변수(=속성)와 관련된 태그 : set, remove

		<c:set var="sample" value="샘플값" />
		${sample } => page scope에 들어있는 sample을 가져온거임
	
	
		<c:set var="sample" value="샘플값" scope="request" />
		${sample } => 뒤지는 순서 : 페이지 뒤지기 > request뒤지기
		${requestScope.sample } => 바로 request뒤지기 


		<c:set var="sample" value="샘플값" scope="session" />
		${sessionScope.sample } => 바로 session뒤지기
		<c:remove var="sample" scope="session" />  <!-- session 삭제하기  : remove를 할때는 어느 영역에서 지우겠다라는 것을 명시해주는 게 좋음  scope="session"  -->
		--> ${sample }


	
	2. 조건문: if(조건식){블럭문}else{}, switch ~ case(조건값)...~default
		
		단일조건문 : if (else는 없음)
			<c:if test="${empty param.name1}">  <!-- => 파라미터 중 name1이라는 값이 없으면?  -->
				파라미터 없음 
			</c:if>
			<c:if test="${not empty param.name1}">  <!-- 값이 있다면 ? -->
				파라미터 있음 
			</c:if>
	
	
		다중조건문 : choose ~ when ~ otherwise (=> switch ~ case ~ default)
			<c:choose>
				<c:when test="${empty param.name1}"><!-- => 파라미터 중 name1이라는 값이 없으면?  -->
					파라미터 없음 
				</c:when>
				<c:otherwise> <!-- 값이 있다면 ? -->
					파라미터 있음 
				</c:otherwise>	
			</c:choose>
	
	
	
	3. 반복문 : foreach, forTokens, for(선언절, 조건절, 증감절),  for(임시블럭변수 : 반복할대상 집합객체)
		
		
		[foreach]
		page scope에 있는 array 만들기 : <c:set var="array" value='<%=new String[]{"value1", "value2"} %>' />

		** 일반 for문
<%-- 		<c:forEach var="i" begin="0" end="${array.length()-1 }" step="1"> <!--  =>  0부터 1까지 돌아라 /  1씩 증가(step="1"일 경우 생략 가능) -->
			${array[i] }
		</c:forEach> --%>
		=> 배열에는 바로 length 못씀  MethodNotFoundException 뜸
		
		<c:forEach var="i" begin="0" end="${fn:length(array)-1 }" step="1"  varStatus="vs"> 
			${array[i] } 
			
			[varStatus] => varStatus="vs"
			반복의 현재상태를 표시 
			: 현재 반복의 상태(LoopTagStatus)
			: 현재 반복의 상태에 맞춰서 view를 제어할 수 있음 
			반복문인지 몇번째 index인지 확인하고 싶을 경우 ${vs.index } 
			몇번째 반복문인지 확인하고 싶을 경우:  ${vs.count } 
			첫번째 반복문인지 확인하고 싶을 경우 : ${vs.first } 
			마지막 반복문인지 확인하고 싶을 경우 : ${vs.last }
			
		</c:forEach> 
		=> array에 직접 length 호출하지 않기 위해서 상단 function 사용<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> --%>
		
		
		**향상된 for문 
		<c:forEach items="${array }" var="element">
			${element }
		</c:forEach>
		
		
		
		///////////////////////////////////////////////////
		
		[forTokens] 
		: 의미를 부여할 수 있는 최소한의 단위 : 토큰 
		int num = 3; => 토큰 4개  (whitespace를 기준으로 토큰 나눔)
		intnum=3; => 구분자가  없기 때문에 전체가 토큰 1개
		select mem_id from member;
		아버지 가방에 들어가신다
		
		
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token }
		</c:forTokens>
		items="아버지 가방에 들어가신다" => 이 문장을 대상으로 
		delims=" " => 공백을 기준으로
		토큰을 쪼개갰다 


		<c:forTokens items="1,2,3,4" delims="," var="token">
			${token * 10 }
		</c:forTokens>
		=> 곱하기 10을 파싱안하고도 출력할 수 있음  => 결과 : 10, 20, 30, 40
		

		
		
	
	4. 기타
		4-1. url 재작성 : url, redirect
			1)  url(client side path만들어줌, session parameter 만들어줌), redirect 
				<c:url value="/06/memoView.jsp" /> => 결과 :/webStudy01/06/memoView.jsp
				=> 클라이언트 사이드 절대경로를 완성시켜줌 
				<%-- => <%=request.contextPath() %>써줄필요 없음 --%>
				
				<!-- => 쿠키가 없더라도 세션파라미터 값을 유지시켜줌
				/webStudy01/06/memoView.jsp;jsessionid=4D0B8CF6BAE05FAA862C1347569B7363
				 -->
				 
				<a href="<c:url value='/10/jstlDesc.jsp' />">세션유지</a>
				
			2)  redirect 
			아래 스클립틀리기호보다 훨씬 간단하게 작성할 수 있음 

			<%-- 	
				<c:redirect url="/06/memoView.jsp"></c:redirect> <!-- memoView로 바로 이동  -->
				<%
					String location = request. getContextPath()  + "/06/memoView.jsp";
					response.sendRedirect(location);
				%> 
			--%>
			

		4-2. 표현구조	 : out => 자동으로 태그 기호를 escape 시켜줌 
			1) 
			 <c:out value="<h4>출력값</h4>" escapeXml="true"></c:out> => 결과 : <h4>출력값</h4> 
			<c:out value="<h4>출력값</h4>" escapeXml="false"></c:out> => 결과 : 진짜 태그로 출력
	 
	 
	 		
			2) 크롤링 : 다른 페이지 긁어올때 쓰임 
			 <%-- <jsp:include page="" ></jsp:include>  => 같은 어플리케이션안에 있을 때만 긁어올수 있음  --%>
			 <%-- <jsp:include page="www.naver.com" ></jsp:include> => 불가능  --%> 
			
			<%-- <c:import url="" ></c:import>  => 긁어오는데 있어서 컨택스트의 제한이 없음   --%>
			<%-- <c:import url="www.naver.com" ></c:import> => 가능  --%>

</pre>


<c:import url="https://www.naver.com/"></c:import> => 바로 화면에 출력 
<c:import url="https://www.naver.com/" var="naver"></c:import> =>네이버를 출력하지 않고 var안에 저장해두었음 (pageScope에 일단 저장)
${naver }
 

<c:out value="${naver }" escapeXml="true" />
html소스를 전부 escape시킴 => 네이버 화면이 아니라 네이버 html 소스가 보임 

<!-- playground.jsp 크롤링  -->



</body>
</html>
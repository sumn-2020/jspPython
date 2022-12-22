<%@page import="java.net.URLEncoder"%>
<%@page import="kr.or.ddit.commons.wrapper.CookieHttpServletRequestWrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
CookieHttpServletRequestWrapper
11폴더 , 12폴더  > viewCookie.jsp
 -->


<h4>Cookie</h4>
<pre>
	session(서버에 저장됨) vs cookie(클라이언트에 저장됨)
		: http의 stateless 특성으로 인해 커뮤니케이션 정보가 유지되지 않는 경우, 사용하는 저장소의 개념 
		
		
	[사용하는 순서]
	1단계. 쿠키 객체 생성하기 (name, value)
	2단계. 응답(헤더를 통해, set-cookie)에 포함시켜 전송
	==> 여기까지는 서버가 할일
	<%
		Cookie firstCookie = new Cookie("firstCookie", "firstValue"); // 1단계
		response.addCookie(firstCookie); //response header에 set-Cookie에 firstCookie라는 이름으로  전송
		
		
		//인코딩하는 법 알아보기 
		//값을 넣더라도 인코딩 해서 쿠키값을 넣어줘야함 
		String koreanValue = URLEncoder.encode("한글값", "UTF-8");
		Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);  
//		Cookie koreanCookie = new Cookie("koreanCookie", "한글값"); //이렇게 되면 인코딩이 안됨 
		response.addCookie(koreanCookie);


		//쿠키의 domain의 레벨구조 알아보기 
/* 		Cookie domainCookie = new Cookie("domainCookie", "domain cookie value"); //쿠키를 생성하고 있는 도메일 localhost 
		domainCookie.setDomain("www.naver.com"); //도메인에 쿠키 세팅하고 
		response.addCookie(domainCookie); //쿠키 넣기  */
		
		
		//쿠키의 Path 알아보기 
		Cookie otherPathCookie = new Cookie("otherPathCookie", "otherPathCookievalue");
		otherPathCookie.setPath(request.getContextPath() +  "/12");  
		response.addCookie(otherPathCookie);
	    //  "/12" 경로(12번폴더)에서만 otherPathCookie 쿠키 보임
	    
	    
	    //쿠키의 maxAge 알아보기 
	    Cookie longLiveCookie = new Cookie("longLiveCookie", "longLive");
	   	longLiveCookie.setPath("/"); //동일한 경로(11번 폴더)든 다른경로(12번폴더)든 서버만 같으면 어떤 경로에서든 확인할 수 있음 
	   	longLiveCookie.setMaxAge(60*60*24*7); //일주일동안 쿠키가 살아남게끔  => 60초*60분*24시간*7일 
	    response.addCookie(longLiveCookie);
	
		
		
	%>
	
	
	==> 이제부터는 클라이언트가 처리
	3단계. 브라우저가 자기 저장소에 저장  <!-- f12 application 탭에서 cookie 확인    -->
	4단계. 다음번 요청(헤더를 통해, cookie)을 통해 재전송 <!-- f12 network 탭에서 headers에서 쿠키 들어온거 확인    -->
	
	==>서버가 다시 받아서 처리
	5단계. 요청에 포함된 쿠키를 통해 상태를 복원 
	
	<%--
	
	
	
		/* CookieHttpServletRequestWrapper로 옮겨감
		Cookie[] cookies = 	request.getCookies();
		Cookie finded = null;
		
		if(cookies != null) {  // 클라이언트가 착실하게 쿠키를 받아서 저장했을거라는 보장 없음  => cookies가 null이 아니면 
			for(Cookie tmp  : cookies){
				if("firstCookie".equals(tmp.getName()) ){
					finded =tmp;  
					 break;//쿠키를 찾았다면 더이상 반복문 돌필요 없음 
				};
			}
		} 
		if(finded != null) { //finded가 널이 아니면 
			out.println("쿠키값: " + finded.getValue()); // 찾은 쿠키값 꺼내오기 
		}
		
		*/
		
//		String findedValue = new CookieHttpServletRequestWrapper(request).getCookieValue("firstCookie"); // CookieHttpServletRequestWrapper로 request값 보내기 
		String findedValue = new CookieHttpServletRequestWrapper(request).getCookieValue("koreanCookie");  
		out.println("쿠키값: " + findedValue); 
		
	--%>
	
	
	<!--  viewCookie.jsp -->
	<a href="viewCookie.jsp">동일 경로에서 쿠키확인 </a>
	<a href="../12/viewCookie.jsp">다른 경로에서 쿠키확인 </a> //.. => webapp contextPath까지 올라갔음 
	<!--  
	 11번 jsp > 쿠키 세개
	 12번 jstp > 쿠키 한개만 출력됨 
	
	-->
	
	
	
	** 쿠키 속성들 
	필수 속성
		name  : 식별자 
		value : String, url encoded value
	부가 속성
		domain(host) : 다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건 
						ex) .naver.com, www.naver.com
		
		path: 다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건 
			path 설정이 명시되지 않은 경우, 쿠키 생성 경로가 반영됨.

		maxAge : 쿠키의 만료 시한을 결정 /  기본값 : 세션 만료 시한
			ex)  0 : 기존에 저장되어있는 longLiveCookie를 삭제하는 역할
			            (name, value... 등 모든 속성이 동일한 쿠키인 경우 삭제)
				 longLiveCookie.setMaxAge(0);
				 
				 -1 : 브라우저가 꺼지면 longLiveCookie를 삭제하는 역할
				 longLiveCookie.setMaxAge(-1);
		
		secure : 안전한 상황에서만 쿠키되어야 함 
			https:// 이거 사용하는 상황에서만 가능(브라우저 주소창에 자물쇠 모양.....이어야함)
			인증서가 없기때문에 localhost에서는 못함
			openSSL 이거 필요함 


</pre>



</body>
</html>
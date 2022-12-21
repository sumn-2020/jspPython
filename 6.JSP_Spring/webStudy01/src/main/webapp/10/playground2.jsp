<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>



<!-- 크롤링 비동기요청방식으로 긁어오기   -->
<h4>네이버 크로울링 결과 긁어오기 </h4>
<form action="<c:url value='/10/proxyTarget.jsp' />" id="crawlerForm">
	<input type="url" name="target" placeholder="https://www.naver.com/" />
	<input type="checkbox" name="source" value="true" /> 소스로보기 
	<input type="submit" value="가져오기" />
</form>

<div id="container">
</div>

<script>

	let container = $("#container");
	$.ajax({
		//url : "https://www.naver.com/",  // 이렇게는 접근 안됨 SOP정책 위반 => 우리 서버(proxyTarget.jsp)를 경유해서 네이버에 접근해야됨   => 프록시방식 사용 필요
		url : "<c:url value='/10/proxyTarget.jsp' />",
		method : "get", //네이버 내용 전체 받아옴  
		dataType : "html", //네이버 html 전체를 받아와서 resp에 넣음  
		success : function(resp) { 
			container.html(resp);
			
			//네이버에서 원하는 내용만 가져오기 ()
			let content = $(resp).find("div:first").html(); //나한테 필요한 영역 찾아와서 사용가능함 
			container.html(content);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}

	});

	/* [SOP]same origin policy => 동일 출처 정책
	
	비동기요청의 대상이 현재 페이지의 출처와 동일해야됨 
	=> SOP 위반했음 naver화면 비동기로 못 긁어옴  
	이걸 보완하기 위해서 프록시 사용 => naver.jsp 로 요청을 보내서 거기서 naver페이지를 받아와야됨 
	 */

	 


	 
	 
	 
	 
	 

</script>


</body>
</html>
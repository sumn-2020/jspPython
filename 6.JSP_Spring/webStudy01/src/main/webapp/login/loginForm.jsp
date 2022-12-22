<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>


<!-- 뒷단에서 날라온 " message"를  session에서 꺼내옴 => 뒷단에서 검증 통과 못할경우 message에   "아이디나 비밀번호 누락"이라는 내용이 채워짐 -->
<c:if test="${not empty message }"> <!-- 뒷단에서 받아온 message 값이 비어있지 않다면 검증이 실패했다는 증거니까 alert 창 실행    -->
	<script>
		alert("${message}");
	</script>
	
	<!--  위 내용 실행후 session은 삭제하는 작업 필요  -->
	<c:remove var="message" scope="session" /> <!-- flash Attribute 방식  : session/application은 안지워지므로 remove해줘야됨    -->
</c:if>


</head>




<body>

<!-- 
LoginProcessControllerServlet
MemberVO

index.jsp
LogoutControllerServlet
layout.jsp
IndexControllerServlet
 -->




<!--  파라미터에 아이디 비밀번호 노출시키지 않게 하기 위해서 post 방식   -->
<form method="post" action="<c:url value='/login/loginProcess.do'/>">
	<ul>
	
		<!--  param.memId  : 파라미터 중 memId 받아서 value값에넣기  -->
		<li>
			<input type="text" name="memId" placeholder="아이디" value="${validId }"    /> 
			<input type="checkbox" name="saveId" />아이디기억하기
			<!-- 
			문제
			최대 5일까지 아이디 기억하기 
			체크하지 않고 로그인했을 경우 기존에 저장되어있는 쿠키까지 삭제 할것
			 -->
			<c:remove var="validId" scope="session" />
		</li>
		<li>
			<input type="password" name="memPass" placeholder="비밀번호" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>








</body>
</html>
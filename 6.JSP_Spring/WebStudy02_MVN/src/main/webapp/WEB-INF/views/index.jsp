<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
    
    
 <h4>웰컴</h4>
 
 <!-- 로그인 된후에 로그인 세션 그대로 유지시키기 새로고침해도  로그인 풀리면 안됨    -->
 <c:choose>
 	<c:when test="${not empty sessionScope.authMember }"> <!-- 로그인 이미 한 사람이라면   =>authMember가 안비어져있다는 것은 뒷단에서 검증을 통과했다는 뜻이니까 -->
 		<a href="<c:url value='/mypage.do'/>">${authMember.memName }님 </a>
 		<form name="logoutForm" action="<c:url value='/login/logout.do'/>" method="post"></form> <!-- /login/logout.do 서블릿으로 post방식으로 넘어감  -->
		<a href="#" class="logoutBtn">로그아웃 </a>

 		<%-- <a href="<c:url value='/login/logout.do'/>">${authMember.memId }님 로그아웃 </a> --%>
 		<!-- 로그인할때도  반드시 post방식으로 넘겨야 되는데 post방식은 form태그가 있어야됨  / 주석처리된 것은 doGet방식 -->
 		
 		<script>
 		$(".logoutBtn").on("click", function(event) {
 			event.preventDefault();
 			document.logoutForm.submit();//post방식으로 로그아웃하기 => name이 logoutForm 태그 찾아서 submit() 하기 
 			
 			return false;
 		});	
 		</script>
 	
 
 	</c:when>
 	<c:otherwise><!-- 아직 로그인 안한사람이라면   -->
 		<a href="<c:url value='/login/loginForm.jsp'/>">로그인</a>
 		<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
 	</c:otherwise> 
 	
 </c:choose>
 
 
 
 
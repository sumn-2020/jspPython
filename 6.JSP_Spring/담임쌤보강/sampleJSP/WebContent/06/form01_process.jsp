<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	
	JSTL
	JSP Standard Tag Library
	jsp에서 자주 사용되는 태그들을 묶어놓은 라이브러리 
	
 -->
<%@page import="kr.or.ddit.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
</head>
<body>

	<%
	
		request.setCharacterEncoding("UTF-8");
	
	
		MemberVO memberVO = new MemberVO();

	
		//스크립틀릿
		String userId = request.getParameter("id"); //input의 name을 받아오기 
		String passwd = request.getParameter("passwd");  
		String userName = request.getParameter("name");
		String postNum = request.getParameter("postNum");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String gender = request.getParameter("gender");
		String hobby1 = request.getParameter("hobby1");
		String hobby2 = request.getParameter("hobby2");
		String hobby3 = request.getParameter("hobby3");
		String comment = request.getParameter("comment");
		
		
		//MemberVO에 값 넣어주기 
		memberVO.setUserId(userId);
		memberVO.setPasswd(passwd);
		memberVO.setUserName(userName);
		memberVO.setPhone1(phone1);
		memberVO.setPhone2(phone2);
		memberVO.setPhone3(phone3);
		memberVO.setHobby1(hobby1);
		memberVO.setHobby2(hobby2);
		memberVO.setHobby3(hobby3);
		memberVO.setComment(comment);
		
		
		//받아온 값들을 session에 넣기
		//내장객체 = >session => 동일한 웹브라우저에서 공유
		session.setAttribute("memberVO", memberVO);
		


	%>


	<!--  value=memberVO자바의세계?에서  var="memberVO" jsp의 세계?로 넘어옴 -->
	<c:set var="memberVO" value="<%= memberVO %>" />
		<p>아이디 : ${memberVO.userId}</p>
		<p>비밀번호 : ${memberVO.passwd}</p>
		<p>이름 : ${memberVO.userName}</p>
		<p>연락처 : ${memberVO.phone1} - ${memberVO.phone2} - ${memberVO.phone3}</p>
		<p>성별 : ${memberVO.gender}</p>
		<p>취미 : ${memberVO.hobby1}, ${memberVO.hobby2}, ${memberVO.hobby3}</p>
		<p>
			<textarea rows="3" cols="30" name="comment">${memberVO.comment}</textarea>
		</p>

		<%-- <p>아이디 : <%= userId %></p>
		<p>비밀번호 : <%= passwd %></p>
		<p>이름 : <%= userName %></p>
		<p>연락처 : <%= phone1 %> - <%= phone2 %> - <%= phone3 %></p>
		<p>성별 : <%= gender %></p>
		<p>취미 : <%= hobby1 %>, <%= hobby2 %>, <%= hobby3 %></p> --%>

	<%
		//5초 후에 form01.jsp로 되돌아감
		response.setHeader("Refresh", "5;URL=form01.jsp");
	%>

	<script type="text/javascript">
		CKEDITOR.replace("comment"); //textarea의 name값을 넣어주면 해당 textarea에 에디터가 들어옴 
	</script>



</body>
</html>
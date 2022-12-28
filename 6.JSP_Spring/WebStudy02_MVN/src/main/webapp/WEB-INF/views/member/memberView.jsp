<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록조회</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<h4>회원 상세조회</h4>

	<table>
		<tr>
			<th>회원아이디</th>
			<td>${member.memId}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.memPass}</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member.memName}</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.memRegno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete}</td>
		</tr>
		
		
		<!-- 선택적 랜더링 필요 : 지금 로그인되어있는 member의 id   -->
		<!-- 
		sessionScope.authMember eq member => 원래 아래랑 결과 다른데 el에서는 동일하게 취급함 
		sessionScope.authMember.equals(member)  => id 비교 
		-->
		
		<c:if test="${sessionScope.authMember eq member }"> <!--  sessionScope에 저장되어있는 authMember와 member가 같다면  -->
			<tr>
				<td colspan="2">
					<a href="<c:url value='/member/memberUpdate.do' />" class="btn btn-primary">수정</a>
					<a href="" class="btn btn-danger">탈퇴</a>
					<form method="post" action="<c:url value='/member/memberDelete.do' />">
						<input type="password" name="memPass" />
					</form>
					<!-- <input type="button" value="수정" />
					<input type="button" value="탈퇴" /> -->
				</td>
			</tr>
		</c:if>
		

	</table>


	<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>
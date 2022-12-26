<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록조회</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>회원목록조회</h4>


<table>
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty memberList }"> <!-- memberList에 내용물이 비어있지 않다면  -->
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td>${member.memId }</td>
						<td>
							<c:url value="/member/memberView.do" var="viewURL"> <!-- 여기서 만들어진 url을 viewURL에다가 넣어서   -->
								<c:param name="who" value="${member.memId}"></c:param>
							</c:url>
							<a href="${viewURL}">${member.memName }</a>
						</td>
						<td>${member.memMail }</td>
						<td>${member.memHp }</td>
						<td>${member.memAdd1 } ${member.memAdd2 }</td>
						<td>${member.memMileage }</td>
					</tr>			
				</c:forEach>	
			</c:when>
			<c:otherwise> <!-- 아무도 가입한 회원 없을 경우  -->
				<tr>
					<td colspan="6">조건에 맞는 회원이 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>

<c:if test="${not empty message }"> <!-- 메세지 있을 경우 실행  -->
	<script type="text/javascript">
	alert("${message}") ; 
	</script>
</c:if>

</head>
<body>

<form method="post">
	<table>
		<tr>
			<th>회원아이디</th>
			<td>
				<input class="form-control" type="text" name="memId" required />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input class="form-control" type="text" name="memPass" />
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>
				<input class="form-control" type="text" name="memName" value="${member.memName}" />
			</td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td>
				<input class="form-control" type="text" name="memRegno1"  />
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<input class="form-control" type="text" name="memRegno2"  />
			</td>
		</tr>
		<tr>
			<th>생일2</th>
			<td>
				<input class="form-control" type="text" name="memBir"  />
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<input class="form-control" type="text" name="memZip"  />
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input class="form-control" type="text" name="memAdd1" />
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input class="form-control" type="text" name="memAdd2" />
			</td>
		</tr>
		<tr>
			<th>홈ㅂ전번</th>
			<td>
				<input class="form-control" type="text" name="memHometel" />
			</td>
		</tr>
		<tr>
			<th>회사전번 </th>
			<td>
				<input class="form-control" type="text" name="memComtel" />
			</td>
		</tr>
		<tr>
			<th>폰번호</th>
			<td>
				<input class="form-control" type="text" name="memHp" />
			</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>
				<input class="form-control" type="text" name="memMail" />
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<input class="form-control" type="text" name="memJob" />
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<input class="form-control" type="text" name="memLike" />
			</td>
		</tr>
		<tr>
			<th>긴</th>
			<td>
				<input class="form-control" type="text" name="memMemorial" />
			</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>
				<input class="form-control" type="text" name="memMemorialday" />
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input class="form-control" type="text" name="memMileage" />
			</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>
				<input class="form-control" type="text" name="memDelete" />
			</td>
		</tr>
	</table>
	<input type="submit" value="저장" />
</form>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>




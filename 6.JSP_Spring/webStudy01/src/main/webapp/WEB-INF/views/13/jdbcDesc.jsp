<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 

1. 드라이버 연동(아래 자바소스 안에 있는 코드)
1. vo설계 DataBasePropertyVO
DataBasePropertyControllerServlet


 -->


<h4>JDBC(Java DataBase Connectivity)</h4>

<pre>

	jdbc 드라이버란? java.sql패키지의 인터페이스들에 대한 구현 객체 모음 


	JDBC 단계 
	1. 벤더가  제공해주는  드라이버 확보
	2. 드라이버를 빌드패스에 추가
	3. 드라이버 로딩
	4. Connection수립 
	5. 쿼리 객체 생성  <!-- java가 sql문을 해석하고 컴파일할 수 있게끔해주는 중간통로인 쿼리 객체 필요 -->
		[쿼리객체의 종류]
		- Statement
		- PreparedStatement
		- CallableStatement
	6. 쿼리 실행 
		[실행할 쿼리의 종류]
		- ResultSet executeQuery  =>  DB 에서 뭔가를 받아야할 쿼리 : 반환값의 형태 필요 => select 
		- int executeUpdate =>  DB를 조작해야할 쿼리 : 반환값의 형태 필요없음 => insert update delete
	7. ResultSet 핸들링
	8. close : 세션종료 =>  try() {} 사용해서 

</pre>



<table>
	<thead>
		<tr>
			<th>property_name</th>
			<th>property_value</th>
			<th>description</th>
		</tr>
	</thead>
	<tbody>
		<td>
			<!-- not empty list 안에 내용물있는지 없는지 확인  -->
			<c:choose> 
				<c:when test="${not empty list }"> <!--  레코드가 있으면 -->
					<c:forEach items="${list }" var="propVO"> <!-- 향상된 for문 -->
						<tr>
							<td>${propVO.propertyName }</td> <!-- vo에서  propertyName값 꺼내오기  -->
							<td>${propVO.propertyValue }</td> <!-- vo에 propertyValue이름 확인 잘해봐야됨  -->
							<td>${propVO.description }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise> <!-- 조회된 레코드 하나도 없음   -->
					<tr>
						<td colspan="3">조회결과없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
			
		</td>
	</tbody>
</table>




</body>
</html>
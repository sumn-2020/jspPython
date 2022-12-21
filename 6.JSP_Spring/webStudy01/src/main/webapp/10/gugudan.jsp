<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	table * {border: 1px solid #000 !important;}
	.red {background: red;}
	.green {background: green;}
</style>
<jsp:include page="/includee/preScript.jsp"></jsp:include>

</head>
<body>

<%

	for(int i = 1; i <= 9; i++) {
		System.out.println("2*"+ i + "=" +  2*i);
	}	

%>



<!-- 문제4 min2단 max4단 선택시 2~4단만 생기게 만들기 -->

<!--  minDan : param.min이라는 파라미터가 있으면 param.min 쓰고 없으면 2 -->	
<!--  maxDan : param.max 파라미터가 있으면 param.min 없으면 9 -->
<c:set var="minDan" value="${empty param.min  ? 2 : param.min }" /> 
<c:set var="maxDan" value="${not empty param.max ? param.max : 9 }" /> 

<%-- 
${dan1 eq minDan ? "selected" : ""}
 => 현재 옵션이 minDan이면 selected  
--%>
<form>
	<select name="min">
		<c:forEach var="dan1" begin="2" end="9"  >
			<option value="${dan1 }"  ${dan1 eq minDan ? "selected" : ""}>${dan1 }단</option>
		</c:forEach>
	</select>
	<select name="max">
		<c:forEach var="dan2" begin="2" end="9"  >
			<option value="${dan2 }" ${dan2 eq maxDan ? "selected" : ""}>${dan2 }단</option>
		</c:forEach>
	</select>
	<button type="submit">SUBMIT</button>
</form>

<table>
	<%--  이게 위로 올라가면 form에도 이걸 쓸수있음 
	<c:set var="minDan" value="${empty param.min  ? 2 : param.min }" /> 
	<c:set var="maxDan" value="${not empty param.max ? param.max : 9 }" />  
	--%>

	<c:forEach var="i" begin="${minDan }" end="${maxDan }"  varStatus="vs">
	
		<c:choose>
			<c:when test="${vs.count eq 3 }">
				<c:set var="clzAttr" value="red" />
			</c:when>
			<c:when test="${vs.last }">
				<c:set var="clzAttr" value="green" />
			</c:when>
			<c:otherwise>
				<c:set var="clzAttr" value="normal" /> <!-- otherwise안쓰면 3번째 이후로부터 다 빨강색으로 적용됨  -->
			</c:otherwise>
		</c:choose>
		<tr class="${clzAttr }">
	
	
		<c:forEach var="j" begin="1" end="9" >
			<td>${i }*${j } = ${i*j }</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>
<script>
//select  선택 후 값 고정시키기 
//$('[name=min]').val("${minDan}"); //파라미터값이 없다면 minDan은 2를 가지고 있을 거임 2가 출력될것임
//$('[name=max]').val("${maxDan}");
</script>






<!-- 문제1. JSTL과 EL을 이용하여 2단부터 9단까지 구구단 출력 (table 태그 활용)(2*3=6) -->
<!-- 문제2. 세번째 row의 배경색을 빨간색으로 출력 (inline css 속성 사용하지 말 것)-->
<!-- 문제3. 마지막 row의 배경색 초록색으로 -->
<%-- <table>
	<c:forEach var="i" begin="2" end="9"  varStatus="vs">
	
		<c:choose>
			<c:when test="${vs.count eq 3 }">
				<c:set var="clzAttr" value="red" />
			</c:when>
			<c:when test="${vs.last }">
				<c:set var="clzAttr" value="green" />
			</c:when>
			<c:otherwise>
				<c:set var="clzAttr" value="normal" /> <!-- otherwise안쓰면 3번째 이후로부터 다 빨강색으로 적용됨  -->
			</c:otherwise>
		</c:choose>
		<tr class="${clzAttr }">
	

		<c:forEach var="j" begin="1" end="9" >
			<td>${i }*${j } = ${i*j }</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table> --%>

<%-- <table>
	<c:forEach var="i" begin="2" end="9"  varStatus="vs">
		<c:choose>
			<c:when test="${vs.count eq 3 }"> <tr class="red"></c:when>
			<c:when test="${vs.last }"><tr class="green"> </c:when>
			<c:otherwise><tr></c:otherwise>
		</c:choose>
		<c:forEach var="j" begin="1" end="9" >
			<td>${i }*${j } = ${i*j }</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table> --%>



































</body>
</html>
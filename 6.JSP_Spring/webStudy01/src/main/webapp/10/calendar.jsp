<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%
	//DateFormatSymbols dfs = DateFormatSymbols.getInstance();
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.CHINESE); //달력 출력하기 
	
	String[] weekDays =  dfs.getWeekdays(); //요일받아오기 
	pageContext.setAttribute("weekDays", weekDays);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="dayCount" value="1" /> <!-- 속성(변수X)을 하나 만들겠다.  초기값 : 1 -->
<c:set var="offset" value="4" />


<table>
	<thead>
		<tr>
			<c:forEach var="idx"  begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
				<td>${weekDays[idx] }</td> <!-- 저 상단에 있는 getWeekdays에서 가져온거  -->
				<!-- 해당데이터의 index가 상수를 따라감???? -->
			</c:forEach>		
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="1" end="6">
		<tr>
			<c:forEach begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
				<%-- <td>${dayCount }</td> --%> <!-- 계속 1만출력됨  -->
				<td>${dayCount - offset }</td> <!-- <td>${dayCount - 4 }</td> -->
				<c:set var="dayCount" value="${dayCount+1 }" /> <!-- 1씩 계속 증가  -->
				
			</c:forEach>
		</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>
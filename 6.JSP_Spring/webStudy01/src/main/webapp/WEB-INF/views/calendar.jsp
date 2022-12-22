<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%-- CalendarWrapper.java로 넣어 놓음
	//DateFormatSymbols dfs = DateFormatSymbols.getInstance();
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.CHINESE); //달력 출력하기 
	String[] weekDays =  dfs.getWeekdays(); //요일받아오기 
	pageContext.setAttribute("weekDays", weekDays); 
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>



</head>
<body>

<!--  
CalendarControllerServlet
CalendarWrapper.java
 -->

<c:set var="dayCount" value="1" /> <!-- 속성(변수X)을 하나 만들겠다.  초기값 : 1 -->
<c:set var="offset" value="${calendar.offset }" /> <!--  뒷단에서 받아온 Calender의 offset값   -->
<c:set var="lastDate" value="${calendar.lastDate }" /> <!--  뒷단에서 마지막날짜(calendar.lastDate) 받아오기 -->
<c:set var="weekDays" value="${calendar.weekDays }" /> <!--  뒷단에서 요일(calendar.weekDays) 받아오기 -->
<c:set var="months" value="${calendar.months }" /> <!--  뒷단에서 월(calendar.months) 받아오기 -->

<h4> 
	<!-- calendar.beforeYear , calendar.beforeMonth ... 뒷단에서 가져온 값들 CalendarWrapper   -->
	<!-- 파마미터에 url없이 그냥 ?만 넣으면 현재 calendar.do 그대로 출력됨. CalendarControllerServlet컨트롤러에 파라미터 넘어감  -->
	<%-- 
	<a href="?year=${calendar.beforeYear }&month=${calendar.beforeMonth }">이전달</a>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${calendar }  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="?year=${calendar.nextYear }&month=${calendar.nextMonth }">다음달</a>
	 --%>
	
	<a href="#" class="moveBtn" data-year="${calendar.beforeYear }" data-month="${calendar.beforeMonth }">이전달</a>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${calendar }  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="moveBtn"  data-year="${calendar.nextYear }" data-month="${calendar.nextMonth }">다음달</a>
	<!-- 다음달 이전달 클릭시 파라미터 두개(year, month)만 넘어가는거를 세개(year, month, language)로 넘어가는 걸로 변경하기   -->
	<!-- form태그의 입력태그가 세개일경우 넘어가는 파라미터 값도 항상 세개여야함   -->
	<!--
	 요청발생하는 창구가 여러개면 일일히 전부 변경이 있을때마다 모든 창구 수정해줘야됨 
	 요청이 발생하는 창구를 하나로 통일시켜야됨 (다음달 이전달 a태그가 요청을 발생할수없도록 막아버리고 아래에 있는 form태그만 요청을 발생시킬수있도록 하자)   -->
	
	<!--  data-year=calendar.beforeYear / data-month=calendar.beforeMonth  : 전달의 버튼인지 다음달의 버튼인지 확인하기 위해 year, month의 정보를 담아둠  -->


</h4>


<form id="calendarForm">

	<!-- 년도와 월 선택후 전송클릭시 달력 변경하기   -->
	<input type="number" name="year" placeholder="2022" value="${calendar.currentYear }" /> <!-- name="year"라는 이름으로 파라미터 넘겨줌  / 뒷단에서 calendar.currentYear값 가져옴 -->
	<select name="month"><!-- name="month"라는 이름으로 파라미터 넘겨줌 -->
		<c:forEach items="${months }" var="monthTxt" varStatus="vs"> <!-- 향상된 for문 monthTxt가 유효한 값을 갖고있을때까지 for문 돌리기 / 인덱스가 사라졌으니까 varStatus 를 사용하면됨 -->
			<c:if test="${not empty monthTxt}"> <!-- monthTxt가 공백이 아니라면  -->
			
				<c:set var="selected" value="${vs.index eq calendar.currentMonth ? 'selected' : ''}" /> <!-- vs.index(0~11)와 calendar.currentMonth가 같으면 selected  아니면 whitespace  -->
				<option value="${vs.index }"  ${selected } >${monthTxt }</option>
				
			</c:if>
		</c:forEach>
	</select>
	
	<!-- ?language=en 이렇게 파라미터 값이 넘어가고 싶을 경우 -->
	<select name="language">
		<%-- <%
			Locale.getAvailableLocales(); //locale의 배열 가져오기 
			Locale tmp;
			tmp.toLanguageTag() //locale의 코드값들 
		%> --%>
		<c:forEach items="${Locale.getAvailableLocales() }" var="tmp"> <!--tmp가 locale정보 하나하나를 가짐  -->
			<c:if test="${not empty tmp.displayLanguage }"> <!-- displayLanguage가 공백이 아닐때만 출력 -->
				<c:set var="selected" value="${tmp eq calendar.locale ? 'selected' : '' }" /> 
				<!-- 선택된 언어가 selected 효과 나타나게 하기   : locale과 현재 locale과  동일하면  selected   -->
				<!-- calendar.locale => 뒷단에서 객체를 만들어줘야됨   -->
				<!-- <option value="en_US">영어(미국)</option> -->
				<option value="${tmp.toLanguageTag() }"  ${selected }>${tmp.displayLanguage }(${tmp.displayCountry })</option>
			</c:if>
		</c:forEach>
		
	</select>

	<input type="submit" value="전송" /> 
</form>
<!--  //년도와 월 선택후 전송클릭시 달력 변경하기   -->



<table>
	<thead> <!-- 월~금 출력 -->
		<tr>
			<!-- 뒷단 calendar객체에서 받아온 SUNDAY~SATURDAY가 끝날때까지 반복하여  idx변수에 담기 -->
			<!-- SUNDAY~SATURDAY은 ENUM으로 숫자로 저장되어있음  -->
			<c:forEach var="idx"  begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>"> 
				<td>${weekDays[idx] }</td> <!-- 저 상단에 있는 getWeekdays에서 가져온거  -->
			</c:forEach>		
		</tr>
	</thead>
	<tbody>
	
	
		<c:forEach begin="1" end="6"> <!-- 공통적으로 모든 달에서 숫자가 있는 줄은 총 6줄 세로로! => 세로로 숫자출력하는거 반복 -->
		<tr>
			
			<!-- 
				날짜출력
				1보다 적으면 출력할 필요 없고
				lastDate가 31보다 크면 출력할 필요 없음  
			--> 
		
			<c:forEach begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">  <!-- SUNDAY~SATURDAY가 끝날때까지 반복 : 가로로 숫자 출력하는 거 반복   -->
				<c:set var="dayStr" value="${dayCount - offset }"/> <!--    1에서 현재 요일만큼 뺀 값을 dayStr에 넣고 =>해당월의 1일과 일요일사이에 몇칸이 벌어지는지 알수있음    -->
				<c:choose>
					<c:when test="${dayStr gt 0 and dayStr le lastDate}"> <!-- 0보다 크고 lastDate보다 적거나 같으면  -->
						<td>${dayStr }</td> <!-- <td>${dayCount - offset }</td>  -->
					</c:when>
					<c:otherwise>
						<td>&nbsp;</td>
					</c:otherwise>
				</c:choose>
				<c:set var="dayCount" value="${dayCount+1 }" /> <!-- 1씩 계속 증가  -->
			</c:forEach>
			
			
			
		</tr>
		</c:forEach>
		
		
		
	</tbody>
</table>



<script>

let calendarForm = $("#calendarForm");

$("a.moveBtn").on("click", function(event){
	//a태그의 data-year와 data-month 내용물을 뽑아옴 
	let year = $(this).data("year");
	let month = $(this).data("month");
	
	calendarForm.find("[name=year]").val(year); //calendarForm에서 year라는 name값을 찾아서 거기의 value값에 $(this).data("year")값이 들어감  
	calendarForm.find("[name=month]").val(month); 
//	calendarForm.get(0).month.value = month; // calendarForm에서  month라는 이름을 가진 값의 value에다가 month 넣기  
	

	//a태그를 클릭해지만 실제로 전송되는건 form태그임 
	calendarForm.submit();
	
});



</script>


</body>
</html>
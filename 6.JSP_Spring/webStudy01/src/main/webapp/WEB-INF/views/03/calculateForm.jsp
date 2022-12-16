
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사칙연산기 - background로 돌아가는</title>

<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/custom.js"></script>


</head>

<body>


CalculateVO
CalculateServlet
OperatorType
RealOperator

<input type="radio" name="dataType" value="json" />JSON
<input type="radio" name="dataType" value="xml" />XML


<!-- action이 없을 경우: 이때 action은 서블릿 주소   -->
<form method="post" id="calForm">
	<input type="number" name="leftOp" placeholder="좌측피연산자">
	<select name="operator">
		<option value="PLUS">+</option>
		<%
			OperatorType[]  operators = OperatorType.values(); //OperatorType.java
			//operators의 length는 4
			for(OperatorType tmp : operators) {
				%>
					<option value="<%=tmp.name() %>"><%=tmp.getSign()%></option>
				<% 
			}
		%>
<!-- 		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option> -->
	</select>
	<input type="number" name="rightOp" placeholder="우측피연산자">
	<button type="submit" id="submit">=</button>
</form>

<div id="resultArea">


</div>




<script>

	let resultArea = $("#resultArea");


	$("#calForm").on("submit", function(event) {
		event.preventDefault();
		
		
		let url = this.action; 
		let method = this.method;
		let data = $(this).serializeObject();  /*  custom.js 직렬화하기   */
		data.leftOp = parseInt(data.leftOp); //문자열을 숫자로 바꾸기 
		data.rightOp = parseInt(data.rightOp); 
		
		$.ajax({
			url : url, 
			method : method, 
			contentType : "application/json",
			data : JSON.stringify(data),
			dataType : "json", 
			success : function(resp) { 
				resultArea.html(resp.expression);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});
		
		
		return false;
	});

	

</script>







</body>
</html>







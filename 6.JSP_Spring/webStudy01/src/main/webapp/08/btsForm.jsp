<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<!-- 


btsLayout.jsp
BTSServlet
BTSMemberServlet
WEB-INF > views > bts 


 -->

	<select name="member">
		<option value>멤버선택</option>
	</select>
	

	<script>

		let memberSelect = $('[name="member"]').on('change', function(event) {
			let code = $(this).val(); //값을 꺼낼때
			<%-- location.href = "<%=request.getContextPath() %>/bts/" + code; --%> /*  동기  */
		   $.ajax({
		         url : "<%=request.getContextPath() %>/bts/" + code,
		         dataType : "html",
		         success : function(resp) {
		            memberSelect.after(resp);
		         },
		         error : function(jqXHR, status, error) {
		            console.log(jqXHR);
		            console.log(status);
		            console.log(error);
		         }
		      });

			
			
		});
		
		
		$.ajax({
			url : "<%=request.getContextPath() %>/bts", 
			dataType : "json", 
			success : function(resp) { 
				
				let options = [];
				$.each(resp.bts, function(code, values) { //resp.bts ㅡ > Map 형태로 들어옴 
					let option = $("<option>").val(code)
								 			  .text(values);
				
					options.push(option);
				});
				memberSelect.append(options);
				
				
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});
	
	</script>



<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>
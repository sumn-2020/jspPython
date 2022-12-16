<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- standard.jsp -->


<h4>out : JspWriter </h4>
<pre>

	버퍼사용하는 이유
	1) 속도가 빠르다
	2) 버퍼를 flush하기 전까지는 버퍼 속 내용을 언제든 내용 바꿀수 있음 
	   out.flush();하고 난 뒤에는 버퍼 속 내용 바꿀 수 없음 


	출력 버퍼 관리자 
	현재 버퍼의 크기 : <%=out.getBufferSize() %>
	잔여 버퍼의 크기 : <%=out.getRemaining() %>
	auto flush 지원여부 : <%=out.isAutoFlush() %>
	<%
		for(int i = 1; i<=100; i++){
			out.println(i+"번째 반복"); // = 버퍼에 계속 기록하겠다 
			if(out.getRemaining()<50) //잔여버퍼의 크기가 50바이트 미만이면 
				out.flush();
			if(i==100)
//				throw new RuntimeException("강제 발생 예외"); //강제로 에러 발생시키기 
//				request.getRequestDispatcher("/02/standard.jsp").include(request, response);
				//forward : b에서 나가는 거기때문에 버퍼를 한번 지워야 됨 
				//include: a->b->a에서 나가기 때문에 따로 버퍼를 지워줄 필요 없음 
				
				pageContext.include("/02/standard.jsp", false); 
				//false : flush를 하지 않은 상태에서 include 
				//true: flush를 하고 난 후 include
		
		}
	%>


</pre>

</body>2
</html>
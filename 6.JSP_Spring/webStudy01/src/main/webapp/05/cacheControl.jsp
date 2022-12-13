<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>Cache 제어</h4>
<pre>
	cache란? 시스템 내부에서 발생하는 속도 저하를 커버하기 위한 임시 저장 데이터
	Pragma(1.0 ver), Cache-Control(1.1 ver) : 캐싱 정책 설정용  
		no-cache(일단 저장은 하되) : 캐시 데이터 사용 전 확인 절차를 거치도록 함
		no-store(아예 저장하지 않음)
		must-revalidate  : 시한이 만료된 캐시 데이터 사용 전 확인 절차를 거치도록 함
		public  : (캐싱하되 공용으로 캐싱해라) 클라이언트, 프록시 서버에 캐싱  
		private : 클라이언트에만 캐싱이 됨
		ex) public;maxages=milliseconds (공용으로 캐싱하는데, 그 데이터는 밀리세컨드(=일주일)만큼만 유효)
		
		
	Expires : 캐싱 데이터 만료 시한 설정용(구체적인 날짜/시점을 제시함)
	<%
		response.setHeader("Pragma", "no-store"); //version 1.0 
		response.setHeader("Cache-Control", "no-store"); //version 1.1
		
		response.addHeader("Pragma", "no-cache"); 
		response.addHeader("Cache-Control", "no-cache"); 
		//setHeader는 뒤에 나온게 앞에걸 덮어씌우므로 addHeader로 추가해줘야됨

		
		response.setDateHeader("Expires", 0); //70년 1월 1일 0시0분0초		
		
	%> 


</pre>



</body>
</html>
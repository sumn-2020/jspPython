<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>EL(Expression Language)</h4>
<pre>
	:표현(속성 데이터 출력O, 데이터 조작X)을 목적으로 하는 스크립트 형태의 언어 
	-> 일반적인 데이터는 el과 사용불가능. 속성데이터만 el과 쓸수있음
	-> 그래서 제어문의 형태가 없음. 
	-> el문 가지고는 if문 사용할 수 없어서 jstl과 같이 쓰임 
	
	
	
	<%
		String data = "데이터";
		pageContext.setAttribute("attr-Data", data);
	%>
	
	1. 속성(=EL변수) 접근 방법(* EL 변수란? scope안에 저장되어있는 데이타)

		"<%=data %>" 이걸 el로 바꾼다면 ? ${data }
		
		현재 String data = "데이터" 이건 변수임, 속성이 아님 
		이걸 속성화시켜야됨 => pageContext.setAttribute("attrData", data);
		
		"${attr-Data }"
		
	
	/////////////////////////////////	
	
	pageScope, requestScope, sessionScope, applicationScope
	이 네개의 객체를 el로 활용한다면? 
	${pageScope.attr-Data } or ${pageScope['attr-Data'] }
	

	/////////////////////////////////
	1번방식이 제일 빠르긴하지만 3번방식이 제일 안전함
	(방식1)${attr-Data }
	(방식2)${pageScope.attr-Data }
	(방식3)${pageScope['attr-Data'] }




	2. 연산자 종류
		1)산술연산자 : +-*/% 
			${3+4 }, ${"3"+4 }, ${"3" + "4" }	=> 전부 다 결과값 : 7
			
			${attr + 4  } => 결과값 4
			4개의 scope에서 attr이라는 연산 찾으려고 시도 -> attr 없음 -> 0 반환 결과적으로 4만 출력
			
			\${"a"+ 4 }  
			=>앞에 역슬래쉬 없으면 500에러  /  결과값: \${"a"+ 4 }
			
			${4/3 } => 결과 1.3333333333333333
			${attr*data } => 결과값 0
			
	
	
			
		2)논리연산자 : &&(and, short-curkit연산자), ||(or, short-curkit연산자), !(not)
		(***short-curit연산자 : 왼쪽에 있는 피연사자가 true면 오른쪽거는 볼 필요도 없는 거)
			${true and true }  => 결과값  true
			${"true" and true }  => 결과값  true
			${true or "false" } => 결과값  true
			${false or attr } = > 결과값 false
			${attr } => 결과값:  없음
			${not attr } => 결과값: true  (attr이 값이 없으니까 false인데 그걸 not = > true)
			


			
		3)비교연산자 : &gt;(gt), &lt;(lt), >=(ge), <=(le), ==(eq), !=(ne)
			${3 lt 4 } => 결과값: true (3<4)
			${4 ge 3 } => 결과값 : true
			${4 eq 3 } => 결과값 : false
			${4 ne 3 } => 결과값 : true
		
		
	
		
		4)단항연산자: empty ( exists존재하는지 여부 판단 => null여부 판단 => length나 size체킹 => 그 다음에 비어있는지 여부 판단 )
		           jstl 조건문하고 많이 활용됨
		          
		          
			
<%-- 		

			<% pageContext.setAttribute("attr", null); %>
			${empty attr } => attr이 비어있는지 보겠다. null이라는 값 들어옴 결과값 : true
			
				
			<% pageContext.setAttribute("attr", " "); %>
			${empty attr } => attr이 비어있는지 보겠다. 한 칸 공백 결과값 : true		
			=>length가 1이면 그냥 아예 없는 걸로 취급함. 1이상 이어야 값이 있는 걸로 취급함
			
			
--%>

			<% pageContext.setAttribute("attr", "     "); %>
			${empty attr } => attr이 비어있는지 보겠다. 값이 있음  : false 
			

			////////////////////////////////////
				
			<%
				//pageContext.setAttribute("listAttr", Arrays.asList());
				pageContext.setAttribute("listAttr", Arrays.asList("a", "b"));
			%>
			list attr : ${not empty listAttr } => 값이 있는지 확인
			
			

		
		5)삼항연산자 : 조건식 ? 참표현 : 거짓표현
			${empty attr ?  "attr 비어있음" : attr} => 결과값 : 공백 세칸 
			attr이 비어있으면 ? "attr 비어있음"
			안비어있으면 attr 값 출력 
			
			${not empty attr ?  "attr 비어있음" : attr} => 결과값 : attr 비어있음 
		
			${listAttr }, ${not empty listAttr ? listAttr : "기본값" }

	
	
	
	
	3. (속성)객체에 대한 접근법
		<%
			MemoVO memo = new MemoVO();
			pageContext.setAttribute("memoAttr", memo);
			memo.setWriter("작성자");
		%>
		el을 통해서 memoVO에 접근해보기
		${memo } => 이렇게 접근불가 
		 
		${memoAttr } => 이렇게 했을때만 접근 가능 
		${memoAttr.writer }
		${memoAttr['writer'] }
	
	 
	4. (속성)집합 객체에 대한 접근법 : <a href="elCollection.jsp">elCollection.jsp</a>
	<!-- elCollection.jsp -->
	
	
	5. el의 기본객체  : <a href="elObject.jsp">elObject.jsp</a>
	<!--  elObject.jsp -->
	
	
	
	
	
	
	


</pre>

</body>
</html>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//session.setAttribute("memberVO", memberVO);
	//session.getAttribute() => Object가 리턴됨 => 형변환 필요
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>

<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>


<script>
$(function() {
	$('#btnDupChk').on('click', function() {
		//input 태그 중에 name이 id인 요소를 select 
		let id = $("input[name='id']").val();
		console.log("id : " + id);
		
		
		if(id=="") {
			alert("아이디를 입력해주세요");
			$("input[name='id']").focus();
		}
		
		if(id == "a001" || id == "b001" || id == "c001") {
			alert("아이디가 중복 되었습니다");
			$("input[name='id']").val("");
			$("input[name='id']").focus();
		}
		
		$("#postBtn").on("click", function() {
			$("input[name='postNum']").val("332211");
		});
			
	});
});
</script>



<script>
function myclick(){
	const inputId = document.querySelector('#inputId');
	const inputPw = document.querySelector('[name=passwd]');
	
	
	if(inputId.value == 'a001' || inputId.value == 'b001' || inputId.value == 'c001') {
		alert('아이디가 중복되었습니다.');
		inputId.focus();
		inputId.value=null;
	}else {
		inputPw.focus();
	}
}
</script>
			


</head>
<body>
	
	<!-- 
		input 태그 정리
		1. type
			- text : 한 줄 텍스트 입력
			- radio : 라디오 버튼 중 하나만 선택
			- checkbox : 다중선택
			- password: 암호 입력
			- hidden : 숨기기
			- file : 파일업로드
			- button : 버튼 모양
			- reset : 폼데이터 초기화
			- submit: 서버로 전송 

		2. name: 입력 양식 식별(유일함)
		3. value: 초깃값 설정 

	 -->

	
	<h3>회원가입</h3>
	<form action="form01_process.jsp" name = "member" method="post">
		<!--  
		required : 필수 
		autofocus: 자동퍼커스
		 -->
		<p>
			아이디: <input type="text" id="" name="id" required placeholder="아이디를 입력해주세요" autofocus  value="${memberVO.userId}"  />
			<!-- 
			클릭하여 a001, b001, c001이 아닌지 검사하여 중복되었다면  "아이디가 중복되었습니다"를 alert하고 
			아이디 입력란을 초기화한 후 autofocus 처리해보자
			-->
			<input type="button" value="아이디 중복 검사" id="btnDupChk"  />
			
		</p>
		<p>비밀번호:<input type="password" name="passwd" required placeholder="비밀번호를 입력해주세요" value="${memberVO.passwd}"></p>
		<!--  최대 글자수 => maxlength  -->
		<!-- 입력 양식 너비 설정 => size  -->
		<p>이름:<input type="text" name="name" maxlength="7" size="7" required id="name" value="${memberVO.userName}"></p>
		<p>	
			<!-- 읽기전용 => 단지 읽기만 함 => readonly  -->
			우편번호:<input type="text" name="postNum" readonly required  value="${memberVO.postNum}" />
			<!-- 검색버튼클릭 => 우편번호 입력란에 332211을 입력해보자  -->
			<button id="postBtn">우편번호 검색</button>
		</p>
		<p>
			연락처 : <input type="text" maxlength="4" size="4" name="phone1"  required value="${memberVO.phone1}" />
			- <input type="text"  size="4" name="phone2" required  value="${memberVO.phone2}" />
			- <input type="text" maxlength="4" size="4" name="phone3"  required value="${memberVO.phone3}" />
		</p>
		
		<!-- checked 속성: 기본값 미리 선택. radio / checkbox에서만 사용가능   -->
		<p>
			성별 : <input type="radio"  name="gender"   value="남성" 
				<c:if test = "${memberVO.gender == '남성'}">checked</c:if>
			 />남성
			 <input type="radio" name="gender"   value="여성"  
			 	<c:if test = "${memberVO.gender == '여성'}">checked</c:if>
			 /> 여성
		</p>
		<p>
			 취미 : 독서<input type="checkbox"  name="hobby1" value="독서" 
			 	<c:if test = "${memberVO.hobby1 == '독서'}">checked</c:if>
			 />
			 운동<input type="checkbox"  name="hobby2" value="운동"  
			 	<c:if test = "${memberVO.hobby2 == '운동'}">checked</c:if>
			 />
			 영화<input type="checkbox"  name="hobby3" value="영화" 
			 	<c:if test = "${memberVO.hobby3 == '영화'}">checked</c:if>
			  />
		</p>
		
		<p>
			<!-- rows:  줄 수 / cols : 열 수   -->
			<textarea rows="" cols="" name="comment" placeholder="가입 인사를 입력해주세요">${memberVO.comment}</textarea>
		
		</p>
		
		<p>
			<!-- 폼데이터에 내용이 채워져야지만 활성화   -->
			<!-- 비활성화 =>  disabled  -->
			<input type="submit"  value="가입하기" />
			<!-- reset : 폼데이터의 데이터를 초기화  -->
			<input type="reset"  value="다시쓰기" />
		</p>
	</form>
	
	
	
	
	
	<script type="text/javascript">
		CKEDITOR.replace("comment"); //textarea의 name값을 넣어주면 해당 textarea에 에디터가 들어옴 
	</script>
	
</body>
</html>
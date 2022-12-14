<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--
방법1(동기요청)
 <meta http-equiv="Refresh" content="5;url=https://www.naver.com">  -->
<title>Insert title here</title>

<style type="text/css">
	.disabled {
		display: none;
	}
</style>



<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.min.js"></script>


</head>
<body>

 GetServerTimeControllerServlet 

<h4>Refresh 헤더 활용</h4>
<%--
	//Refresh : 화면전체가 다시 그려진다(동기요청). 화면전체의 lock을 건다. 
	
	//방법2(동기요청)
	//<meta http-equiv="Refresh" content="1"> 와 같은 역할임
	response.setIntHeader("Refresh", 1); // 1초 단위로 갱신하겠다. 
--%>

<pre>
	서버의 갱신 데이터 확보(동기요청구조)
	1. Refresh response header
	2. meata tag
	3. reload
</pre>


<%-- <h4>현재 서버의 시간: <span><%=new Date() %></span></h4> --%>
<!-- <h4>현재 서버의 시간: <span id="timeArea" data-job-id></span></h4> -->
<h4>현재 서버의 시간: <span id="timeArea"></span></h4>
<!-- <input type="text" placeholder="기록필드" /> -->
<button class="controlBtn" data-control-type="START">시작</button>
<button class="controlBtn disabled" data-control-type="STOP" >멈춤</button>

<input type="radio" name="dataType" data-data-type="json"  />JSON
<input type="radio" name="dataType" data-data-type="text"  />PLAIN

<input type="radio" name="locale"  value="<%=Locale.KOREAN.toLanguageTag() %>" checked />한국어
<input type="radio" name="locale"  value="<%=Locale.ENGLISH.toLanguageTag() %>"  />영어
<input type="radio" name="locale"  value="<%=Locale.JAPANESE.toLanguageTag() %>"  />일어
<!-- <input type="radio" name="locale"  value="jp"  />일어  -->


<script>
	
	//방법3(동기요청)
/* 	setTimeout(() => {
		location.reload();
	}, 1000); */
	
	
	let timeArea = $("#timeArea");
	let dataTypes = $('[name="dataType"]'); //dataType이라는 name을 가진 input들 : JSON, PLAIN
	let locales = $('[name="locale"]'); //locale이라는 name을 가진 input들 : 한국어, 영어, 일어
	
	let successes = {
		json:function(resp) {
			timeArea.html(resp.now);
		},
		text: function(plain) {
			timeArea.html(plain);
		}
	}
	
	//이 함수만 1초에 한번씩 반복해주기 위해서 함수로 묶었음
	//이렇게 하지 않으면 계속 페이지가 전체적으로 1초씩 반복되기 때문에 
 	let sendRequest = function() { 
		
//		2단계: dataType 라디오 버튼의 선택 조건에 따라 비동기 요청 헤더(Accept) 설정
// 		--> dataType에 따라 sucess함수의 형태가 달라짐
		let dataType = dataTypes.filter(":checked").data('data-type');
		if(!dataType) {//만약 dataType이 없으면
			dataType = "json";
			dataTypes.filter("[data-data-type=json]").prop("checked", true);
		}


//		3단계 : locale 라디오 버튼의 선택 값에 따라 비동기 요청의 locale 파라미터가 결정됨
		let locale = locales.filter(":checked").val();
		let data = {
				//locale:locale 
				//a(넘겨야 할 파라미터 이름) : b(let locale)
		}
		if(locale) { //locale이 있다면
			data.locale = locale;
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/05/getServerTime",
			data:data,
			dataType : dataType, 
			/* success : function(resp) { 
				timeArea.html(resp.now);
			}, */
			success : successes[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	};
	
	
	
// 	1단계: 컨트롤 버튼에 대한 클릭이벤트 처리
   let controlBtns = $(".controlBtn").on("click",function() { //시작 멈춤 버튼을 클릭했을 시 
	   
//	   $(this).prop("disabled", true); //disabled -> abled
 	   //attr("disabled","disabled") = >문자열로 취급함
	   //prop("disabled", true)
	   controlBtns.toggleClass("disabled");

	   let controlType =  $(this).data("controlType");
	   if(controlType=="START"){
			//	컨트롤버튼 타입이 START면
			//		시계작동
		   let jobId = setInterval(sendRequest, 1000);
		   timeArea.data("jobId", jobId); //timeArea에 데이터타입추가 data-job-id = setInterval(sendRequest, 1000) 로 변경 
	   }else {
			//	컨트롤버튼 타입이 STOP면
			//		시계멈춤
			let jobId = timeArea.data('jobId');
			if(jobId) { //jobId가 있을 경우 
				clearInterval(jobId);
				timeArea.data("jobId", null); //시간 멈춤
			}
	   }


   });
	
	
	
	
	

	
	
	
	



	
	
	
	
	
	
	
	

	
	



</script>



</body>
</html>
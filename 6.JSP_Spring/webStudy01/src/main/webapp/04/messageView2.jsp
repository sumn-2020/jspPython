<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/messageView.jsp</title>

<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>



</head>
<body>

<!--  Message_ko.properties  / GetMessageControllerServlet  -->


<select id="selName">
 	<option>prop1</option>
	<option>prop2</option>
	<option>prop3</option>
	<option>prop4</option>
	<option>prop5</option>
	<option>hi</option> 
</select>

<h4 id="greetingArea" data-key1="prop1" data-key2="sample" data-other-key = "TEST"></h4> 
<!-- 태그 하나에 데이터 속성(=엔트리) 두개 : 
엔트리의 key: key1, key2, other-key(otherKey: 카멜표기법은 html에서 인식안됨/other-key라고 써놓으면 알아서 otherKey로 인식됨)
엔트리의 value: hi, sample, TEST 
 -->
 
<input type="radio" name="dataType" data-data-type="json" checked />JSON
<input type="radio" name="dataType" data-data-type="xml" />XML
<input type="radio" name="dataType" data-data-type="text"  />PLAIN
 
<input type="radio" name="language" data-locale="ko" />한국어
<input type="radio" name="language" data-locale="en" />영어


<script>

	let greetingArea = $("#greetingArea");
	console.log(greetingArea.data("key1"));
	console.log(greetingArea.data("otherKey"));
	greetingArea.data("key2", "otherValue"); //key2의 value값을 otherValue로 변경해라 
	console.log(greetingArea.data("key2"));
	
	let radioBtns = $('[type="radio"]');
	let dataTypes = radioBtns.filter("[name=dataType]"); //dataTypes을 가진 라디오버튼만 골라냄 
	let locales = radioBtns.filter("[name=language]"); //language을 가진 라디오버튼만 골라냄 
	
	let successes = {
		json : function(resp) { //json이라는 응답데이터가 오면 
			console.log(resp);
			greetingArea.html(resp.message);	
		},
		xml : function(domResp) { //xml이라는 응답데이터가 오면 돔 구조를 가지고 놀면됨 
			console.log(domResp);
			let message = $(domResp).find("message").text();
			greetingArea.html(message);
		}, 
		text: function(plain) {//text이라는 응답데이터가 오면 plain를 가지고 놀면됨 
			console.log(plain);
			greetingArea.html(plain);
		}
	}

	let settings = {
			url : "<%=request.getContextPath() %>/04/getGreetingMessage", 
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
	};
	
	radioBtns.on("change", function() {
		greetingArea.empty(); // = greetingArea.html("");
		let dataType = dataTypes.filter(":checked").data("dataType"); //체크 되어있는 놈들의 data-type
		settings.dataType = dataType;
		settings.success = successes[dataType]; //successes.json(successes['json']), successes.xml(successes['xml']), successes.text(successes['text'])	
		
		settings.data = {
//			name:greetingArea.data("key1")
			name:greetingArea.attr("data-key1")
		}
		let locale = locales.filter(":checked").data("locale");
		if(locale){
		/* 	settings.data = {  // if문이 true일때만 data가  존재하게됨 
				locale : locale // 파라미터 이름 : 파라미터 값 
			} */
			settings.data.locale=locale;
		}
		
		console.log("======================");
		console.log(settings);
		console.log("======================");
		$.ajax(settings); //페이지가 렌더링되면 딱 한번 실행됨 
	}).trigger("change"); //trigger : 강제로 어떤 이벤트를 발생시키겠다 . 페이지가 렌더링 되면서 무조건 change이벤트 실행됨  
	
	//다섯개의 라디오 버튼에 대해서 비동기요청이 넘어감 f12 콘솔창 확인 해봏ㄹ것  
	
	
	
	
		
	$("#selName").on("change", function(){
		const txt = $(this).find("option:selected").text();
		$("#greetingArea").attr("data-key1", txt);
		$("#greetingArea").html("");
		
	}).trigger("change"); 
	
	
	
	
	
	
	




</script>
</body>
</html>
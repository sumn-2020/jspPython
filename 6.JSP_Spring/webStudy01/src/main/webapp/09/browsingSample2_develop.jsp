<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script>

	//li태그 생성하기 
	 $.fn.generateFileList = function(){

		
		//하드코딩된거 없애기 (data: target)
		let target = this.data("target");
		let ulTag = this;

		$.ajax({
			url : "<%=request.getContextPath()%>/browsing/getFileList",
			data : {
				//target : "/destImages"
				target : target //어떤 ul태그냐에 따라서 data값을 다르게 넣을 수 있음 
			}, 
			dataType : "json", //json으로 받아온다는 것은 마샬링이 필요하다는 말 
			success : function(resp) { 
				let liTags = [];
				
				console.log(resp.files); //setAttribute("files", files)
				$.each(resp.files, function(index, wrapper) { //index와 wrapper 하나하나를 받아올수있음 
					let li = $("<li>").addClass("list-group-item")
							 		  .data("contentType", wrapper.contentType)//data-content-type="wrapper.contentType"
							          .html(wrapper.name);
					liTags.push(li);
				});
				ulTag.html(liTags);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		
		return this;
	}
</script>
</head>
<body>


<!-- SpecificTargetFileBrwosingServlet
FileManagerServlet -->


<!-- 
1단계 ul li 완성시키기 
2단계 li active 처리 
3단계 copyBtn 클릭이벤트 처리
4단계 소스 파일과 destination파일 넘기기
5단계 그 요청을 받아서 서버사이드에서 받아서 처리 -->

<div class="row">
	<div class="col">
		<!-- 이미지 갯수만큼 로딩시키기   -->
		<h4>src의 위치 : /resources/images </h4>
		<ul id="srcUL"  class="list-group" data-target="/resources/images"> <!-- data속성의 key :  target-->
			<li data-content-type="image/jpeg" class="list-group-item">cat1.jpg</li>
			<li data-content-type="image/jpeg" class="list-group-item active">cat1.jpg</li>
		</ul>
	</div>
	<div class="col">
		<input type="radio" name="command" value="COPY" checked /> COPY
		<input type="radio" name="command" value="MOVE" checked /> MOVE
		
		<button id="controlBtn">실행</button>
	</div>
	<div class="col">
		<!-- 복사 될 위치  : copy버튼 클릭할 때마다 아래 태그에 추가  -->
		<h4>destination위치 : /destImgs</h4>
		<ul id="destUL" data-target="/destImgs"> <!-- data속성의 key :  target-->
		</ul>
	</div>
</div>



<script>
	
	//ul태그가 아니라 li가 event의 대상이 됨 
	let srcUL = $("#srcUL").generateFileList().on("click", "li" , function(event) {//상단에 있는 generateFileList function 함수 실행 
//		alert("1번방식");
		$(this).siblings("li").removeClass("active");
		$(this).addClass("active"); //this = li
	}); 
	
	//처음부터 ul태그 안에 li가 있다는 것을 가정하고 만든 것임  => 동적인 화면을 구성하기 위해서는 $("#srcUL").generateFileList().on("click", "li" , function(event) 이런 모양으로 써줘야됨 
/* 	$("#srcUL>li").on("click", function(){
		alert("2번방식");
	}); 
*/




	let destUL = $("#destUL").generateFileList();
	//copy버튼 클릭했을 때 
	// => $("#copyBtn").on("click", function(){
 	$(document).on("click", "#copyBtn",  function(event){
 		
 		let fileName = srcUL.find("li.active").text();
 		if(!fileName) //선택한 파일이 없으면 
 			return false; 
 		
 		
 		let srcFile = srcUL.data("target") + "/" + fileName; // /resources/images + "/" + fileName
 		let data = {
				srcFile : srcFile, 
				destFolder : destUL.data(), 
				command: "COPY"		
 		}
 		
 		//아래 ajax 코드를 간결하게 사용하는 방법 => url, data, method="post"가 한줄에 
		$.post("<%=request.getContextPath() %>/browsing/fileManager", data, function(resp) { 
			//resp안에 result값 넘어왔음 
			if(resp.result){//넘어와서 있으면 
				destUL.generateFileList(); //얘를 갱신해라 
			}else {
				alert("파일관리실패");
			}
				
		}, "json"); 

<%-- 	
	$.ajax({
			url : "<%=request.getContextPath() %>/browsing/fileManager", 
			method : "post", // get: 가져오겠다 <-> post: body만들기  
			data : { //뒷단으로 세개의 데이터를 넘긴다.
				//srcFile : "/resources/images/cat1.jpg",  //파일 하나를 선택했을 경우 넘기는 거
				srcFile : srcFile, 
				//destFolder : "/destImgs", // 선택된 파일 넘길 경로 
				destFolder : destUL.data(), //data-target="/destImgs"
				command: "COPY"
			
			},
			dataType : "json", 
			success : function(resp) { 
	
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		}); 
		
--%>
	
 		
 		
 		
 		
 		
	}); 
	

	




	
	


</script>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>
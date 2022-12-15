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

MemoControllerServlet
<h4>Restful 기반의 메모관리</h4>

<form action="${pageContext.request.contextPath}/memo" method="post">
	<input type="text" name="writer" placeholder="작성자" />
	<input type="date" name="date" placeholder="작성일" />
	<textarea name="content"></textarea>
	<input type="submit" value="등록" />
</form>
<br><br>

<table class="table-bordered">
	<thead>
		<tr>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<script>

	//EDD(Evetn Driven Development)(O) vs TDD(Test Driven Development)

	//show.bs.modal : 버튼 띄워질때 적용 	 / shown.bs.modal:다 띄워지고 난 후에 적용
	$("#exampleModal").on("show.bs.modal", function(event) { //event => show라는 이벤트
		//this == event.target : modal창 그 자체
		let memo = $(event.relatedTarget).data("memo") // 모달을 띄울때 사용했던 엘리먼트 (modal을 오픈할 때 사용한 클릭대상 : tr버튼태그)
		//.data("memo")  밑에서 저장해놨던 .data("memo", memo)의 data의 키 값을 가져와서 내용물 들고옴

		$(this).find(".modal-body").html(memo.content);

	}).on("hidden.bs.modal", function(event) {//event => hidden라는 이벤트 (hidden 이벤트 안하면 모달 닫히고서도 계속  모달 내용물이 남아있는데 hidden 이벤트를 통해서  empty해줌으로써 모달 내용물 확 지워버릴수있다. )
		$(event.target).find(".modal-body").empty(); //event.target == this
	});




let listBody = $("#listBody");
let makeListBody = function(memoList) {
	listBody.empty();
	let trTags = [];
	if(makeListBody.length>0) { //메모가 한 건 이상 있으면 
		
		$.each(memoList, function(index, memo) { //index, memo : 인덱스와 한 건의 메모 가져오기 
			let tr = $("<tr>").append(
				$("<td>").html(memo.writer),		
				$("<td>").html(this.date)// this.date = memo.date
			).attr({
				/* data-bs-toggle="modal" data-bs-target="#exampleModal" a링크 굳이 만들 필요 없음 */ 
				"data-bs-toggle" : "modal",
				"data-bs-target" : "#exampleModal"
			}).data("memo", memo);//데이터의 원래의 타입을 유지시켜줄수 있음 memo전체의 내용을 data타입으로 저장해둠 (memo라는 키를 가지고)
			trTags.push(tr);
		});
		
	}else {
		let tr = $("<tr>").html(
			$("<td>")
				.attr("colspan","2")
				.html("작성된 메모 없음")
		);
		trTags.push(tr);
	}
	listBody.append(trTags); //여러개 tr태그 넣어주기 
}

$.ajax({
	url : "${pageContext.request.contextPath}/memo", 
	method : "get", //put(update): 1번 메모("${pageContext.request.contextPath}/memo?idx=1)를 수정하겠다, post: 메모를 작성하겠다 , get: 메모를 가져오겠다 , delete: 1번 메모를 삭제하겠다    
	dataType : "json", 
	success : function(resp) { 
		console.log(resp.memoList[0].writer);
		makeListBody(resp.memoList);
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
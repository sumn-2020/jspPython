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


<script>
let listBody = $("#listBody");
let makeListBody = function(memoList) {
	listBody.empty();
	let trTags = [];
	if(makeListBody.length>0) { //메모가 한 건 이상 있으면 
		
		$.each(memoList, function(index, memo) { //index, memo : 인덱스와 한 건의 메모 가져오기 
			let tr = $("<tr>").append(
				$("<td>").html( 
						'<a href="" class="" data-bs-toggle="modal" data-bs-target="#exampleModal">' + memo.writer + '</a>'
						
						+'<div class="modal fade" id="exampleModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">'
						+  '<div class="modal-dialog">'
						+    '<div class="modal-content">'
						+      '<div class="modal-header">'
						+        '<h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>'
						+        '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'
						+      '</div>'
						+      '<div class="modal-body">'
						
						memo.writer
						
						
						+      '</div>'
						+      '<div class="modal-footer">'
						+        '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'
						+        '<button type="button" class="btn btn-primary">Understood</button>'
						+      '</div>'
						+    '</div>'
						+  '</div>'
						+'</div>'
				
				),		
				$("<td>").html(this.date)// this.date = memo.date
			).data("memo", memo);//데이터의 원래의 타입을 유지시켜줄수 있음 
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
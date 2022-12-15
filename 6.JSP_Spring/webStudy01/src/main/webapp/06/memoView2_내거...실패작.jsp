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

<table>
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
$.ajax({
	url : "${pageContext.request.contextPath}/memo", 
	method : "get", //put(update): 1번 메모("${pageContext.request.contextPath}/memo?idx=1)를 수정하겠다, post: 메모를 작성하겠다 , get: 메모를 가져오겠다 , delete: 1번 메모를 삭제하겠다    
	dataType : "json", 
	success : function(resp) { 
		
		//컨트롤러에서 정보 불러온 후   jsp로 데이터 넘겨주기 
		console.log(resp.memoList[0].writer);
		
		let trTags = function(writer, date) {
	
			let arr = resp.memoList;
			
			$.each(){
				let tr = $("<tr>").append(
						$("<td>").html(resp.memoList[i].writer),
						$("<td>").html(resp.memoList[i].date)
					);
				return tr;
			}
			
			
		}
		$("#listBody").empty();
		$("#listBody").append(trTags);
		
		
		

	},
	error : function(jqXHR, status, error) {
		console.log(jqXHR);
		console.log(status);
		console.log(error);
	}
});
</script>









</body>
</html>
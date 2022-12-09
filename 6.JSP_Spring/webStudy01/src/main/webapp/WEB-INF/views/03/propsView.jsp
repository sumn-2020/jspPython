<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>


<!-- <img src="./resources/images/cat1.jpg" />
<img src="../resources/images/cat1.jpg" />
<img src="../../resources/images/cat1.jpg" />
 -->
<%-- <img src="<%= request.getContextPath() %>/resources/images/cat1.jpg" />--%>



<h4> properties 파일 뷰어 </h4>

<label>
	<input type="radio" name="dataType" value="json" checked />JSON
</label>
<label>
	<input type="radio" name="dataType" value="xml"  />XML
</label>


<button type="button" class="loadData">LOAD DATA</button>
<button type="button" class="clearData">CLEAR DATA</button>
<table border="1px">
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>


<!-- Properties Controller Servlet  -->
<script>

  let listBody = $('#listBody');
  let dataTypes = $("[name=dataType]");
  
  
  
  let makeTrTag = function(name, value) {
	  let tr = $("<tr>").append(
              $("<td>").html(name)      
             ,$("<td>").html(value)      
     );
	  return tr;
  }
  
  
  
  let fn_sucesses = {
		
		json : function(resp) {
			 let trTags = [];
	         $.each(resp, function(name, value) {
	            /* let tr = $("<tr>").append(
	                     $("<td>").html(name)      
	                    ,$("<td>").html(value)      
	            ); */
	            //trTags.push(tr); 
	            trTags.push(makeTrTag(name, value));
	         });
	         listBody.empty(); // 자식들을 지우는 함수
	         listBody.append(trTags);			
		},
		xml : function(comResp) { //comResp => dom 가져옴 
			
			let root = $(comResp).find("Properties")  //Properties라는 엘리먼트를 찾는다 
			let trTags = []; //한건한건의 tr들을 모아놓는 곳 
			root.children().each(function(idx, child) {
				//console.log(child.tagName);
				//console.log(child.innerHTML);

				let name = child.tagName;
				let value = child.innerHTML;
				let tr = makeTrTag(name, value); //한건한건의 tr들을 받아서 
				trTags.push(tr);//trTags에 넣어주기 
				
			});
	        listBody.empty(); // 자식들을 지우는 함수
	        listBody.append(trTags);	
		}
		  
  }
  
  
  let btn = $(".loadData").on("click", function() {
	   let dataType  = dataTypes.filter(":checked").val();
	   $.ajax({
		   
		      /* url -> 이 요청이 servlet에게 넘어간다는 말씀! */
		      /* 응답데이터와 관련된 설정들 */
		      dataType : dataType,
		      success : fn_sucesses[dataType],
		      error : function(jqXHR, status, error) {
		         console.log(jqXHR);
		         console.log(status);
		         console.log(error);
		      }  
		});  
  });

  let clearBtn = $('.clearData').on("click", function() {
	  listBody.empty();  
  });




</script>


</body>
</html>
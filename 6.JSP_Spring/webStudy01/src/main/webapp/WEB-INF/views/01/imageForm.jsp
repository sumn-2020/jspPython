<!-- ImageStreamingFormServlet02 -->

<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>  
	<form action='${cPath}/imageStreaming.do'>
		<select name='image'> 
			${options}
		</select> 
		<input type='submit' value='전송' />
	</form>
	
	<script type="text/javascript">
		$("[name=image]").on("change", function(event) {
			event.target===this;
//			this.form.submit(); //submit이벤트가 발생하지 않음 
			$(this).parents("form").submit();
		});
	</script>
	
</body>    
</html>           


<!-- 
<select name='image' onchange="this.form.submit();"> 
select가 바뀔때마다 <form>으로 submit 

 -->


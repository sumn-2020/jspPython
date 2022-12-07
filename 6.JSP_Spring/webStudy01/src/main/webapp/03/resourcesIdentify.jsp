<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="sun.security.util.Length"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourcesIdentify.jsp</title>
</head>
<body>


하이시스템자원
<h4>자원의 종류와 식별방법 : 접근하는 방법에 따라 달라짐</h4>
<pre>
	: 자원의 위치와 경로 표기 방법에 따라 분류 
	
	1. File system resource : d:/contents/images/cat1.jpg
	<%
		String realPath = "d:/contents/images/cat1.jpg"; //물리주소 
		File fileSystemResource = new File(realPath);
	%> 
	파일의 크기 : <%= fileSystemResource.length() %>
	
	--------------------------------------------------------------------------------------------------------------------
	
	2. Class path resource : /kr/or/ddit/images/cat2.png <!-- 논리주소 - 클래스패스 리소스  : 클래스패스 안에 들어 있는 소스 -->

	<%
	
		//String qualifiedName = "/kr/or/ddit/images/cat2.png";
		String qualifiedName = "../images/cat2.png"; //DescriptionServlet상위 폴더 ddit로 이동한 뒤 images폴더 내부 
		
		//이미지 존재를 찾아내기 위한 코드 필요  : 클래스 로더 (클래스 패스의 관리자)
		//File classPathResource = new File(qualifiedName); //이렇게 쓸수 없음 바로 퀄리파이드경로 넣을 수 없음 
		realPath = DescriptionServlet.class.getResource(qualifiedName).getFile(); //DescriptionServlet.class => DescriptionServlet.class 실제 톰캣 내부속 파일로 접근 가능 
		realPath = DescriptionServlet.class.getClassLoader().getResource("kr/or/ddit/images/cat2.png").getFile(); //getClassLoader => 상대경로 사용 안함 (클래스패스로 고정되어있기 때문에 시작할 때 / 로 시작하지 않음) 
		File classPathResource = new File(realPath);

	%>	
	
	실제경로(톰캣 내부에 있는 실제 경로) : <%= realPath %>
	파일크기 : <%= classPathResource.length() %>
	
	--------------------------------------------------------------------------------------------------------------------
	
	3. web resource : https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif   <!-- 논리주소  -->
	
	<!-- /webStudy01/src/main/webapp/resources/js/jquery-3.6.1.min.js -->
	http://localhost/webStudy01/resources/js/jquery-3.6.1.min.js
	<!-- 웹상에 존재하는 자원의 내 로컬 자원으로 가져와야됨   -->
	<%
		//new File() => 로컬에 가지고있는 자원에만 쓸수 있음 다른방식 필요
		String resourceURL = "https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif"; //논리주소
//		String resourceURL = "http://localhost/webStudy01/resources/js/jquery-3.6.1.min.js"; //논리주소
		URL url = new URL(resourceURL);
		
		URLConnection conn = url.openConnection(); //구글서버를 대상으로 요청 넘어감
		String resourcePath = url.getPath(); //파일 이름 따오기 
		int lastIdx = resourcePath.lastIndexOf('/');
		String fileName = resourcePath.substring(lastIdx + 1);
		String folderPath = "d:/contents/images/"; //저장위치
		File downloadFile = new File(folderPath, fileName); //folderPath에 fildName이라는 이름으로 저장하기 
		InputStream is = conn.getInputStream();
		Files.copy(is, Paths.get(downloadFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
		
		//downloadFile.getPath() : 파일은 패스타입으로 변경
		//REPLACE_EXISTING : 저장하려는 위치에 똑같은게 있으면 replace해라 
			
	%>
	
	resourcePath :  <%=resourcePath  %>


</pre>

</body>
</html>
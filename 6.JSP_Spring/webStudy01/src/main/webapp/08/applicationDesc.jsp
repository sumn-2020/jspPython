<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>application(ServletContext)</h4>
<pre>
	:하나의 컨텍스트와 해당 컨텍스트를 운영중인 서버의 정보를 가진 객체 (singleton)
	(Servlet Container(=WAS, JSP container)와 커뮤니케이션하기 위한 객체)
	
	1. MIME확보 ( = 서버가 가진 정보를 끌어올 수 있다는 것)
	   <%=application.getMimeType("test.jpg") %>
	     스펙정보확인 : <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	   <%=application.getServerInfo() %>
	   <%=application.getContextPath() %> 
	    <%=request.getServletContext().getContextPath() %>
	    
	    
	
 	2. 컨텍스트 파라미터 획득
 		<%=application.getInitParameter("imageFolder") %> <!-- WEB-INF > web.xml :  <param-name>imageFolder</param-name> -->
	3. logging <% application.log("정상 로그 메시지"); %>
	4(******). 현재 컨텍스트의 웹리소스 획득
		<%
		
		
			//   resources/images/cat1.jpg파일 읽어와서 
			String imageURL = "/resources/images/cat1.jpg";
			String realPath = application.getRealPath(imageURL); //물리주소를 넘기면 진짜주소인 논리주소를 넘겨줌
			//System.out.println(realPath);
			
			//09번폴더에 /resources/images/cat1.jpg파일 복사하기  => 이클립스디렉토리에서 확인할게 아니고  D:\A_TeachingMaterial\6.JSP_Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webStudy01\09
			String saveFolderURI = "/09"; //09번폴더에 cat1.jpg파일 복사하기 (이대로는 물리주소기때문에 그대로 못씀  realpath로 논리주소로 바꿔줘야됨)
			String saveFolderPath = application.getRealPath(saveFolderURI); 
			
			File imageFile = new File(realPath);
			File destFile = new File(saveFolderPath, imageFile.getName()); //imageFile.getName() : 똑같은 이름으로 복사를 뜰거야

			
			try(
				
				// 아예  처음부터 스트림 개방한 상태에서 가져올수있음  = > 상단에 있는 번거로운 작업 필요없어짐 
				InputStream is =  application.getResourceAsStream(imageURL); 	
					
					
				//스트림 개방
//				FileInputStream fis = new FileInputStream(imageFile);//jpg파일을 읽어들여야 하니까 fileinputstream
//				BufferedInputStream is = new BufferedInputStream(fis);
				
//				FileOutputStream fos = new FileOutputStream(destFile); //09폴더에 읽어들인 파일을 출력해야되니까 fileoutputstream
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
					
			){
				
				Files.copy(is, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				//StandardCopyOption Enum임 얘가 가지고있는 필드 가져와서 쓸수있음 => 기존에 있는거면 기존걸로 바꿔치기 
				
			}catch(IOException e) {
				//throw new RuntimeException(); = > 이렇게 하면 안됨 
				throw new RuntimeException(e); //원본e에 대한 정보를 유지한다.
			}
			
		%>
		imageFile의 사이즈 : <%=imageFile.length() %> 
		톰캣속에 있는 실제경로 : <%=realPath %>

</pre>

<img src="<%=request.getContextPath() %><%=saveFolderURI %>/<%=destFile.getName() %>" />

</body>
</html>
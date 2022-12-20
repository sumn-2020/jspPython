package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/browsing/fileManager")
public class FileManagerServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		//파라미터 값 유효한지 체크 
		int sc = validate(req);
		Map<String, Object> modelMap = (Map<String, Object>) req.getAttribute("modelMap"); //아래쪽에서 HashMap안에 들어있는 데이터 값들 들고오기 
		
/*		if(sc == 200) { //validate검증해봤는데 200이다? 통과
			
			File sourceFile = (File) modelMap.get("sourceFile");
			
			File destinationFolder = (File) modelMap.get("destinationFolder");	
//			File destFile = new File(destinationFolder , sourceFile.getName()); //destinationFolder에다가 만들거다!! 파일을 srcFile파일 이름 그대로 
			Path  destFilePath = Paths.get(destinationFolder.getCanonicalPath(), sourceFile.getName()); //destinationFolder라는 폴더의 절대경로 (getCanonicalPath)에 
			
			File command = (File) modelMap.get("command");		
			
			Files.copy(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING); //어느경로에서 어느경로로 복사를 해라 
			
		}else { //통과못했다? 그럼 sendError
			resp.sendError(sc);
		}*/
		

		if(sc == 200) { //validate검증해봤는데 200이다? 통과
			
			 boolean result = fileManage(modelMap);
			 req.setAttribute("result", result);
			 String viewName = "/jsonView.do";
			 req.getRequestDispatcher(viewName).forward(req, resp);

			 //fileManage(modelMap);
	
		}else { //통과못했다? 그럼 sendError
			resp.sendError(sc);
		}
		

		
		//파라미터 값 가져오기 
/*		String srcFile =  req.getParameter("srcFile");
		String destFolder = req.getParameter("destFolder");
		String command = req.getParameter("command");*/

/*		int sc = 200;
		if(srcFile == null || srcFile.isEmpty()  //파라미터값이 아예 안넘어왔을 경우 
		   || destFolder == null || destFolder.isEmpty()  
		   || command == null || command.isEmpty() ) { 
			
			sc = 400; //bad_request 400오류 
			
		}else {
			
			//a) 첫번째 검증  sourceFile
			ServletContext application =   req.getServletContext(); //미리 init 메소드로 빼놓아도 상관없음  => 따로 init으로 빼는 방식은 SpecificTargetFileBrwosingServle참고
			String srcPath = application.getRealPath(srcFile);
			File sourceFile = new File(srcPath);
			
			if(!sourceFile.exists()) {//파일이 존재하지 않으면
				sc = 404; //404오류 
			}else if(sourceFile.isDirectory()) { //sourceFile는 폴더기 때문에 isDirectory()못쓰기 때문에 오류 
				sc = 400; //400오류 
			}
			
			
			//b) 두번째검증  destPath
			String destPath = application.getRealPath(destFolder);
			File destinationFolder = new File(destPath);
			if(!destinationFolder.exists()) {
				sc = 404; 
			}else if(destinationFolder.isFile()) { //destinationFolder는 폴더기 때문에  isFile() 못쓰기 때문에 오류 
				sc = 400;
			}
			
			//b) 세번째검증  COPY
			if(! "COPY".equals(command)) {
				sc = 404;
			}
			
		}*/	
	
	}
	
	////파라미터 값 유효한지 체크 
	//throws IOException얘의 예외는 modelMap의 호출자에게 예외 떠넘겨짐
	private boolean fileManage(Map<String, Object> modelMap) throws IOException { //낱개 데이터를 한데 묶어서 가져오기 위해서 modelMap
		
		File sourceFile = (File) modelMap.get("sourceFile");
		File destinationFolder = (File) modelMap.get("destinationFolder");	
		Path  destFilePath = Paths.get(destinationFolder.getCanonicalPath(), sourceFile.getName()); 
		String command = (String) modelMap.get("command");		
		Files.copy(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING); 
		
		return true;
	}
	

	
	//받아온 파라미터 값 검증하기 
	private int validate(HttpServletRequest req) {
		
		String srcFile =  req.getParameter("srcFile");
		String destFolder = req.getParameter("destFolder");
		String command = req.getParameter("command");
		
		
		
		//필요한 세개 데이터(sourceFile, destPath, command) 를 한데 모아서 사용할라고 HashMap만들고이씅ㅁ 
		Map<String, Object> modelMap = new HashMap<>();
		req.setAttribute("modelMap", modelMap);
		

		
		int sc = 200;
		if(srcFile == null || srcFile.isEmpty()  //파라미터값이 아예 안넘어왔을 경우 
		   || destFolder == null || destFolder.isEmpty()  
		   || command == null || command.isEmpty() ) { 
			
			sc = 400; //bad_request 400오류 
			
		}else {
			
			//a) 첫번째 검증  sourceFile
			ServletContext application =   req.getServletContext(); //미리 init 메소드로 빼놓아도 상관없음  => 따로 init으로 빼는 방식은 SpecificTargetFileBrwosingServle참고
			String srcPath = application.getRealPath(srcFile);
			File sourceFile = new File(srcPath);
			
			if(!sourceFile.exists()) {//파일이 존재하지 않으면
				sc = 404; //404오류 
			}else if(sourceFile.isDirectory()) { //sourceFile는 폴더기 때문에 isDirectory()못쓰기 때문에 오류 
				sc = 400; //400오류 
			}else {
				modelMap.put("sourceFile", sourceFile); //modelMap이라는 HashMap에 sourceFile이라는 key로 value값을 넣어줄수있음 
			}
			
			//b) 두번째검증  destPath
			String destPath = application.getRealPath(destFolder);
			File destinationFolder = new File(destPath);
			if(!destinationFolder.exists()) {
				sc = 404; 
			}else if(destinationFolder.isFile()) { //destinationFolder는 폴더기 때문에  isFile() 못쓰기 때문에 오류 
				sc = 400;
			}else {
				modelMap.put("destinationFolder", destinationFolder);
			}
			
			//b) 세번째검증  COPY
			if(! "COPY".equals(command)) {
				sc = 404;
			}else {
				modelMap.put("command", command);
			}
			
		}
		
		return sc;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

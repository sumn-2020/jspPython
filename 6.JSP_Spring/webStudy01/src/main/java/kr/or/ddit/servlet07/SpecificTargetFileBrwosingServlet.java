package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;




//li태그 만들기 

@WebServlet("/browsing/getFileList")
public class SpecificTargetFileBrwosingServlet extends HttpServlet {

	
	//ServletContext(application) : 서버 정보가 다 들어있는 것
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		//1. 넘어온 파라미터 값 검증하기 
		String target =  req.getParameter("target");
		if(target==null || target.isEmpty()) { //파라미터가 안넘어 왔거나 잘못넘어왔을 경우
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//1-1. 실제 존재하는 파라미터 인지 확인하기
		//target : /resources/images
		String targetPath = application.getRealPath(target);
		File targetFolder = new File(targetPath); //물리주소 필요  :  public void init(ServletConfig config) throws ServletException {
		
		//target : aszdgklasjdthskd (파라미터 값이 이상하게 넘어왔을경우)
		if(!targetFolder.exists()) {//폴더가 존재하지 않으면 
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//target : /resources/images/cat01.jpg (폴더경로가 제대로 넘어오지 않았을 경우)
		if(targetFolder.isFile()) {//폴더가 맞는지 확인
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		

		//2.
		//fileList 
		//필요한 파일 정보를 가져올 수 있음 
		//File[] fileList 이게 우리가 내보내야 할 json파일 형태 
		//File[] fileList = targetFolder.listFiles();만으로는 그대로 마샬링 못하기 때문에 adapter, adaptee 같은 wrapper 디자인 패턴을 이용해서 재구조화 시켜줘야됨 
		File[] fileList = targetFolder.listFiles();
		System.out.println(fileList);
		//돼지코들  모아둠
		List<FileWrapper> wrapperList = Arrays.stream(fileList)
							 .map(file -> new FileWrapper(file,application)) //람다식이 딱 한줄이면 body부분 없앨수있음
							 .collect(Collectors.toList());
			  
			  
							  /*.map(file -> { //람다식을 이용해서 하나하나씩 구현
								  return new FileWrapper(file,application);
							  });*/
		
		
		

		//3. 
		//req.setAttribute("files", fileList);
		//돼지코를 대상으로 마샬링 해줘야됨 
		req.setAttribute("files", wrapperList);
		
		//4.
		String viewName = "/jsonView.do";
		
		//5. 
		req.getRequestDispatcher(viewName).forward(req,resp);
			
	
	}
	
}

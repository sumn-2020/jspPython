package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//B

@WebServlet(value="/bts/*", loadOnStartup=2)
public class BTSMemberServlet extends HttpServlet{

	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		application = config.getServletContext();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
	
		
		String code =  Optional.of(requestURI)
						.map(uri->uri.substring(req.getContextPath().length())) //url 잘라내기
						.map(uri->uri.substring("/bts/".length())) ///bts/ 이후의 것들을 잘라낸다 
						.get();
		
	
		
		Map<String, String[]>  members =  (Map) application.getAttribute("btsMembers");
		String[] contents = members.get(code);
		
		if(contents == null) {//요청에 대한 검증 ( ex. /bts/sadfsd 이 페이지는 존재하지 않습니다. )
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String contentPage = contents[1]; //컨텐츠페이지 url  => /WEB-INF/views/bts/rm.jsp
		
		System.out.println(contentPage);
		req.setAttribute("contentPage", contentPage);
		req.getRequestDispatcher("/WEB-INF/views/bts/btsLayout.jsp").forward(req, resp);
	
	}
	

}




















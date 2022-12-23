package kr.or.ddit.servlet09;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet09.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet09.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servlet09.service.DataBasePropertyServiceImpl;
import kr.or.ddit.servlet09.service.DatabasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;


@WebServlet("/13/properties.do")
public class DataBasePropertyControllerServlet extends HttpServlet{

	
	
	private DatabasePropertyService service = new DataBasePropertyServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//1
		String propertyName = req.getParameter("propertyName");
		
		//2
		List<DataBasePropertyVO> list = service.retrievePropertyList(propertyName); //실행코드의 캡슐화
		//3
		req.setAttribute("list", list); //자바코드 밖에서 갖고놀수있게끔 세팅해둬야됨 

		//4
		String viewName = "/WEB-INF/views/13/jdbcDesc.jsp";
		//5
		// 규칙! redirect:/ 로 시작되는 viewName은 redirect로 넘기기
		if(viewName.startsWith("redirect:")) { //viewName이 redirect:로 시작 할 경우  
			viewName = viewName.substring("redirect:".length()); // redirect:길이 만큼 잘라라 => redirect: 이부분 삭제
			resp.sendRedirect(req.getContextPath() + viewName); //   req.getContextPath() +  /login/loginForm.jsp
		}else { //forward할 경우
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		

	
	}
}

package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.servlet04.service.CalculateService;
import kr.or.ddit.servlet04.service.CalculateServiceImpl;
import kr.or.ddit.servlet04.service.PropertiesService;
import kr.or.ddit.servlet04.service.PropertiesServiceImpl;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/04/calcuate")
public class CalculateServlet extends HttpServlet {

		//이 서블릿에서 service를 이용할 수 있게 가져옴 
		private CalculateService service = new CalculateServiceImpl();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//그냥 url 제공이 목적 그냥 화면에 뿌려주는 역할 
			String path = "/WEB-INF/views/03/calculateForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			//사칙연산로직은 여기서 짜면됨 
			//역직렬화와 언마샬링 작업 필요
			CalculateVO calculateVO = null;
			try(
				InputStream is =  req.getInputStream();
			) {
				calculateVO = new ObjectMapper().readValue(is, CalculateVO.class);
			}
			
			
			req.setAttribute("expression", calculateVO.getExpression()); // for jsonView.do
			req.setAttribute("message", calculateVO.getExpression()); // for plainView.jsp
			
			String accept = req.getHeader("Accept");
			String viewName = null;
			
			if(accept.contains("json")) {
				viewName = "/jsonView.do";
			}else {
				viewName = "/WEB-INF/views/04/plainView.jsp";
			}
			
			req.getRequestDispatcher(viewName).forward(req,resp);
			
			
		}
		

}

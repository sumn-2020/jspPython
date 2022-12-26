package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet {
	
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1.
		String memId = req.getParameter("who");
		if(StringUtils.isBlank(memId)) { //누구를 상세조회 할것인지가 빠져있음 - 정상처리 불가
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);// 400에러
			return; 
		}
		
		//2. 
		MemberVO member = service.retrieveMember(memId);
		
		//3. 
		req.setAttribute("member", member);
		
		//4.
		String viewName = "/WEB-INF/views/member/memberView.jsp";
		
		//5번단계 
		// 규칙! redirect:/ 로 시작되는 viewName은 redirect로 넘기기
		if(viewName.startsWith("redirect:")) { //viewName이 redirect:로 시작 할 경우  
			viewName = viewName.substring("redirect:".length()); // redirect:길이 만큼 잘라라 => redirect: 이부분 삭제
			resp.sendRedirect(req.getContextPath() + viewName); //   req.getContextPath() +  /login/loginForm.jsp
		}else { //forward할 경우
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
		
	}
	

}

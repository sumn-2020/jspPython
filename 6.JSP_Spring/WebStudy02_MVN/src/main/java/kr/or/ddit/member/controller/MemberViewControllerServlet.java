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
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
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
		String viewName = "member/memberView";
		
		//5번단계 
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
	}
	

}

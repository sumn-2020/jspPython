package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet {
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		//1.  요청분석
		//String memId = req.getParameter("memId"); 
		//String memName = req.getParameter("memName"); 
		//String memMail = req.getParameter("memMail"); 
		//String memHp = req.getParameter("memHp"); 
		//String memAdd1 = req.getParameter("memAdd1"); 
		//String memAdd2 = req.getParameter("memAdd2"); 
		//String memMileage = req.getParameter("memMileage"); 
		
		//2 모델확보 - 모델레이어 사용(service 가져와서 사용)
		List<MemberVO> memberList = service.retrieveMemberList(); 
		
		//3 
		req.setAttribute("memberList", memberList); //memberList 가져와서 "memberList"라는 이름으로 jsp에 보내주기 
		
		//4.
		String viewName = "member/memberList";
		
		
		//5번단계 
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
		
		
		
	}
}

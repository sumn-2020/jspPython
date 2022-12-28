package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Provider.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet {

	private MemberService service = new MemberServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		req.setAttribute("member", member);
		
		String viewName = "member/memberForm";
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		req.setCharacterEncoding("UTF-8");
		
		//요청 검증 
		MemberVO member = new MemberVO(); //파라미터를 몇개 받을수있는지 모르니까 일단 그릇 만들어두기
		
		/*case INVAILDPASSWORD: //비번오류가 났다면 다시 memberForm페이지로 넘어가는데 이때는 
			viewName = "member/memberForm";
			break;*/
		req.setAttribute("member", member); //이전에 내가 작업한 데이터들이 떠있게 됨 
		
		try {
			BeanUtils.populate(member, req.getParameterMap());//map으로부터 데이터를 바인딩할때 populate씀 
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		//try가 잘 되었따면 member안에 데이터가 들어가있게되는거임 
		
		
		String viewName = null;
		
		
		//검증하기 
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors); // 세팅한 errors jsp로 공유하기 위해서 세팅해주기 
		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		if(valid) {
			ServiceResult result = service.modifyMember(member);//사용자가 작성한 모든 값들 들어가있음
			switch (result) {
			case INVAILDPASSWORD: //비번오류가 났다면
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case FAIL: //수정실패했다면(서버에 문제가 생겼을 경우)
				req.setAttribute("message", "서버오류, 조금있다 다시하시오 ");
				viewName = "member/memberForm";
				break;
			default: //수정성공했다면 (ok) 
				viewName = "redirect:/mypage.do";
				break;
			}
		}else {
			viewName = "member/memberForm";
		}
		
		
		
		
		

		
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);

	
	
	}
}

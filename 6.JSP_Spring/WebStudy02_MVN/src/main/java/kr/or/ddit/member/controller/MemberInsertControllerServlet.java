package kr.or.ddit.member.controller;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName = "/WEB-INF/views/member/memberForm.jsp";
		//5번단계 
		// 규칙! redirect:/ 로 시작되는 viewName은 redirect로 넘기기
		if(viewName.startsWith("redirect:")) { //viewName이 redirect:로 시작 할 경우  
			viewName = viewName.substring("redirect:".length()); // redirect:길이 만큼 잘라라 => redirect: 이부분 삭제
			resp.sendRedirect(req.getContextPath() + viewName); //   req.getContextPath() +  /login/loginForm.jsp
		}else { //forward할 경우
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 파라미터값 받아오기 
		req.setCharacterEncoding("UTF-8");
		
		MemberVO member = new MemberVO(); //한꺼번에 vo에 넣어줄 그릇 하나 만들기 
		req.setAttribute("member", member); //미리 membervo 공유 redirect 할때까지 정보 살아있게됨 
		
		
//		member.setMemId(req.getParameter("memId")); //파라미터 받아서 넣기 
		//19개 파라미터 값 넣기.... 귀찮 
		
		
		//reflection쓰자 
		 Map<String, String[]> parameterMap =  req.getParameterMap();
/*		 parameterMap.entrySet().stream()//파라미터 한쌍의 정보에 접근 가능 
		 			.forEach(entry->{
		 				String paramName = entry.getKey(); //paramName이 이름을 통해서 memIㅇ 찾아
//		 				MemberVO.class.getDeclaredField(paramName);
		 				PropertyDescriptor pd = new PropertyDescriptor(paramName, MemberVO); //모든 ㅍ프로퍼티 가지고있는 vo에서 paramName꺼내기 
		 				Method setter =  pd.getWriteMethod(); // setter찾았음 
		 			}); 
*/
		
		//beanUtils가 이걸 대체해줄수있음 
		try {
			BeanUtils.populate(member, parameterMap);
				
		}catch(IllegalAccessException | InvocationTargetException e) {
			throw new ServletException();
		}
		//try까지 다 끝내고 나면 memberVO안에 파라미터값들 다 들어갔음 
		
		
		
		
		//2. 
		String viewName = null;
		
		ServiceResult result =  service.createMember(member);
		switch (result) {
		case PKDUPLICATED: //pk중복일 경우
			req.setAttribute("message", "아이디 중복");
			viewName = "/WEB-INF/views/member/memberForm.jsp"; //정보 계속 남아있게됨  (req.setAttribute("member", member); //미리 membervo 공유 redirect 할때까지 정보 살아있게됨 )
			break; //기존에 입력데이터((req.setAttribute("member", member), message(req.setAttribute("message", "아이디 중복");)를 가지고 memberForm.jsp로 넘어감 
		case FAIL: 
			req.setAttribute("message", "서버에 문제 있음. 조금있다하시오");
			viewName = "/WEB-INF/views/member/memberForm.jsp";
			break;
		default: //ok일 경우
			viewName = "redirect:/"; 
			break;
		}
		
		
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

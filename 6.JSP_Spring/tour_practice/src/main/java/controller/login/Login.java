package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.login.MemberServiceImpl;
import vo.MemberVO;


@WebServlet("/login")
public class Login extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/views/login/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//service불러오기 
		MemberServiceImpl service = MemberServiceImpl.getService();
		
		//파라미터를 통해서 값 받아오기 input의 name값 
		String id = req.getParameter("userId");
		String password = req.getParameter("password");
		System.out.println(id + password);
		
		//memberVO에서 해당 값에 일치하는 사람이 있는지 확인해야됨
		MemberVO mv = new MemberVO();
		mv.setMemId(id); // 받아온 파라미터값은 memberVOmemberID에 넣어서 비교 
		mv.setMemPw(password);
		
		
		//회원로그인
		mv = service.loginMember(mv); //서비스에서  loginMember 뽑아오기 
		
		if(mv != null) { // 정보가 있으면 
			HttpSession session = req.getSession(); 
			session.setAttribute("memberVO", mv); 
			resp.sendRedirect("/");
		}else if(mv == null) { //정보가 없으면 
			req.getSession().setAttribute("msg", "로그인실패");
			resp.sendRedirect("/login");
		}
		

	
	}

}

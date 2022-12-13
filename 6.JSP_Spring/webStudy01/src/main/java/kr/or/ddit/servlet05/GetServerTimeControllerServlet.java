package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/05/getServerTime")
public class GetServerTimeControllerServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		//1. 요청의 조건: 헤더(Accept)와 파라미터(locale)를 받아와서 처리
		String localeParam =  req.getParameter("locale");
		String accept = req.getHeader("Accept");
		
//		파라미터 locale에 따라 locale객체가 달라져야함
		Locale clientLocale = req.getLocale(); //기본 locale은 클라이언트거 따라가야하므로 req.getLocale써야됨
		if(localeParam != null && !localeParam.isEmpty()) { // localeParam에 파라미터가 있다면 
			clientLocale = Locale.forLanguageTag(localeParam); //Locale.KOREAN.toLanguageTag() 이게담기는 거임
		}


		
		//모델확보
		Date now = new Date();
		//c: 그냥 캐릭터만 인식됨 시간이라는 걸 알려주기 위해 t를 추가함 
//		String nowStr = String.format(Locale.FRANCE, "now : %tc", now); //다른 나라 시간 형식으로 출력	
		String nowStr = String.format(clientLocale, "now : %tc", now);
		//req.setAttribute("now", now.toString()); //toString을 추가함으로써 우리한테 익숙한 시간이 출력됨
		
		
		//모델공유
		req.setAttribute("now", nowStr); //(for json선택) toString을 추가함으로써 우리한테 익숙한 시간이 출력됨
		req.setAttribute("message", nowStr); //(for plain선택) plainView.jsp
		resp.setHeader("Refresh", "1"); //1초마다 새로고침
		
		
//		헤더 accept에 따라 viewName이 달라져야함 
		String viewName = null;
		if(accept.contains("json")) { //accept에 json이 포함되어있다면 json으로 마샬링을 하겠다
			 viewName = "/jsonView.do"; //마샬링을 통해서 jsp로 전달가능함 
		}else if(accept.contains("plain")) { //plain은 마샬링 필요 없음 
			viewName = "/WEB-INF/views/04/plainView.jsp"; 
		}else { //기본적으로 주어지는 오류. 위에 것들은 개발자가 오류 코드 따로 지정해줘야됨 
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, accept + "mime은 생성할 수 없음");
		}
		

		//뷰선택
		if(viewName == null && !resp.isCommitted()) { //
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"뷰네임은 널일수 없음");
		}else {
			//뷰이동
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
		
	}


}

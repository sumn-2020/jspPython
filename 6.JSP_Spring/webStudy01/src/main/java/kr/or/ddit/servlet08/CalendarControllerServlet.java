package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import  static java.util.Calendar.*; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/10/calendar.do")
public class CalendarControllerServlet extends HttpServlet   {
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Locale cliendLocale = req.getLocale(); //클라이언트가 무슨 언어(어디나라 컴퓨터인가에 따라서 언어 달라짐)를 쓰냐에 따라서 locale 달라짐
		
		
		//현재 날짜에 해당하는 켈린더 객체 받아오기
		Calendar calendar =  Calendar.getInstance(); 
		// 뒷단에 있는 calender를 받을수있는 adaptee를 앞단에서 만들어야 함 (앞단에서 이걸 받아줄수있는 게 없음) =>CalendarWrapper.java
		// adaptee라는 calendar안에 offset값(<c:set var="offset" value="4" />)이 없기 때문에 wrapper 디자인패턴 만들어줌 ?????????
		
		//  앞단에서 세개의 파라미터를 받아와야됨    => ?year=calendar.beforeYear&month=calendar.beforeMonth&language=en
		
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String language = req.getParameter("language");

		//language 파라미터에 대한 검증
		if(language != null && !language.isEmpty()) { //lanague라는 파라미터가 정상적인 값을 가지고 있고 1이상일 경우
			cliendLocale = Locale.forLanguageTag(language); //기본으로는 클라이언트 언어를 사용하겠지만 선택을 따로 할 경우 해당 언어를 사용하겠다. 
		}
		
		//month와 year 파라미터에 대한 검증
		// 파라미터 받아온 후 파라미터 검증 필요 
		// ?year=calendar.beforeYear&month=ㅂㅈㄱㅂㅈㄱㅂㅈㄷㄱ : 정상적인 파라미터값 넘어오지 않았을 경우도 검증해야됨 
		try {
			
			//일단 파라미터가 있으면 파싱부터 한후 조건이 맞으면 파싱 고고
			if(yearParam!=null && monthParam !=null) {
				
				//if 조건 맞으면 파싱을  한 후
				int year = Integer.parseInt(yearParam); //파라미터가 이런식으로 넘어온다면 400에러 <= Integer.parseInt("abdghg") 
				int month = Integer.parseInt(monthParam);
				
				//year와 month를 해당 파라미터로 바꾼다. 
				calendar.set(YEAR, year); //받아와서 파싱한 year을 넣어준다. 
				calendar.set(MONTH, month);
			}
			
		}catch(NumberFormatException e) { //문자열을 int로파싱할 수 없을 경우 던지는 예외처리 
			resp.sendError(400, e.getMessage()); //예외처리도 하나의 조건문처럼 사용할 수 있음 
			return;
		}
		
/*		
		if문 속에 들어있는 예외처리를 위처럼 try catch문으로도 쓸수있음 
		if(yearParam!=null && !yearParam.matches("\\d{4}")) {  // yearParam이 널이 아니거나 4자리 숫자가 아닐경우      \\d{4} => 4자리의 숫자로 표현하다 
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400에러 띄우기
			return;
		}
		
*/		
	
		
		//req.setAttribute("calendar", calendar);
		req.setAttribute("calendar", new CalendarWrapper(calendar, cliendLocale)); 
		//CalendarWrapper 클래스로 calender객체 넘김 (adaptee로써)
		// CalendarWrapper.java에 있는 생성자 public CalendarWrapper(Calendar adaptee, Locale locale)이쪽으로 calendar와 cliendLocaler값 들어감 
		//adaptee에서 처리돼서 받아온 값들을 "calendar"속에 넣고 사용
		
		String viewName = "/WEB-INF/views/calendar.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
		
		
		
		
		
	
	}
	
	
}

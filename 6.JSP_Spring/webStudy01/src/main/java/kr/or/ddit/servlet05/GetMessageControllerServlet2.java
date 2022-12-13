package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import jdk.nashorn.internal.parser.JSONParser;
import kr.or.ddit.servlet01.DescriptionServlet;




@WebServlet("/04/getGreetingMessage3")
public class GetMessageControllerServlet2 extends HttpServlet {
	
	


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 요청분석 (line, header, body)
		String accept = req.getHeader("Accept"); // */* html json xml ... 
		String locale = req.getParameter("locale");
		
		//이도 저도 아닐 경우 클라이언트 로케일을 따라가야됨 
		Locale clientLocale = null;
		if(locale != null) { //의도한 locale (파라미터에 띄워지는 locale)
//			ko, en : lanuage tag, locale code
			clientLocale = Locale.forLanguageTag(locale);	
		}else { //자연발생한 locale
			clientLocale = req.getLocale(); //Accept-lanuage header로 결정됨 
		}
		String name = req.getParameter("name");		
		
		
		
		//2. 모델확보 ()
		Object message = retrieveMessage(clientLocale , name);
		
		//3. 모델공유(setAttribute)
//		{"message" : "HELLO"}
//		vo를 대신할 수 있는 것(지금 이 샘플에서는 vo 안쓰고 있으므로 map으로!) : Map 
//		Map<String, Object> target =   Collections.singletonMap("message" , message); // 마샬링뷰에서 이미 Map을 만들고있으므로 따로 만들필요 없음
		req.setAttribute("message", message);
		
		
		//4. 뷰선택 : 어떤 뷰를 사용할 것인가는 accept를 통해서 정해짐 
		String viewName = null;
		int statusCode = HttpServletResponse.SC_OK; //성공적으로 출력됐을 경우 : 200
		
		
		if(accept.contains("json")) {
			viewName = "/jsonView.do"; // MarchallingJsonViewServlet
		}else if(accept.contains("xml")) {
			viewName = "/xmlView.do"; // MarchallingXmlViewServlet
		}else if(accept.contains("plain")) { //일반텍스트이므로 굳이 마샬링이라는 작업 필요없음 
			viewName = "/WEB-INF/views/04/plainView.jsp";  ///webStudy01/src/main/webapp/WEB-INF/views/04/plainView.jsp
		}else {
			//이도저도 아닌 상태는 => 상태코드 출력 
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE; // 이도 저도 아닌 경우 : 406
		}


		//5. 뷰로 이동
		if(statusCode == HttpServletResponse.SC_OK) { //성공이거나 
			//5. 뷰로 이동
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {//아니거나 => 문제가 생겼다는 것을 클라이언트한테 보내줘야됨
			resp.sendError(statusCode);
		}
		

	}

	private Object retrieveMessage(Locale clientLocale, String name) {
		String baseName = "kr.or.ddit.props.Message";//webStudy01/src/main/resources/kr/or/ddit/props/Message_en.properties
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, clientLocale);
		return bundle.getString(name);
	}
	

	
	
}

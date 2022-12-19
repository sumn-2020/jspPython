package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//A 


@WebServlet(value="/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet {
   
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = config.getServletContext();
		application.setAttribute("btsMembers", getBtsMemberList()); //set(A에서)으로 넣었으니까 get(B에서)으로 꺼낼수도 있음
		//A먼저 실행돼야 B가 실행될수있음, loadOnStartup = 1, 2로 조절
		
		
	}
	

	
	
	public Map<String, String[]> getBtsMemberList() {
		Map<String, String[]> members = new LinkedHashMap<>();
		int idx = 1;
		members.put("B00"+idx++, new String[] {"RM", "/WEB-INF/views/bts/rm.jsp"});
		members.put("B00"+idx++, new String[] {"뷔", "/WEB-INF/views/bts/bui.jsp"});
		members.put("B00"+idx++, new String[] {"지민", "/WEB-INF/views/bts/jimin.jsp"});
		members.put("B00"+idx++, new String[] {"진", "/WEB-INF/views/bts/jin.jsp"});
		members.put("B00"+idx++, new String[] {"슈가", "/WEB-INF/views/bts/suga.jsp"});
		members.put("B00"+idx++, new String[] {"정국", "/WEB-INF/views/bts/jungkuk.jsp"});
		return members;
	}
   
   public String[] getMemberContent(String code) {
      String[] content = getBtsMemberList().get(code); //넘어온 값을 넘김
      return content;
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      1. 생략
//      2.
      Map<String, String[]> bts = getBtsMemberList();
//      3.
      req.setAttribute("bts", bts);
//      4.
      String viewName = "/jsonView.do";
//      5.
      req.getRequestDispatcher(viewName).forward(req, resp);
   
   }
}
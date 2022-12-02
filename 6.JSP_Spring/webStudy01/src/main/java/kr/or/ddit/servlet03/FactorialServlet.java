package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/02/factorial.do")
public class FactorialServlet extends HttpServlet{
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//_JSPService(request, response)
	 	String numParam = req.getParameter("number");
	 	if(numParam == null || !numParam.matches("\\d{1,2}")){
	 		//ui가 필요 없기 때문에 null이 없는지 아닌지는 신경쓸 필요 없음 numParam == null
	 		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	 		return;
	 	}
	 
 		//jsp에서는 전역변수 될수있으면 쓰면안됨 
 		int input = Integer.parseInt(numParam); 
 		String pattern = "%d! = %d";
 		int result = fact(input);
 		String expr = String.format(pattern, input, result); 
	 	
 		
 		try(
 			PrintWriter out =  resp.getWriter();
 		)
 		{
			out.print(expr);
		} 
 		
	}
	
	
 	
 	

	private int fact(int n) {
		
		if(n<0)
			throw new IllegalArgumentException("음수는 연산 불가");
		if (n <= 1)
			return n;
		else 
			return fact(n-1) * n;
	}


}

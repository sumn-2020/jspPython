package kr.or.ddit.memo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo")
public class MemoControllerServlet extends HttpServlet{
	
	
	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
	private MemoVO memo;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//"요청분석"단계 굳이 넣을 필요 없음 - memoVO전체를 불러오기 때문에 필요 없음
		//만약 작업하려면 아래와 같이 하면됨 
		String accept = req.getHeader("Accept");
		if(accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE); //나는 xml을 제공할 수 없다 
			return;
		}
		
		///////////////////////////////////////////////////////////
		
	
		// 모델확보
		List<MemoVO> memoList = dao.selectMemoList();
		
		//모델공유
		//req에 setAttribute라는 걸 저장하는데 memoList라는 키를 가진 memoList 값 세팅 
		req.setAttribute("memoList", memoList);
		//System.out.println(memoList);

		//마샬링하기 위해서  뷰로이동
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp); //viewName 여기에 req, resp 같이 보여줌 


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Post-Redirect-Get : PRG pattern
		MemoVO memoParam = getMemoFromRequest(req);//요청받아서 이 메소드를 실행하겠다 

		
		//public int insertMemo(MemoVO memo);
		//String으로 받아온 req객체를 MemoVO객체화 할 수 밖에 없음 왜냐? insertMemo에 넣어줘야 되니까.
		// insertMemo에는 파라미터 값을 MemoVO로만 받을 수 있기 때문에 String으로 들어오는 req는 받으면 오류
		dao.insertMemo(memoParam); 
		resp.sendRedirect(req.getContextPath() + "/memo");
		//getrequestDispathcer.forward대신 sendRedirect 썻음 
		//왜냐? 내 정보를 지워주기 위해서 안지워주면 내가 작성한 모든 정보들을 담고 넘겨줌 
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) { 
		
		MemoVO memo = new MemoVO(); //메모객체를 만들고 
		
		//앞단에서 파라미터 값 받아오고 
		String writer = req.getParameter("writer");
		System.out.println(writer);
		String date = req.getParameter("date");
		String content = req.getParameter("content");
		
		//memoVO에 추가 set
		//memoVO에서 데이터를 뽑아올땐 getWriter
		memo.setWriter(writer);
		memo.setDate(date);
		memo.setContent(content);

		return memo;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
}








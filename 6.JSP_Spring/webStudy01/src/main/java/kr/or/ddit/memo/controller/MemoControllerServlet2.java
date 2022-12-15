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

@WebServlet("/memo11")
public class MemoControllerServlet2 extends HttpServlet{
	
	
	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
	private MemoVO memo;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//"요청분석"단계 굳이 넣을 필요 없음 - memoVO전체를 불러오기 때문에 필요 없음
		//만약 작업하려면 아래와 같이 하면됨 
		String accept = req.getHeader("Accept");
		if(accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		
		///////////////////////////////////////////////////////////
		
	
		// 모델확보
		List<MemoVO> memoList = dao.selectMemoList();
		
		//모델공유
		req.setAttribute("memoList", memoList);
		//System.out.println(memoList);

		//마샬링하기 위해서  뷰로이동
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Post-Redirect-Get : PRG pattern
		MemoVO memo = getMemoFromRequest(req);

		dao.insertMemo(memo);
		resp.sendRedirect(req.getContextPath() + "/memo");
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) {
	

		return null;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
}








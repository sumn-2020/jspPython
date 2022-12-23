package kr.or.ddit.memo.dao;

import java.util.List;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	
  	public List<MemoVO> selectMemoList();
  	public int insertMemo(MemoVO memo);
  	public int updateMemo(MemoVO memo); //primary key 값을 가지는 값이 꼭 필요함 
  	public int deleteMemo(int code);
	

}

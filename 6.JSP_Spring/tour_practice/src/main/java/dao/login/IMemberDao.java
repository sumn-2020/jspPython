package dao.login;

import vo.MemberVO;

public interface IMemberDao {


	
	/**
	 * 회원 로그인
	 * @param mv
	 * @return
	 */
	public MemberVO loginMember(MemberVO mv);
	
	
	
}

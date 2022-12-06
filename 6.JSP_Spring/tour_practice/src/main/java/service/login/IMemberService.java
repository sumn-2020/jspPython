package service.login;

import vo.MemberVO;

public interface IMemberService {
	
	/**
	 * 회원로그인
	 * @param mv
	 * @return
	 */
	public MemberVO loginMember(MemberVO mv);
		
		
}

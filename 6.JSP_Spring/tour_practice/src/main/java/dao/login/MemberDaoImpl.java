package dao.login;

import dao.common.MyBaitsDao;
import vo.MemberVO;

public class MemberDaoImpl extends MyBaitsDao implements IMemberDao {
	
	
	//싱글톤 
	private static MemberDaoImpl dao;
	private MemberDaoImpl() {
	}
	public static MemberDaoImpl getDao() {
		if(dao == null)
			dao = new MemberDaoImpl();
		return dao;
	}
	

	
	//회원로그인
	@Override
	public MemberVO loginMember(MemberVO mv) {
		return selectOne("member.loginMember", mv); //xml에 있는 loginMember에 mv 넘겨주기 
	}
	
	
	
	
	
	
}

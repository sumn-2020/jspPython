package service.login;

import dao.common.MyBaitsDao;
import dao.login.MemberDaoImpl;
import vo.MemberVO;

public class MemberServiceImpl extends MyBaitsDao implements IMemberService {

	private MemberDaoImpl dao;
	
	//싱글톤
	private static MemberServiceImpl service;
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getDao();
	}
	public static MemberServiceImpl getService() {
		if(service == null) 
			service = new MemberServiceImpl();
		return service;
	}
	
	
	//회원로그인
	@Override
	public MemberVO loginMember(MemberVO mv) {
		return this.dao.loginMember(mv); //dao에있는 loginMember에 mv 넘기기
	}
	


	
	
	
	
}

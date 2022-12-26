package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	//결합력 최상 -> 여기가 바뀌면 dao도 내용물 바뀜 
	private MemberDAO memberDAO = new MemberDAOImpl(); //레이어간의 의존관계 형성! 여기서는 싱글톤 방식 사용안함 
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	//회원 목록 조회
	@Override
	public List<MemberVO> retrieveMemberList() {

		List<MemberVO> memberList = memberDAO.selectMemberList();
		return memberList;
	}

	//회원상세조회
	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDAO.selectMember(memId);
		if(member==null) {
			throw new UserNotFoundException(String.format(memId+"에 해당하는 사용자 없음"));
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

}

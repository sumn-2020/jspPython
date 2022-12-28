package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	//결합력 최상 -> 여기가 바뀌면 dao도 내용물 바뀜 
	private MemberDAO memberDAO = new MemberDAOImpl(); //레이어간의 의존관계 형성! 여기서는 싱글톤 방식 사용안함 
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		try {
			//memberDAO.selectMember(memId) 이렇게 해도 되지만 retrieveMember가 똑같은거 가지고있
			retrieveMember(member.getMemId()); //회원id 있는지 찾아보고 
			
			//1. 
			result = ServiceResult.PKDUPLICATED; //만약 pk 중복일 경우(=id가 중복일 경우, 기존회원일 경우)
			
		} catch (UserNotFoundException e) {
			//2.  id가 없을 경우(신규회원일 경우) => insertMember실행! 
			int rowcnt = memberDAO.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL; //rowcnt가 0 이상이면 ok출력 아니면 fail 출력 
		}
		
		return result;
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

		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		//ServiceResult result = authService.authenticate(member); //call by reference(ObjectPlayground.java) => 안전하지 않음 
		//member : 아이디, 패스워드, 새로 입력한 정보들 
		//authenticate : copyProperties(member, savedMember) => 수정할 데이터가 입력할 데이터를 덮어씌워버림 
		
		ServiceResult result = authService.authenticate(inputData);
		if(ServiceResult.OK.equals(result)) {
			int rowcnt = memberDAO.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}


}
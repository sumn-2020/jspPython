package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		
		//1. 이 사람이 있는지 여부 판단
		MemberVO savedMember = memberDAO.selectMember(member.getMemId());


		
		if(savedMember == null) { //그런 사람 없으면 
			throw new UserNotFoundException(String.format("%s 사용자 없음", member.getMemId()));//예외발생
		}
		
		//2. 비번확인
		String inputPass= member.getMemPass(); //넘어온 비번
		String savedPass = savedMember.getMemPass(); //기존에 가지고있는 비번
		ServiceResult result = null;
		
		if(savedPass.equals(inputPass)) {
			
			try {
//				member.setMemName(savedMember.getMemName()); //인증에 성공했을 때 savedMember안에 저장되어있는 MemName을 가져올 수 있음 
//				아예 처음부터 memberVO안에 memberVO내용을 저장해 둘수있다면? reflection사용
				BeanUtils.copyProperties(member, savedMember);
				result = ServiceResult.OK;
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}

			
		}else {
			result = ServiceResult.INVAILDPASSWORD;
		}
		
		return result;
	}

}

package kr.or.ddit.member.service;

import static org.junit.Assert.*; //여러가지 상황에 대한 예측이 가능함 

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceTest {

	private MemberService service= new MemberServiceImpl();
	private MemberVO member;
	
	@Before
	public void setUp() throws Exception {
		member = new MemberVO();
//		member.setMemId("a001");
	}

	@Test
	public void testCreateMember() {
		ServiceResult rowcntresult =  service.createMember(member);
/*		result == ServiceResult.OK
			result == ServiceResult.FAIL
			result == ServiceResult.PKDUPLICATED*/
		
	}

	@Test
	public void testRetrieveMemberList() {
		
		List<MemberVO> memberList =  service.retrieveMemberList();
		assertNotEquals(0, memberList.size());
		
	}

	@Test
	public void testRetrieveMember() {
		
		
		
		
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}

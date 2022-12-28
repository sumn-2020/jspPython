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
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemRegno1("12333");
		member.setMemRegno2("12333");
		member.setMemBir("2000-01-01");
		member.setMemZip("0000");
		member.setMemAdd1("주소1");
		member.setMemAdd2("주소2");
		member.setMemHometel("집");
		member.setMemComtel("회사");
		member.setMemHp("폰");
		member.setMemMail("메일");
		member.setMemJob("직업");
		member.setMemLike("취미");
		member.setMemMemorial("기념일");
		member.setMemMemorialday("기념");
		member.setMemMileage(123);
		member.setMemDelete("1");
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
		
		ServiceResult rowcntresult =  service.modifyMember(member);
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}

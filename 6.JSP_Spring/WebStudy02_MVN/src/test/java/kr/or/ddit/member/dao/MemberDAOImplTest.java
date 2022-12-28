package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	
	private MemberDAO dao = new MemberDAOImpl();
	private MemberVO member;
	
	
	@Before
	public void setUp() throws Exception { //모든 메소드 실행전에 미리 실행됨 
		member = new MemberVO(); //mybatis에는 null값에 대한 표현설정이 필요함
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("2000-01-01");
		member.setMemZip("0000");
		member.setMemAdd1("주소1");
		member.setMemAdd2("주소2");
	}

	@Test
	public void testInsertMember() {
		dao.insertMember(member);
	}

	@Test
	public void testSelectMemberList() {
		
		List<MemberVO> memberList = dao.selectMemberList();
		memberList.stream() //memberList하나하나에 접근
				  .forEach(System.out::println);
		
		
	}

	@Test
	public void testSelectMember() {
		
		MemberVO member = dao.selectMember("a001");
		System.out.println(member);
		member = dao.selectMember("123412a");
		assertNull(member);
		
		
		
	}

	@Test
	public void testUpdateMember() {
		
	
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}

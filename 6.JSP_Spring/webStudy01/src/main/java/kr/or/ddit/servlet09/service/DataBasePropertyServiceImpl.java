package kr.or.ddit.servlet09.service;

import java.util.List;

import kr.or.ddit.servlet09.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet09.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DatabasePropertyService {

	private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	
	
	@Override
	public List<DataBasePropertyVO> retrievePropertyList(String propertyName) {
		
		List<DataBasePropertyVO> list = dao.selectPropertyList(propertyName); //dao와 의존관계 형성 
		/*list.stream() //stream : 리스트 안쪽 요소 하나하나를 건들잉ㄹ수있음
			.forEach(vo->{
				System.out.println(vo);
			});
			.forEach(vo->System.out.println(vo));
			
		*/
		list.stream() //stream : 리스트 안쪽 요소 하나하나를 건들잉ㄹ수있음
			.forEach(System.out::println); 
		
		
		return list;
		
		
		
	}
	
}

package kr.or.ddit.exception;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 에러나 예외(Throwable) : 예상하지 못했던 비정상적인 처리상황
 * 	  - Error : 개발자가 직접 처리하지 않는 에러 계열
 * 	  - Exception : 개발자가 직접 처리 할 수 있는 예외 계열 
 * 
 * 			- checked exception (Exception) : 반드시 처리해야만 하는 예외 
 * 				ex) IOException, SQLException
 * 
 *          - unchecked exception (RuntimeException) : 처리하지 않더라도 메소드 호출구조를 통해 JVM에게 제어권이 전달되는 예외 
 *          	ex) NullPointerException, IndexOutOfBoundException
 * 					
 *
 * <예외처리방법>
 * 1) 직접처리(능동처리) : try(closable object 선언구조 )~catch~finally
 * 2) 위임처리(수동처리) : 메소드의 호출자에게 throws로 예외 제어권 위임
 * 
 * <커스텀 예외 사용방법>
 * 1) Exception이나 RuntimeException의 자식 클래스 정의(예외 클래스)
 * 2) throw 예외 인스턴스 생성
 *
 *
 */



public class ExceptionDesc {

	public static void main(String[] args) { //여기에 throws IOException 쓰면 이 예외는 최종적으로 vertual machine에게 떠넘겨짐
		
		//IOException에 관련된 코드 
		//버추얼머신한테 오류 알리지 않고 조용히 오류 처리하고 싶다면 throws IOException 쓰지 않고
		// try catch 써서 직접 오류 처리 하면 됨 
/*		try {
			String data  = getSampleData();
			System.out.println(data);
		}catch(IOException e) {
//			System.err.println(e.getMessage()); //무슨 오류인지 알려줘야 되니까 System.err 사용
			e.printStackTrace();
		}*/
		
		
		//runtimeException에 관련된 코드 
/*		try {
			System.out.println(getSampleDataWithRE());
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.out.println("정상처리 위장 가능 ");
		}*/
		
		
		//커스텀 예외클래스 사용하여 예외처리 (UserNotFoundException.java)
		try {
			System.out.println(getSampleDataWithRE());
		} catch (NullPointerException | UserNotFoundException e) {
			System.err.println(e.getMessage());
			System.out.println("정상처리 위장 가능 ");
		}
		
		
		//SQLException에 관련된 코드 
//		System.out.println(getSampleChangeException());
		

		
	}
	
	
	//IOException
	private static String getSampleData() throws IOException { // 내가 가진 예외를 떠넘기기 => 메인메소드로 떠넘겨짐
		String data = "SAMPLE";
		if(1==1) //이 if문은 무조건 만족한다.
			throw new IOException("강제발생예외");//일부러 예외를 만들고싶을 때 throw 사용 ( 내가 가진 예외를 떠넘기기 )
			
		return data;
	}
	
	//RuntimeException
/*	private static String getSampleDataWithRE() throws RuntimeException { 
		String data = "SAMPLERE";
		if(1==1) 
			throw new NullPointerException("강제발생예외");
		return data;
	}*/
	
	//커스텀 예외클래스 사용하여 예외처리 (UserNotFoundException.java)
	private static String getSampleDataWithRE(){ 
		String data = "SAMPLERE";
		if(1==1) 
			throw new UserNotFoundException("강제발생예외");
		return data;
	}
	
	
	//SQLException
	private static String getSampleChangeException(){ 
		String data = "SAMPLEREChangeException";
		try {
			if(1==1)
				throw new SQLException("강제발생예외");
			return data;
		} catch (SQLException e) {
			throw new RuntimeException(e); //상단에 throws가 없어도 있는 것처럼 동작하게 됨 => 이렇게만 적어도 버츄얼머신에 예외가 떠넘겨짐  
		}
	}
	
}











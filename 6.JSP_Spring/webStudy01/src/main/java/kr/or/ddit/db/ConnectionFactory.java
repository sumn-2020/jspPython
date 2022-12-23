package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory Object[Method] Pattern
 * 필요객체의 생성을 전담하는 객체를 별도 운영하는 구조 
 */

public class ConnectionFactory {

	
	//드라이버 로딩 
	private static String url = "";
	private static String user = "";
	private static String password = "";
	

	//ConnectionFactory 클래스가 메모리에 로딩될때 실행  => 이 안에 들어가있는 코드는  어플리케이션 통틀어서 단 한번 실행
	static {  
		
		String path = "/kr/or/ddit/db/dbInfo.properties"; //클래스패스 리소스

		//드라이버를 빌드패스에 추가
		try(
				InputStream is = ConnectionFactory.class.getResourceAsStream(path); //ConnectionFactory클래스 내부에서 아예 stream해오기 
		) {
			Properties dbInfo = new Properties();
			//외부에있는 properties 파일 로딩
			dbInfo.load(is);
			
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
			Class.forName(dbInfo.getProperty("driverClassName")); //properties 파일 안에있음 
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		} 
	}
	
	public static Connection getConnection() throws SQLException {
		

		Connection conn =   DriverManager.getConnection(url, user, password);
		return conn;
	}
}





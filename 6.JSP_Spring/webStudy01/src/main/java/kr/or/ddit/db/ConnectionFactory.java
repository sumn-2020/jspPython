package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 * 필요객체의 생성을 전담하는 객체를 별도 운영하는 구조 
 */

public class ConnectionFactory {

	
	//드라이버 로딩 
	private static String url = "";
	private static String user = "";
	private static String password = "";
	

	private static DataSource ds; // 커넥션 풀링을 위해서 넣어줌  
	
	
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
			
//			Class.forName(dbInfo.getProperty("driverClassName")); //properties 파일 안에있음 

			//여기에 커넥션 풀링 넣어보기 
			BasicDataSource bds = new BasicDataSource(); //커넥션에 관련된건 다 얘가 전담 => 이제부터 이 안에서 모든 커넥션들이 이루어짐  
			bds.setDriverClassName(dbInfo.getProperty("driverClassName")); //BasicDataSource얘가 dbInfo.getProperty("driverClassName")이걸 갖고 놀게됨 
			bds.setUrl(url); 
			bds.setUsername(user);
			bds.setPassword(password);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize")) ); //맨처음에 커넥션을 다섯개 만들어놓겠따
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle"))); //최대 놀릴수있는 커넥션은 다섯개 => setInitialSize하고 갯수는 같아야함 
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal"))); //다섯개 커넥션 다 쓴 후에 6번째 사람이 더 들어왔을 때 하나더 만듬 그 후에 다 쓴건 반납됨 (여유분 다섯개를 더 만들수있따)
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait"))); //11번째부터는 2초 기다려라 반납된거 있을때까지 반납된게 없으면 sqlException발생시킴 

			ds = bds;
			
			
			
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		} 
		
		
		
		
	}
	
	public static Connection getConnection() throws SQLException {
		

//		Connection conn =   DriverManager.getConnection(url, user, password);
//		return conn;
		
		return ds.getConnection();
		
		
	}
}





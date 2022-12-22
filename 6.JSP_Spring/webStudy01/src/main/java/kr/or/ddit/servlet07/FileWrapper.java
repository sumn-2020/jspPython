package kr.or.ddit.servlet07;

import java.io.File;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Adapter.java 파일 참고 
public class FileWrapper {

	//@JsonIgnore => 마샬링할때 이 부분은 무시해라 
	@JsonIgnore
	private File adaptee;
	
	//이걸 추가하면 adaptee가 없으면 이 클래스는 사용할 수 없는 객체가 되어버림
	public FileWrapper(File adaptee, ServletContext application) {  
		super();
		this.adaptee = adaptee;
		this.name = adaptee.getName(); // ex) test.hwp 가 받아온다면 톰캣은 알지 못하기 때문에 null값 출력됨 => optional을 사용해서 null들어왔을 때 경우 처리해줘야됨
		this.contentType = Optional.ofNullable(application.getMimeType(name))
									  .orElse("application/octet-stream");	
	}
	

	private String name;
	private String contentType;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	// 쓸데없는 주소값 : 이상하게 옴 
	// 근데 우리는 name하고 contentType만 필요한데 엉뚱한것들도 같이 오는건 불필요하니까
	//이렇게 원하는것만 뽑아서 쓰려면 wrapper 디자인 패턴 써야됨 



}

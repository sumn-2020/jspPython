package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;

/**
 * VO(Value Object), DTO(Data Transfer Object), Java Bean, Model
 *	 
 *
 *[ property ] : (자바에서 제공하는 데이터 딕셔너리를 참고해서 스키마 데이터사용할수있음) 
 * VARCHAR2 => String
 * CHAR => String
 * DATE => String  .. 
 *
 * JavaBean 규약의 조건 
 * 1. 값을 담을 수 있는 property를 정의해야됨   => String memId,  private String memBir ... 
 * 2. property 캡슐화해야됨 => private 붙이기 
 * 3. 캡슐화된 property에 접근할 수 있는 인터페이스 제공 => getter/setter 만들기 
 * 		- get[set] + property명의 첫문자를 대문자로 ->  결과물 : camel case 
 * 4. 객체의 상태 비교 방법 제공 => equals(), hashCode() => primary key 값만 만들면됨 
 *   ==, equals 
 * 5. 객체의 상태 확인 방법 제공 => toString
 *   상태를 노출 시키면 안되는 것들은 제외해야됨 (ex. password )  
 * 6. 객체 직렬화 가능 해야됨 => implements Serializable
 *   숨겨야 하는 것들은 transient 붙여서 직렬화 피해줄 것 : private transient String memPass;
 *  
 * 
 *  
 *  회원관리를 위한 Domain Layer
 *  
 *  
 *  
 *  
 *  
 */
public class MemberVO implements Serializable {
	
	//(groups={InsertGroup.class, UpdateGroup.class})
	//"가입"할때 + "수정"할때 검증하는 그룹 => memId
	//"가입"할때만 검증하는 그룹(groups=InsertGroup.class)   => memBir 
	
	
	//@NotBlank(groups= {Default.class, DeleteGroup.class}, message="아이디는 필수")  // 기본그룹, 기본그룹이면서 insert그룹, 기본그룹이면서 delete그룹 
	@NotBlank(groups= {Default.class, DeleteGroup.class})  
	private String memId;
	@NotBlank(groups= {Default.class, DeleteGroup.class}) //memPass은 비어있을수없음
	@Size(min=4, max=8) //4~8글자 이하
	@JsonIgnore //직렬화할때, 마샬링할때 스킵됨  (주민번호랑 비밀번호는 보이지 말아야 하니까 앞단에 넘겨줄 필요도 없음 )
	private transient String memPass; 
	@NotBlank
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", groups=InsertGroup.class) // 형식제한 => \\d : 숫자 한글자 소문자d =>  TO_DATE(#{memMemorialday, jdbcType=DATE},'YYYY-MM-DD')
	@NotBlank(groups=InsertGroup.class)
	private String memBir; 
	@NotBlank  //비어있을수없음
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	//@NotNull //마일리지에는 비어있지 않음을 쓰고싶다면 notBlank가 아니라 notNull을 써야됨 
	@Min(0)//마일리지 최소값 
	private Integer memMileage;
	private String memDelete;
	
	
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemRegno1() {
		return memRegno1;
	}
	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}
	public String getMemRegno2() {
		return memRegno2;
	}
	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemZip() {
		return memZip;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public String getMemHometel() {
		return memHometel;
	}
	public void setMemHometel(String memHometel) {
		this.memHometel = memHometel;
	}
	public String getMemComtel() {
		return memComtel;
	}
	public void setMemComtel(String memComtel) {
		this.memComtel = memComtel;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemJob() {
		return memJob;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public String getMemLike() {
		return memLike;
	}
	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}
	public String getMemMemorial() {
		return memMemorial;
	}
	public void setMemMemorial(String memMemorial) {
		this.memMemorial = memMemorial;
	}
	public String getMemMemorialday() {
		return memMemorialday;
	}
	public void setMemMemorialday(String memMemorialday) {
		this.memMemorialday = memMemorialday;
	}
	public Integer getMemMileage() {
		return memMileage;
	}
	
	public void setMemMileage(Integer memMileage) {
		this.memMileage = memMileage;
	}
	
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
	
	
	//객체의 상태 비교 방법 제공 => equals(), hashCode() => primary key 값만(memId) 만들면됨 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		return true;
	}
	
	// 주민번호랑 비밀번호는 보이지 말아야 하니까 앞단에 넘겨줄 필요도 없음 => 제외시킬것
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memBir=" + memBir + ", memZip=" + memZip
				+ ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHometel=" + memHometel + ", memComtel="
				+ memComtel + ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob + ", memLike="
				+ memLike + ", memMemorial=" + memMemorial + ", memMemorialday=" + memMemorialday + ", memMileage="
				+ memMileage + ", memDelete=" + memDelete + "]";
	}
	
	
	
	
	
	
	
	
	
	
}




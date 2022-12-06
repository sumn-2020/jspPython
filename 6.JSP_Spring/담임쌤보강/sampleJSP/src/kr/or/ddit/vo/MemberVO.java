package kr.or.ddit.vo;


//자바빈 클래스. 멤버변수 /기본생성자 /getter/ setter 메소드 
public class MemberVO {
    
	//멤버 변수
	private String userId;
	private String passwd;
	private String userName;
	private String postNum;
	private String phone1;
	private String phone2;
	private String phone3; 
	private String gender; 
	private String hobby1; 
	private String hobby2; 
	private String hobby3;
	private String comment;
	
	
	//기본생성자
	public MemberVO() {
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby1() {
		return hobby1;
	}

	public void setHobby1(String hobby1) {
		this.hobby1 = hobby1;
	}

	public String getHobby2() {
		return hobby2;
	}

	public void setHobby2(String hobby2) {
		this.hobby2 = hobby2;
	}

	public String getHobby3() {
		return hobby3;
	}

	public void setHobby3(String hobby3) {
		this.hobby3 = hobby3;
	}

	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", postNum=" + postNum
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", gender=" + gender
				+ ", hobby1=" + hobby1 + ", hobby2=" + hobby2 + ", hobby3=" + hobby3 + ", comment=" + comment + "]";
	}
	

}

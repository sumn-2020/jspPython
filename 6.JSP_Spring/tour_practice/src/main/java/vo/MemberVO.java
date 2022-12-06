package vo;

public class MemberVO {
	
	private String memId;
	private String memNm;
	private String memRegno1;
	private String memRegno2;
	private String memPw;
	private String memTel;
	private String memAddr;
	private String memAddr2;
	private String memEmail;
	private String memStatus;
	private String memMileage;
	private String atchFile;// 첨부파일
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
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
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemAddr2() {
		return memAddr2;
	}
	public void setMemAddr2(String memAddr2) {
		this.memAddr2 = memAddr2;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(String memMileage) {
		this.memMileage = memMileage;
	}
	public String getAtchFile() {
		return atchFile;
	}
	public void setAtchFile(String atchFile) {
		this.atchFile = atchFile;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memNm=" + memNm + ", memRegno1=" + memRegno1 + ", memRegno2=" + memRegno2
				+ ", memPw=" + memPw + ", memTel=" + memTel + ", memAddr=" + memAddr + ", memAddr2=" + memAddr2
				+ ", memEmail=" + memEmail + ", memStatus=" + memStatus + ", memMileage=" + memMileage + ", atchFile="
				+ atchFile + "]";
	}
	


}

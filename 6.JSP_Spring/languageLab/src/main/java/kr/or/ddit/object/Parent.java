package kr.or.ddit.object;

public class Parent {
	
	private String code="default";
	
	//후크메소드
	public void method() {
		System.out.println("parent method execute");
	}
	
	public void template() {
		this.method(); //this => 생성된 instance자체 
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	//두개의 객체를 비교할때 주소값으로 하지 않고 상단에 있는 필드값(private String code="default")으로 비교함 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parent other = (Parent) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}

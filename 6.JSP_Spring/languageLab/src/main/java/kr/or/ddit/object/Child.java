package kr.or.ddit.object;

public class Child extends Parent {
	@Override
	public void method() {
		System.out.println("child method execute");
	}
	@Override
	public void template() {
		this.method(); //child안에 있는 method실행 
		//super.method(); // 부모의 메소드 실행
	}
}

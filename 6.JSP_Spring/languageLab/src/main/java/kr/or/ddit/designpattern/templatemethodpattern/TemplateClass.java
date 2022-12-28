package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	
	// ******* template Method ******* 
	public final void template() { //이 안에서는 1,2,3 단계가 딱 고정되어있음
		
		//순서 정의
		 //이 순서가 바뀌지 않는 전형성을 가지고있어야됨 
		 stepOne();
		 stepTwo();
		 stepThree();
		
		
	}
	
	
	//******* hook method ******* 
	//형태 고정   
	private void stepOne() {
		System.out.println("1단계");
	}
	
	protected abstract void stepTwo(); 
	// 2단계에서는 하는 일이 다 달라서 형태를 고정시키지 않을 거임 
	// 얘는 인스턴스를 생성할수 없음  => 상단에 클래스명옆에 abstract 붙여야됨 
	// abstract =>  파생클래스 안에서 사용 됨 
	
	//형태 고정   
	private void stepThree() {
		System.out.println("3단계");
	}
	
	
	
}

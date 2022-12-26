package kr.or.ddit.designpattern.templatemethodpattern;

public class DerivedClass1 extends TemplateClass {

	
	//TemplateClass.java속에서 template method에 있던 abstract 붙어있는 hook method 오버라이드로 가져옴 
	@Override
	protected void stepTwo() {
		System.out.println("A방식으로 구현된 2단계");
	}

}

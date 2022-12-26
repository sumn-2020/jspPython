package kr.or.ddit.designpattern.templatemethodpattern;

import java.util.Arrays;

public class Playground {
	public static void main(String[] args) {
		
		//파생메소드 배열로 만들기 
		TemplateClass[] array = new TemplateClass[] {new DerivedClass1(), new DerivedClass2()};
		Arrays.stream(array) //arry를 대상으로 해서 item에 하나하나 접근 가능 
			  .forEach(TemplateClass::template);
		
	}
	
}

package kr.or.ddit.enumpkg;

import kr.or.ddit.servlet04.RealOperator;

public enum OperatorType {
	//enum : "상수"의 정렬
	
	//PLUS, MINUS, MULTIPLY, DIVIDE : OperatorType의 객체
	PLUS('+', (l, r)->{return l+r;}), //람다식 : 파라미터 (l, r)-> 메소드 {return l+r;}
	MINUS('-', (l, r)-> l-r),  //메소드 실행코드 안에 하나바껭 없으면 바디({}) 없애도 무관함
	MULTIPLY('*', (l, r)-> l*r), 
	DIVIDE('/', (l, r)-> l/r),
	REST('%', (l, r)-> l%r);
	
	private char sign;
	private RealOperator realOperator;

	//생성자 private  => 외부에서 사용할수 없음 => 이게 생성되는 순간 기본생성자 사라짐 
	//PLUS('+'), MINUS('-'), MULTIPLY('*'), DIVIDE('/'); 이렇게 따로 생성자 만들어줘야됨 
	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator; //상수를 통해서 해야될 연산까지 결정할 수 있게됨 
	}

	public char getSign() {
		return sign;
	}

	
	//답만 필요할 경우
	public int operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
		//realOperator 여기서 +를 하던지 -를 하던지 람다식이 알아서 하게됨
		// realOperator한테 연산을 맡기게 됨 
	}
	
	
	
	public String getExpression(int leftOp, int rightOp) {
		//2+2 = 4
		//d: 숫자, s: 문자 
		return String.format("%d %s %d = %d", leftOp, sign, rightOp, realOperator.operate(leftOp, rightOp));//realOperator 여기서 +를 하던지 -를 하던지 람다식이 알아서 하게됨
		
	}
	
	
	
	
}

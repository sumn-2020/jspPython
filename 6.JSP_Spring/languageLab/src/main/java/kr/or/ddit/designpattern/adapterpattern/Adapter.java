package kr.or.ddit.designpattern.adapterpattern;

public class Adapter implements Target {

	//adapter는 adaptee가 없으면 작동못함
	//=> 이런 객체를  wrapper 객체라고 함
	private Adaptee adaptee; 
	public Adapter(Adaptee dapatee) { //wrapper instance 
		super();
		this.adaptee = dapatee;
	}
	

	@Override
	public void request() {
		adaptee.specificRequest(); //여기서 adaptee에 있는 specificRequest를 사용할수 있음 
	}

}

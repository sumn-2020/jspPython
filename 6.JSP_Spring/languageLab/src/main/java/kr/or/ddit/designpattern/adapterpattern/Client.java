package kr.or.ddit.designpattern.adapterpattern;

//Target , OtherConcrete, Client, Adaptee


public class Client {
	private Target target;
	
	public static void main(String[] args) {
		Client client = new Client();
//		client.target = new OtherConcrete();

//		client.target = new Adaptee(); //target에 바로 adaptee가 붙을 수 없음 
		client.target = new Adapter(new Adaptee()); //Adapter속에 있는 adaptee를 통해서만 사용할 수 있음
		
		client.target.request();
		
	}
}

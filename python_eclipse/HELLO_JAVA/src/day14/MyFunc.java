package day14;

public class MyFunc {

	public static void showNum() {
		for(int i = 1; i<=100000; i++) {
			System.out.println(i);
			if(i%100 == 0) {
				System.out.println();
			}
		}
	}
	
	public static void showAscii() {
		for(int i = 1; i<=100000; i++) {
			System.out.println((char)i);
			if(i%100 == 0) {
				System.out.println();
			}
		}
	}
	
	
	public static void main(String[] args) {
		showNum();
		showAscii();
	}
	
}

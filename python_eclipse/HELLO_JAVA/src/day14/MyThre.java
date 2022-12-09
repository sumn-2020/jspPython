package day14;




//총 스레드 3개
//- 기본스레드 한개
//- showNum, showAscii스레드 두개 
public class MyThre {
	
	public static void showNum() {
		new Thread(new Runnable() {
			public void run() {
				
				for(int i = 1; i<=100000; i++) {
					System.out.println(i);
					if(i%100 == 0) {
						System.out.println();
					}
				}
				
				
				
			}
		}).start(); //thread가 동작될려면 start 시켜줘야됨 
	}
	
	
	public static void showAscii() {
		
		new Thread(new Runnable() {
			public void run() {
				
				for(int i = 1; i<=100000; i++) {
					System.out.println((char)i);
					if(i%100 == 0) {
						System.out.println();
					}
				}

				
			}
		}).start();
		

		
	}
	
	

	public static void main(String[] args) {
		showNum();
		showAscii();
	}

}

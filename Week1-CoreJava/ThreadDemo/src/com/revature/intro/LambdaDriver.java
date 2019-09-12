package com.revature.intro;

public class LambdaDriver {

	public static void main(String[] args) {
		
		Runnable job = () -> {
			System.out.println("Hello World");
		};
		
		Thread t = new Thread(job);
		
		t.start();
		
		Thread t2 = new Thread(()-> System.out.println("Also Hello World"));
		t2.start();
		
	}
	
}

package com.mycompany.main;

public class Application {

	public static void main(String[] args) {
		
		System.out.println(StaticClass.sum(13.1, 15.7));
		
		FinalClass x = new FinalClass();
		
		System.out.println(x.k);
		
		ConcreteClass y = new ConcreteClass();
		y.run();
	}
}

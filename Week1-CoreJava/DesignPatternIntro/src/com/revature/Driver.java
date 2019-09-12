package com.revature;

public class Driver {
	
	public static void main(String[] args) {
		MySingleton singleton1 = MySingleton.getInstance();
		System.out.println(singleton1.getValue());
		singleton1.setValue(5);
		MySingleton singleton2 = MySingleton.getInstance();
		System.out.println(singleton2.getValue());
		System.out.println(singleton1 == singleton2);
	}

}

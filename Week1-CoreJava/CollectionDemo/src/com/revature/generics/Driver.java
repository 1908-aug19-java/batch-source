package com.revature.generics;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		
		ArrayList<String> arrList = new ArrayList<>();
		arrList.add("test");
//		int str = (int) arrList.get(0);
		String str = arrList.get(0);
//		System.out.println(str);
		
		Integer[] intArr = {1,2,3,4,5};
		
		printAll(intArr);
		
		String[] strArr = {"Hello", "World", "Blue", "Green"};
		
		printAll(strArr);
		

	}
	
//	public static void printAll(Integer[] arr) {
//		for(Integer i: arr) {
//			System.out.println(i);
//		}
//	}
//	
//	public static void printAll(String[] arr) {
//		for(String i: arr) {
//			System.out.println(i);
//		}
//	}
	
	public static <T> void printAll(T[] arr) {
		for(T item: arr) {
			System.out.println(item);
		}
	}

}

package com.revature.sync;

public class Driver {

	public static void main(String[] args) {
		/*
		StringBuilder sbuilder =  new StringBuilder();
		StringBuffer sbuffer = new StringBuffer();
		Runnable job = new StringTestRunnable(sbuilder, sbuffer);
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("StringBuilder result:");
		System.out.println(sbuilder);
		System.out.println();
		
		System.out.println("StringBuffer result:");
		System.out.println(sbuffer);
		*/
		
		Counter c = new Counter();
		Runnable job = new CountTestRunnable(c);
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Count: "+ c.value);
	}

}

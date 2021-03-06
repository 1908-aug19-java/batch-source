package com.revature;

public class MySingleton {
	
	private static MySingleton mySingleton;
	
	private int value;
	
	private MySingleton() {
		super();
	}

	public static MySingleton getInstance() {
		if(mySingleton == null) {
			mySingleton = new MySingleton();
		}
		return mySingleton;
	}
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
}

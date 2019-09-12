package com.revature.sync;

public class StringTestRunnable implements Runnable {

	StringBuilder sbuilder = new StringBuilder();
	StringBuffer sbuffer = new StringBuffer();
	
	public StringTestRunnable() {
		super();
	}
	
	public StringTestRunnable(StringBuilder sbuilder, StringBuffer sbuffer) {
		super();
		this.sbuffer = sbuffer;
		this.sbuilder = sbuilder;
	}
	
	@Override
	public void run() {
		for(int i=0; i<40; i++) {
			sbuilder = sbuilder.append("~");
			sbuffer = sbuffer.append("~");
		}
		
	}

}

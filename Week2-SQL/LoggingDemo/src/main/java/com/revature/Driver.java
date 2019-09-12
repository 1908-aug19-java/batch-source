package com.revature;

import org.apache.log4j.Logger;

public class Driver {

	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
//		log.trace("logging a trace");
//		log.debug("debug");
//		log.info("info");
//		log.warn("warn");
//		log.error("error");
//		log.fatal("fatal");
		
		calculate(3,4);
		calculate(0,4);
		calculate(0,0);
		
		
	}
	
	public static int calculate(int x, int y) {
		
		if(x==0 && y==0) {
			log.error("incorrect input, exception thrown");
			throw new RuntimeException();
		}
		
		int sum = x+y;
		log.info("caluclation was successful! Added "+x+" and "+y+". Result was "+sum);
		return sum;
	}

}

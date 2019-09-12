package com.revature;

public class Calculator {
	
	/*
	 * We'll create a calculator that accepts a string input 
	 * It can accept 0, 1, or 2 numbers separated by a comma
	 * 
	 * If we provide 0 numbers (empty string) we want our
	 *    calculator to return 0
	 * If we provide 1 we want it to return that number
	 * If we provide 2 we want it to return the sum of them
	 * If we provide an invalid or null input, we want it to
	 *    return -1
	 * 
	 */
	
	public static int add(String input) {
		if(input == null) {
			return -1;
		}
		if("".equals(input)) {
			return 0;
		}
		
		String[] values = input.trim().split(",");
		if(values.length==1) {
			if(values[0].matches("^\\d+$")) {
				return Integer.parseInt(values[0]);
			} else {
				return -1;
			}
		}
		
		if(values.length==2) {
			if(values[0].matches("^\\d+$") && values[1].matches("^\\d+$")) {
				return (Integer.parseInt(values[0])+Integer.parseInt(values[1]));
			}
		}
		
		return -1;
	}

}

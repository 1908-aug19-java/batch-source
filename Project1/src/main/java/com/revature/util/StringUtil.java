package com.revature.util;

import java.util.Random;

public class StringUtil {
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	private static final String USERNAME_REGEX = "^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	private static final String MONEY_REGEX = "[^.0-9]";
	private static final String TOKEN_REGEX = "^[A-Za-z0-9-_=]{10}[.]{1}[A-Za-z0-9-_=]{10}[.]{1}[A-Za-z0-9-_.+/=]{10}$";
	
	private StringUtil() {
		
		throw new IllegalStateException("Utility Class");
	}
	
	public static boolean isValidUsername(String username) { return username.matches(USERNAME_REGEX);}
	public static boolean isValidEmail(String email) { return email.matches(EMAIL_REGEX);}
	public static boolean isValidAmount(double num) {
		
		String amount = Double.toString(num);
		
		if(amount.matches(MONEY_REGEX)) return false;
		
		if (num <= 0) return false;
		
		int idx = amount.indexOf('.');
		
		if(idx == -1) return true;
		
		return(amount.substring(idx).length() <= 3);
		
	}
	
	public static boolean isValidToken(String token) {  
		
		if(token == null) return false;

		return EncryptionUtil.decrypt(token).matches(TOKEN_REGEX);
	}
	
	public static String getRandomString() {
		
		int leftLimit = 97;
	    int rightLimit = 122;
	    int targetStringLength = 10;
	    
	    Random random = new Random();
	    
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    
	    for (int i = 0; i < targetStringLength; i++) {
	    	
	        int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
		
		return buffer.toString();
	}
}
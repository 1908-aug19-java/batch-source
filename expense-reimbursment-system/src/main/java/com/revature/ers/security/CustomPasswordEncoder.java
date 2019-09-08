package com.revature.ers.security;
import org.mindrot.jbcrypt.BCrypt;


public class CustomPasswordEncoder {

	public String encode(CharSequence rawPassword) {
		String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
		return hashed;
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}

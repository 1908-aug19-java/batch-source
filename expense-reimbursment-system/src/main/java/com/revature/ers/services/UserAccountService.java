package com.revature.ers.services;

public interface UserAccountService {

	boolean isValidfirstName(String firstName);
	boolean isValidlastName(String lastName);
	boolean isValidEmail(String email);
	boolean emailExists(String email);
	boolean isValidPassword(String password);
	boolean passwordsAreEqual(String password, String psw_repeat);
	boolean areValidCredentials(String email, String password);
}

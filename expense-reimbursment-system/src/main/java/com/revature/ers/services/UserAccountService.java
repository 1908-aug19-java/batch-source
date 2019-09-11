package com.revature.ers.services;

public interface UserAccountService {

	boolean isValidName(String lastName);
	boolean isValidEmail(String email);
	boolean emailExists(String email);
	boolean isValidPassword(String password);
	boolean isSamePassword(String password, String confirm_password);
	boolean areValidCredentials(String email, String password);
}

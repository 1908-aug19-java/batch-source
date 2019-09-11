package com.revature.ers.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.CustomPasswordEncoder;

public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountDAO userAccountDAO = new UserAccountDAOimpl();

	@Override
	public boolean areValidCredentials(String email, String password) {
		try {
			UserAccount userAccount = userAccountDAO.findByEmail(email).get();
			CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
			if (!customPasswordEncoder.matches(password, userAccount.getPassword())) {
				return false;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidName(String name) {
		String regex = "[A-Za-z-']{1,20}";
		if (!name.matches(regex)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		if (!email.matches(regex)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean emailExists(String email) {
		List<UserAccount> userAccounts = userAccountDAO.findAll();
		for (UserAccount userAccount : userAccounts) {
			if (userAccount.getEmail().equals(userAccount.getEmail())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isValidPassword(String password) {
		String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}";
		if (!password.matches(regex)) {
			return false;
		}
		return true;
	}

	public boolean isSamePassword(String password, String confirm_password) {
		return password.equals(confirm_password);
	}
}

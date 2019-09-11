package com.revature.ers.services;

import java.util.NoSuchElementException;


import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.CustomPasswordEncoder;

public class UserAccountServiceImpl implements UserAccountService {

	UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	
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
	public boolean isValidfirstName(String firstName) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isValidlastName(String lastName) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isValidPassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean passwordsAreEqual(String password, String psw_repeat) {
		// TODO Auto-generated method stub
		return false;
	}

}

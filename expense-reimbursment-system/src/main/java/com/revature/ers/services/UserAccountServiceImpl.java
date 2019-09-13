package com.revature.ers.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.ers.dao.AuthorityDAO;
import com.revature.ers.dao.AuthorityDAOImpl;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.Authority;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.SecurityHandler;

public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);
	private UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	private AuthorityDAO authorityDAO = new AuthorityDAOImpl();

	@Override
	public boolean areValidCredentials(String email, String password) {
		try {
			UserAccount userAccount = userAccountDAO.findByEmail(email).get();
			SecurityHandler securityHandler = new SecurityHandler();
			if (!securityHandler.hashMatches(password, userAccount.getPassword())) {
				return false;
			}
		} catch (NoSuchElementException e) {
			LOGGER.error(e);
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
			if (userAccount.getEmail().equals(email)) {
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

	public boolean isSamePassword(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}
	
	public boolean validate(UserAccount userAccount, String confirmPassword) {
		boolean[] validation = { isValidName(userAccount.getFirstName()),
				isValidName(userAccount.getLastName()),
				isValidEmail(userAccount.getEmail()),
				emailExists(userAccount.getEmail()),
				isValidPassword(userAccount.getPassword()),
				isSamePassword(userAccount.getPassword(), confirmPassword) };
		boolean isValid = true;
		for (boolean b : validation) {
			isValid = isValid && b;
		}
		return isValid;
	}
	
	public UserAccount createUserAccount(HttpServletRequest request) {
		UserAccount userAccount = null;
		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String authorityName = request.getParameter("authority");
			Authority authority = authorityDAO.findByName(authorityName).orElseThrow(NullPointerException::new);
			return new UserAccount(firstName, lastName, email, password, authority);
		} catch (NullPointerException e) {
			LOGGER.error(e);
		}
		return userAccount;
	}
}

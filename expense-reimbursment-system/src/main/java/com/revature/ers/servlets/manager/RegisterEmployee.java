package com.revature.ers.servlets.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.dao.AuthorityDAO;
import com.revature.ers.dao.AuthorityDAOImpl;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.Authority;
import com.revature.ers.models.UserAccount;
import com.revature.ers.util.Authorities;

/**
 * Servlet implementation class RegisterEmployee
 */
@WebServlet("/manager/register-employee")
public class RegisterEmployee extends HttpServlet {
	private UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	private AuthorityDAO authorityDAO = new AuthorityDAOImpl();
	String warning;
	String newline = "\r\n";
	String bullet = "&#8226; ";
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/manager/registerEmployee.html");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		warning = "";
//		UserAccount userAccount = createUserAccount(request, response);
//		validateFirstname(userAccount);
//		validateLastname(userAccount);
//		validateEmail(userAccount);
//		validatePassword(request, response);
//
//		boolean check = warning.equals("");
//		RequestDispatcher requestDispatcher;
//		if (check) {
//			userAccount.setPassword(request.getParameter("password"));
//			response.sendRedirect(request.getContextPath() + "/manager");
//		} else {
//			request.setAttribute("EMPLOYEE", user);
//			request.setAttribute("WARNINGS", warning);
//			requestDispatcher = request.getRequestDispatcher("/html/manager/registerEmployee.html");
//			requestDispatcher.forward(request, response);
//		}
//
//	}
//
//	private void validatePassword(HttpServletRequest request, HttpServletResponse response) {
//		String password = request.getParameter("password");
//		String psw_repeat = request.getParameter("psw_repeat");
//		String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}";
//		if (!password.matches(regex)) {
//			warning += "Password requires:" + newline + bullet + "At least one number " + newline + bullet
//					+ "At least one lowercase " + newline + bullet + "At least one uppercase letter." + newline + bullet
//					+ "At least six characters." + newline;
//		}
//		if (!password.equals(psw_repeat)) {
//			warning += "Passwords do not match.";
//		}
//		password = null;
//		psw_repeat = null;
//	}
//
//	private void validateEmail(User user) {
//		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//		if (!user.getEmail().matches(regex)) {
//			warning += "Invalid Email" + newline;
//		}
//		List<User> users = userRepository.findAll();
//		for (User u : users) {
//			if (user.getEmail().equals(u.getEmail())) {
//				warning += "Email " + user.getEmail() + " already exists" + newline;
//			}
//		}
//	}
//
//	private void validateUsername(User user) {
//		String regex = "[A-Za-z0-9]{3,32}";
//		if (!user.getUsername().matches(regex)) {
//			warning += "Invalid username, please use only letters and numbers" + newline;
//		}
//		List<User> users = userRepository.findAll();
//		for (User u : users) {
//			if (user.getUsername().equals(u.getUsername())) {
//				warning += "User Name " + user.getUsername() + " already exists" + newline;
//			}
//		}
//	}
//
//	private void validateLastname(User user) {
//		String regex = "[A-Za-z-']{1,20}";
//		if (!user.getLastName().matches(regex)) {
//			warning += "Invalid last name, please use Letters, dashes, and apostrophes." + newline;
//		}
//	}
//
//	private void validateFirstname(User user) {
//		String regex = "[A-Za-z-']{1,20}";
//		if (!user.getFirstName().matches(regex)) {
//			warning += "Invalid first name, please use Letters, dashes, and apostrophes." + newline;
//		}
//	}
//
//	private UserAccount createUserAccount(HttpServletRequest request, HttpServletResponse response) {
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String email = request.getParameter("email");
//		Authority authority = authorityDAO.findByName(Authorities.EMPLOYEE.getName()).get();
//		UserAccount userAccount = new UserAccount(firstName, lastName, email, "", Timestamp.from(Instant.now()),true, false, 0L, authority);
//		return userAccount;
//	}
//
//	private void setAuthorities(User user) {
//		Authority authority = authorityRepository.findByName("USER");
//		authority.getUsers().add(user);
//		user.getAuthorities().add(authority);
//		userRepository.save(user);
//		authorityRepository.save(authority);
//	}

}

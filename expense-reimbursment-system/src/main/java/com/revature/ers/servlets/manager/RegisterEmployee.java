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
//@WebServlet("/manager/register-employee")
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


}

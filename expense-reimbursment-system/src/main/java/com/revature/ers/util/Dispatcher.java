package com.revature.ers.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.handlers.LoginHandler;
import com.revature.ers.handlers.manager.ManagerHandler;
import com.revature.ers.handlers.manager.ManagerHome;
import com.revature.ers.handlers.manager.RegisterEmployee;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.servlets.LoginServlet;

public class Dispatcher {
	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);
	private LoginHandler loginHandler;
	private ManagerHandler managerHandler;

	public Dispatcher() {
		loginHandler = new LoginHandler();
	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response) {
		SecurityHandler securityHandler = new SecurityHandler();
		String credentials = response.getHeader("Authorization");
		boolean isAuthenticated = securityHandler.isAuthenticated(credentials);
		boolean isAuthorizedManager = securityHandler.isAuthorized(credentials, "MANAGER");
		boolean isAuthorizedEmployee = securityHandler.isAuthorized(credentials, "EMPLOYEE");
		LOGGER.info(" isAuthenticated: " + isAuthenticated + " isAuthorizedManager: " + isAuthorizedManager
				+ " credentials" + credentials);
		String path = request.getRequestURI().substring(request.getContextPath().length());
		LOGGER.error("Test");
		LOGGER.error(response.getHeader("Authorization"));
		LOGGER.error(path);
		if (path.startsWith("/api")) {

		} else {
			try {
				if (path.isEmpty() || "/".equals(path)) {
					response.sendRedirect(request.getContextPath() + "/login");
				} else
					if (path.startsWith("/login")) {
					loginHandler.process(request, response);
				} else if (path.startsWith("/manager") && isAuthenticated && isAuthorizedManager) {
					switch (path) {
					case "/manager":
						managerHandler = new ManagerHome();
						managerHandler.process(request, response);
						break;
					case "/manager/employee":
						break;
					case "/manager/register-employee":
						managerHandler = new RegisterEmployee();
						managerHandler.process(request, response);
						break;
					case "/manager/all-employees":
						break;
					}
				} else if (path.startsWith("/employee") && isAuthenticated && isAuthorizedEmployee) {
					switch (path) {
					case "/employee":
						break;
					case "/employee/profile":
						break;
					case "/employee/reimbursement":
						break;
					}
				} else {
					response.setHeader("Authorization", "");
					response.sendRedirect(request.getContextPath() + "/login");
				}

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}
}

package com.revature.ers.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.security.SecurityHandler;

public class Dispatcher {
	static final Logger LOGGER = Logger.getLogger(Dispatcher.class);

	public void dispatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.isEmpty() || "/".equals(path)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
			requestDispatcher.forward(request, response);
		} else if (path.equals("/login")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
			requestDispatcher.forward(request, response);
		}
		if (path.startsWith("/api")) {
			RequestDispatcher requestDispatcher;
			if (path.equals("/api/resources")) {
				requestDispatcher = request.getRequestDispatcher("/resources");
				requestDispatcher.forward(request, response);
			} else {
				SecurityHandler securityHandler = new SecurityHandler();
				String jwt = request.getHeader("Authorization");
				if (securityHandler.isAuthenticatedJWT(jwt, request)) {
					switch (path) {
					case "/api/user-accounts":
						requestDispatcher = request.getRequestDispatcher("/api/user-accounts");
						requestDispatcher.forward(request, response);
						break;
					case "/api/authorities":
						requestDispatcher = request.getRequestDispatcher("/api/authorities");
						requestDispatcher.forward(request, response);
						break;
					case "/api/reimbursements":
						requestDispatcher = request.getRequestDispatcher("/api/reimbursements");
						requestDispatcher.forward(request, response);
						break;
					}
				} else {
					response.sendError(400, "Authentication required");
				}
			}

		}

	}
}

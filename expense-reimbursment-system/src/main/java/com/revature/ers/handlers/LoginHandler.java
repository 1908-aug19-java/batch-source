package com.revature.ers.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.services.UserAccountService;
import com.revature.ers.services.UserAccountServiceImpl;
import com.revature.ers.servlets.LoginServlet;
import com.revature.ers.util.Authorities;
import com.revature.ers.util.HTMLReader;
import com.revature.ers.util.ResourceUrls;

public class LoginHandler {

	UserAccountService userAccountService = new UserAccountServiceImpl();
	UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.error("Login View");
		String method = request.getMethod();
		switch (method) {
		case "GET":
			processGET(request, response);
			break;
		case "POST":
			proccessPOST(request, response);
			break;
		}
	}

	private String getHTML(HttpServletRequest request) {
		HTMLReader htmlReader = new HTMLReader(request, ResourceUrls.getHTMLURLS().get("application"));
		String[] headElements = { "<title>Login</title>" };
		String[] htmlBody = { htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("login")) };
		String[] scriptElements = {
				"<script type='text/javascript' src='" + ResourceUrls.getJSURLS().get("login") + "'></script>" };

		htmlReader.setHeadElements(headElements).setBodyElement(htmlBody).setScriptElements(scriptElements);
		return htmlReader.getHtml().toString();
	}

	private void processGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write(getHTML(request));
		out.flush();
	}

	private void proccessPOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LOGGER.info("kindof");
		if (userAccountService.areValidCredentials(email, password)) {
			HttpSession session = request.getSession(true);
			UserAccount userAccount = userAccountDAO.findByEmail(email).get();
			session.setAttribute("userAccount", userAccount);
			String authorityName = userAccount.getAuthority().getName();

			SecurityHandler securityHandler = new SecurityHandler();
			String encoded = securityHandler.encodeData(email + ":" + password + ":" + authorityName);
			response.setHeader("Authorization", encoded);
			LOGGER.info("in " + encoded);
			HttpServletRequestWrapper hWrapper = new HttpServletRequestWrapper(request) {
				@Override
				public String getMethod() {
					return "GET";
				}
			};
			if (authorityName.equals(Authorities.EMPLOYEE.getName())) {
				// HttpServletRequestWrapper(request);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/employee");
				requestDispatcher.forward(hWrapper, response);
//				response.sendRedirect(request.getContextPath() + "/employee");
			} else if (authorityName.equals(Authorities.MANAGER.getName())) {
				LOGGER.error("my");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/manager");
				requestDispatcher.forward(hWrapper, response);
//				LOGGER.info("in m" + response.getHeader("Authorization"));
//				LOGGER.info("in m2" + response.getHeader("Authorization"));
//				LOGGER.info("in m3" + request.getHeader("Authorization"));
//				response.sendRedirect(request.getContextPath() + "/manager");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.write(getHTML(request));
			out.flush();
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
//			requestDispatcher.forward(request, response);
		}
	}
}

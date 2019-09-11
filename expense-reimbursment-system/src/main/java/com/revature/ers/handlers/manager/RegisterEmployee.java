package com.revature.ers.handlers.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.dao.AuthorityDAO;
import com.revature.ers.dao.AuthorityDAOImpl;
import com.revature.ers.models.Authority;
import com.revature.ers.models.UserAccount;
import com.revature.ers.services.UserAccountService;
import com.revature.ers.services.UserAccountServiceImpl;
import com.revature.ers.util.Authorities;
import com.revature.ers.util.HTMLReader;
import com.revature.ers.util.ResourceUrls;

public class RegisterEmployee implements ManagerHandler {

	private static final Logger LOGGER = Logger.getLogger(RegisterEmployee.class);
	private AuthorityDAO authorityDAO = new AuthorityDAOImpl();
	private UserAccountService userAccountService = new UserAccountServiceImpl();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		switch (method) {
		case "GET":
			processGET(request, response);
			break;
		case "POST":
			processPOST(request, response);
			break;
		}
	}
	
	public void processGET(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.write(getHTML(request));
		out.flush();
	}

	public void processPOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserAccount userAccount = createUserAccount(request, response);
		boolean[] validation = { userAccountService.isValidName(userAccount.getFirstName()),
				userAccountService.isValidName(userAccount.getLastName()),
				userAccountService.isValidEmail(userAccount.getEmail()),
				userAccountService.emailExists(userAccount.getEmail()),
				userAccountService.isValidPassword(userAccount.getPassword()), userAccountService
						.isSamePassword(userAccount.getPassword(), request.getParameter("confirm_password")) };
		boolean isValid = true;
		for (boolean b : validation) {
			isValid = b && isValid;
		}

		if (isValid) {
			LOGGER.error("valid");
		} else {
			LOGGER.error("Invalid");
		}
	}

	private String getHTML(HttpServletRequest request) {
		HTMLReader htmlReader = new HTMLReader(request, ResourceUrls.getHTMLURLS().get("application"));
		String htmlHeader = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("managerHeader"));
		String htmlMainBody = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("registerEmployee"));
		String htmlFooter = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("applicationFooter"));
		String [] headElements = {"<title>Register Employee</title>"};
		String [] body = {htmlHeader, htmlMainBody, htmlFooter};
		String [] scriptElements = {"<script type='text/javascript' src='"+ResourceUrls.getJSURLS().get("registerEmployee")+"'></script>"};
		
        htmlReader.setHeadElements(headElements).setBodyElement(body).setScriptElements(scriptElements);
		return htmlReader.getHtml().toString();
	}
	
	private UserAccount createUserAccount(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Authority authority = authorityDAO.findByName(Authorities.EMPLOYEE.getName()).get();
		UserAccount userAccount = new UserAccount(firstName, lastName, email, "", Timestamp.from(Instant.now()), true,
				false, 0L, authority);
		return userAccount;
	}
}

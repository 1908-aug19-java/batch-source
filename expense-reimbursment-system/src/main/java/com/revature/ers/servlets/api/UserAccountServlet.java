package com.revature.ers.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.services.UserAccountService;
import com.revature.ers.services.UserAccountServiceImpl;
import com.revature.ers.util.FilterPair;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/api/user-accounts")
public class UserAccountServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UserAccountServlet.class);
	private static final long serialVersionUID = 1L;
	private static final UserAccountService USER_ACCOUNT_SERVICE = new UserAccountServiceImpl();
	private static final UserAccountDAO U_ACCOUNT_DAO = new UserAccountDAOimpl();
	private static final Gson GSON = new Gson();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("UserAccountServlet: running doGet");
		String email = request.getParameter("email");
		FilterPair emailPair = new FilterPair("email", email);
		try {
			if (emailPair.getValue() != null && emailPair.getValue().equalsIgnoreCase("ALL")) {
				List<String> emails = U_ACCOUNT_DAO.findUserAccountEmails(new FilterPair[] {});
				String userAccountsJson = GSON.toJson(emails);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(userAccountsJson);
				out.flush();
			} else {
				FilterPair firstNamePair = new FilterPair("first_name", request.getParameter("first_name"));
				FilterPair lastNamePair = new FilterPair("last_name", request.getParameter("last_name"));
				FilterPair[] pairs = { emailPair, firstNamePair, lastNamePair };
				pairs = Arrays.stream(pairs).filter(p -> p.getValue() != null && !"".equals(p.getValue()))
						.toArray(FilterPair[]::new);

				List<UserAccount> userAccounts = U_ACCOUNT_DAO.findAllByParams(pairs);
				String userAccountsJson = GSON.toJson(userAccounts);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(userAccountsJson);
				out.flush();
			}
		} catch (IOException | IllegalStateException e) {
			LOGGER.error(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("UserAccountServlet: running doPost");
		SecurityHandler securityHandler = new SecurityHandler();
		String jwt = request.getHeader("Authorization");
		try {
			if (securityHandler.isAuthorizedJWT(jwt, "MANAGER")) {
				UserAccount userAccount = USER_ACCOUNT_SERVICE.createUserAccount(request);
				boolean isValid = USER_ACCOUNT_SERVICE.validate(userAccount, request.getParameter("confirm_password"));
				if (isValid) {
					userAccount.setPassword(new SecurityHandler().hash(userAccount.getPassword()));
					U_ACCOUNT_DAO.save(userAccount);
				} else {
					response.sendError(400, "Invalid fields");
				}
				response.sendError(500,
						"Something went wrong on the server side. Please try your request at a later time");

			} else {
				response.sendError(400, "You are not authorized to access this resource");
			}
		} catch (ExpiredJwtException e) {
			LOGGER.info("Session expired");
			response.setHeader("Authorization", null);
			response.sendError(402, "Your session is expired");
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

}

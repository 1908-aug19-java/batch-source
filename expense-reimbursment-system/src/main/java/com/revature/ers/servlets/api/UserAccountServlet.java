package com.revature.ers.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.ers.dao.AuthorityDAO;
import com.revature.ers.dao.AuthorityDAOImpl;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.services.UserAccountService;
import com.revature.ers.services.UserAccountServiceImpl;
import com.revature.ers.util.AuthorityEnum;
import com.revature.ers.util.FileManager;
import com.revature.ers.util.FilterPair;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/api/user-accounts")
@MultipartConfig
public class UserAccountServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UserAccountServlet.class);
	private static final long serialVersionUID = 1L;
	private static final UserAccountService USER_ACCOUNT_SERVICE = new UserAccountServiceImpl();
	private static final UserAccountDAO U_ACCOUNT_DAO = new UserAccountDAOimpl();
	private static final AuthorityDAO AUTHORITY_DAO = new AuthorityDAOImpl();
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
		SecurityHandler securityHandler = new SecurityHandler();
		String jwt = request.getHeader("Authorization");
		try {
			if (securityHandler.isAuthorizedJWT(jwt, AuthorityEnum.EMPLOYEE.getName())
					|| securityHandler.isAuthorizedJWT(jwt, AuthorityEnum.MANAGER.getName())) {
				String email = request.getParameter("email");
				FilterPair emailPair = new FilterPair("email", email);

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
					String authorityName = request.getParameter("authority");
					FilterPair orderByPair = new FilterPair("ORDER BY", request.getParameter("ORDERBY"));
					FilterPair limitPair = new FilterPair("LIMIT", request.getParameter("LIMIT"));
					FilterPair offsetPair = new FilterPair("OFFSET", request.getParameter("OFFSET"));
					FilterPair authority_id = new FilterPair("empty", null);
					if(authorityName != null) {
						 authority_id = new FilterPair("authority_id", AUTHORITY_DAO.findByName(authorityName).get().getId().toString());
					}
					FilterPair[] pairs = {authority_id, emailPair, firstNamePair, lastNamePair, orderByPair, limitPair, offsetPair };
					pairs = Arrays.stream(pairs).filter(p -> p.getValue() != null && !"".equals(p.getValue()))
							.toArray(FilterPair[]::new);
					LOGGER.info(Arrays.toString(pairs));
					List<UserAccount> userAccounts = U_ACCOUNT_DAO.findAllByParams(pairs);
					userAccounts.forEach(ua -> {
						ua.setPassword(null);
						ua.setActive(null);
						ua.setBlocked(null);
						ua.setLastLogin(null);
						ua.setFailedLogins(null);
						String imageUri = ua.getImageUrl();
						if (imageUri != null) {
							ua.setImageUrl(imageUri.substring(imageUri.indexOf(FileManager.staticPath)));
						}
					});
					LOGGER.info((userAccounts));
					String userAccountsJson = GSON.toJson(userAccounts);
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(userAccountsJson);
					out.flush();
				}
			} else {
				response.sendError(400);
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
					response.sendError(466);
				}
			} else {
				response.sendError(400);
			}
		} catch (ExpiredJwtException e) {
			LOGGER.info("Session expired");
			response.setHeader("Authorization", null);
			response.sendError(402, "Your session is expired");
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		SecurityHandler securityHandler = new SecurityHandler();
		String jwt = request.getHeader("Authorization");
		try {
			if (securityHandler.isAuthorizedJWT(jwt, "MANAGER")) {
				UserAccount userAccount = USER_ACCOUNT_SERVICE.createUserAccount(request);
				int statusCode = USER_ACCOUNT_SERVICE.validationCodes(request, userAccount);
				if (statusCode != 200) {
					response.sendError(statusCode);
				} else {
					Part filePart = request.getPart("file");
					String imageUrl = null;
					if (filePart != null) {
						String newFileName = userAccount.getEmail() + "_ProfilePicture.jpg";
						imageUrl = FileManager.fileSystemStorageSimulation + '\\' + newFileName;
						new FileManager().saveImageFile(request, filePart, imageUrl);
					}
					UserAccount dbUserAccount = U_ACCOUNT_DAO.findByEmail(request.getParameter("email")).get();
					dbUserAccount.setFields(userAccount);
					if (imageUrl != null) {
						dbUserAccount.setImageUrl(imageUrl);
						U_ACCOUNT_DAO.update(dbUserAccount);
						PrintWriter out = response.getWriter();
						out.print(imageUrl);
						out.flush();
					} else {
						U_ACCOUNT_DAO.update(dbUserAccount);
					}
				}
			} else {
				response.sendError(400);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}

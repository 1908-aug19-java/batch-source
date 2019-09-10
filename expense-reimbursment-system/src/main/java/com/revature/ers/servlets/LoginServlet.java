package com.revature.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.services.UserAccountService;
import com.revature.ers.services.UserAccountServiceImpl;
import com.revature.ers.util.Authorities;
import com.revature.ers.util.HTMLReader;
import com.revature.ers.util.ResourceUrls;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserAccountService userAccountService = new UserAccountServiceImpl();
	UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HTMLReader htmlReader = new HTMLReader(request, ResourceUrls.getHTMLURLS().get("application"));
		String [] headElements = {"<title>Login</title>"};
		StringBuffer html = htmlReader.setHeadElements(new StringBuffer(htmlReader.getApplicationHTML()), headElements);
		String htmlBody = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("login"));
		htmlReader.setBodyElement(html, htmlBody);
		String []scriptElements = {"<script type='text/javascript' src='"+ResourceUrls.getJSURLS().get("login")+"'></script>"};
		htmlReader.setScriptElements(html, scriptElements);
		out.write(html.toString());
		out.flush();
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
//			requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (userAccountService.areValidCredentials(email, password)) {
			HttpSession session = request.getSession(true);
			UserAccount userAccount = userAccountDAO.findByEmail(email).get();
			session.setAttribute("userAccount", userAccount);
			String authorityName = userAccount.getAuthority().getName();
			if(authorityName.equals(Authorities.EMPLOYEE.getName())) {
				response.sendRedirect(request.getContextPath() + "/employee");
			}else if(authorityName.equals(Authorities.MANAGER.getName())) {
				response.sendRedirect(request.getContextPath() + "/manager");
			}
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
			requestDispatcher.forward(request, response);
		}

	}

}

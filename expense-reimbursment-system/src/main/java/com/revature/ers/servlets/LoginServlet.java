package com.revature.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		if(true) {
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    
//		    String filePath = "/html/login.html";
//		    
//		    filePath = getServletContext().getRealPath(filePath);
//		    System.out.println(filePath);
//		    String content = "";
//		    try
//	        {
//	            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
//	        }
//	        catch (IOException e)
//	        {
//	            e.printStackTrace();
//	        }
		    //out.println(HTMLReader.getHtmlreader().);
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
			requestDispatcher.forward(request, response);
		}
		
	}
	
	private String getServerSideValidationHTML() {
		String html = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>title</title>\r\n" + 
				"<link rel=\"shortcut icon\" type=\"image/png\"\r\n" + 
				"	href=\"/ers/images/favicon.ico\" />\r\n" + 
				"<link rel=\"stylesheet\" href=\"/ers/css/login.css\">\r\n" + 
				"<link rel=\"stylesheet\" href=\"/ers/bootstrap/css/bootstrap.min.css\" />\r\n" + 
				"<link rel=\"stylesheet\" href=\"/ers/fontawesome/css/all.min.css\">\r\n" + 
				"<link rel=\"shortcut icon\" type=\"image/png\"\r\n" + 
				"	href=\"/ers/images/favicon.ico\" />\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	Hi<div class=\"container\">\r\n" + 
				"		<div class=\"card card-login mx-auto text-center bg-dark\"\r\n" + 
				"			style=\"margin-top: 1%;\">\r\n" + 
				"			<div class=\"card-header mx-auto bg-light\" style=\"margin-top: 3%;\">\r\n" + 
				"				<span> <img src=\"/ers/images/rev3.png\" class=\"w-75\"\r\n" + 
				"					alt=\"Logo\" /></span>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"card-body\">\r\n" + 
				"				<form action=\"/ers/login\" method=\"post\" class=\"col-sm-6 offset-sm-3\">\r\n" + 
				"					<div class=\"input-group form-group\">\r\n" + 
				"						<div class=\"input-group-prepend\">\r\n" + 
				"							<span class=\"input-group-text\"><i class=\"fas fa-user\"></i></span>\r\n" + 
				"						</div>\r\n" + 
				"						<input type=\"text\" id=\"email\" name=\"email\" class=\"form-control\"\r\n" + 
				"							placeholder=\" Username\">\r\n" + 
				"					</div>\r\n" + 
				"					\r\n" + 
				"					<div class=\"input-group form-group\">\r\n" + 
				"						<div class=\"input-group-prepend\">\r\n" + 
				"							<span class=\"input-group-text\"><i class=\"fas fa-user\"></i></span>\r\n" + 
				"						</div>\r\n" + 
				"						<input type=\"text\" id=\"test\" name=\"test\" class=\"form-control\"\r\n" + 
				"							placeholder=\" test\">\r\n" + 
				"					</div>\r\n" + 
				"\r\n" + 
				"					<div class=\"input-group form-group\">\r\n" + 
				"						<div class=\"input-group-prepend\">\r\n" + 
				"							<span class=\"input-group-text\"><i class=\"fas fa-key\"></i></span>\r\n" + 
				"						</div>\r\n" + 
				"						<input type=\"password\" id=\"password\" name=\"password\"\r\n" + 
				"							class=\"form-control\" placeholder=\"Password\">\r\n" + 
				"					</div>\r\n" + 
				"\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<input type=\"submit\" id=\"submit-login\" value=\"Login\"\r\n" + 
				"							class=\"btn btn-outline-danger float-right login_btn\">\r\n" + 
				"					</div>\r\n" + 
				"				</form>\r\n" + 
				"\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<script type=\"text/javascript\" src=\"/ers/js/login.js\"></script>\r\n" + 
				"	<script type=\"text/javascript\" src=\"/ers/js/util.js\"></script>\r\n" + 
				"	<script src=\"/ers/webjars/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"	<script src=\"/ers/webjars/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return html;
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

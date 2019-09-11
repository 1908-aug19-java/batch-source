package com.revature.ers.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.ers.models.UserAccount;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.servlets.LoginServlet;
import com.revature.ers.util.Authorities;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/login", "/html/login.html"})
public class LoginFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);
	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SecurityHandler securityHandler = new SecurityHandler();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String credentials = httpRequest.getHeader("Authorization");
		boolean isAuthenticated = securityHandler.isAuthenticated(credentials);
		boolean isAuthorizedManager = securityHandler.isAuthorized(credentials, "MANAGER");
		boolean isAuthorizedEmployee = securityHandler.isAuthorized(credentials, "EMPLOYEE");
		LOGGER.info(" isAuthenticated: " + isAuthenticated + " isAuthorizedManager: " + isAuthorizedManager+ " credentials" + credentials);
//		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpSession session = httpRequest.getSession(false);
//
//		boolean isLoggedIn = (session != null && session.getAttribute("userAccount") != null);
		String loginURI = httpRequest.getContextPath() + "/login";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.html");
		if (isAuthenticated && (isLoginRequest || isLoginPage)) {
			//if logged in already requesting login page then send to home page
//			UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
//			String authorityName = userAccount.getAuthority().getName();
			if (isAuthorizedEmployee) {
				((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() +"/employee");
			} else if (isAuthorizedManager) {
				((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/manager");
			}
		} else {
			LOGGER.info("not looping");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	static ServletResponse setCachingHeaders(HttpServletResponse response) {
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.addHeader("Cache-Control", "pre-check=0, post-check=0");
		response.setDateHeader("Expires", 0);
		return (ServletResponse)response;
	}
}
package com.revature.ers.filters;

import java.io.IOException;
import java.util.Enumeration;

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
 * Servlet Filter implementation class ManagerFilter
 */
//@WebFilter({ "/manager/*", "/html/manager/*", "/js/manager/*", "/css/manager/*" })
public class ManagerFilter implements Filter {
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	/**
	 * Default constructor.
	 */
	public ManagerFilter() {
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
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Enumeration<String> enumeration = httpRequest.getHeaderNames();
		while(enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
		
		
		String credentials = httpRequest.getHeader("Authorization");
		boolean isAuthenticated = securityHandler.isAuthenticated(credentials);
		boolean isAuthorized = securityHandler.isAuthorized(credentials, "MANAGER");
		String loginURI = httpRequest.getContextPath() + "/login";
		LOGGER.info("filter" + credentials);
		String s = httpResponse.getHeader("Authorization");
		LOGGER.error(s + " t");
		System.out.println(isAuthenticated + " " + isAuthorized);
		//response = LoginFilter.setCachingHeaders(((HttpServletResponse) response));

		if (isAuthenticated && isAuthorized) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(loginURI);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

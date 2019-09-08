package com.revature.ers.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ers.dao.AuthorityDAO;
import com.revature.ers.dao.AuthorityDAOImpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.util.Authorities;

/**
 * Servlet Filter implementation class EmployeeFilter
 */
@WebFilter({ "/employee/*", "/html/employee/*", "/js/employee/*", "/css/employee/*" })
public class EmployeeFilter implements Filter {
	private AuthorityDAO authorityDAO = new AuthorityDAOImpl();

	/**
	 * Default constructor.
	 */
	public EmployeeFilter() {
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("userAccount") != null);
		String loginURI = httpRequest.getContextPath() + "/login";
		response = LoginFilter.setCachingHeaders(((HttpServletResponse) response));
		if (isLoggedIn) {
			// If they are logged, if they are an employee than process request, otherwise
			// redirect to /login filter for further handling
			UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
			String authorityName = authorityDAO.findById(userAccount.getAuthorityId()).get().getName();
			if (authorityName.equals(Authorities.EMPLOYEE.getName())) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendRedirect(loginURI);
			}

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

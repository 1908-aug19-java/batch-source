package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;

public class LoginServlet extends HttpServlet {

		public static Logger LOG = Logger.getLogger(LoginServlet.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		request.getRequestDispatcher("Views/login.html").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		System.out.println(username);
		System.out.println(password);
		
		EmployeeDao e = new EmployeeDaoImpl();
		
		Integer employee_id = e.login(username, password);
		
//		System.out.println(employee_id);
		
		boolean isManager = e.checkIfManager(username);
		
		System.out.println(isManager);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("user_name", username);
		session.setAttribute("password", password);
		
		if(employee_id != 0 && isManager == false) {
			
			LOG.info("User logged in as employee");
			request.getRequestDispatcher("Views/employee.html").forward(request, response);
		}else if(employee_id != 0 && isManager == true) {
			request.getRequestDispatcher("Views/manager.html").forward(request, response);
		}else {
			LOG.info("User logged in as manager");

			response.sendRedirect("/Reimbursement/login");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

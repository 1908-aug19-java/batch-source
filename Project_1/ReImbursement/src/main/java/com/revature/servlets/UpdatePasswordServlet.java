package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;

public class UpdatePasswordServlet extends HttpServlet {

	EmployeeDao ed = new EmployeeDaoImpl();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UpdatePasswordServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("user_name");
		String password = (String) session.getAttribute("password");
		
		
		
		int emp_id = ed.login(username, password);
		
		String newPass = request.getParameter("newPass");
		
		int stat = ed.changeUserPassword(emp_id, newPass);
		
		response.sendRedirect("/Reimbursement/login");
		
		
		
	}
	
}

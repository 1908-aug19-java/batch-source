package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;

public class EmployeeViewInformation extends HttpServlet {

	EmployeeDao ed = new EmployeeDaoImpl();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeViewInformation() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("user_name");
		String password = (String) session.getAttribute("password");
		
		int employee_id = ed.login(username, password);
		
		boolean isManager = ed.checkIfManager(username);

		
		if(employee_id != 0 && isManager == false) {
			
			request.getRequestDispatcher("Views/information.html").forward(request, response);

		}else if (employee_id != 0 && isManager == true) {
			
			request.getRequestDispatcher("Views/managerEdit.html").forward(request, response);

		}
		
		
		//request.getRequestDispatcher("Views/information.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}

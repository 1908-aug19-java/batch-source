package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private EmployeeDaoImpl empDao=new EmployeeDaoImpl();   
	private static Logger log = Logger.getRootLogger();

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//log.info("login servlet GET REQUEST");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String m = request.getParameter("role");

		String isManager = m;
		

		//System.out.println("email: " + email + " | Password: " + pass );
		
		EmployeeDao empDao = new EmployeeDaoImpl();
		if((email!=""||pass!="" )&& empDao.getEmployeeByEmail(email).getPassword().equals(pass)) 
		{	
			Employee emp=null;
			emp = empDao.getEmployeeByEmail(email);
			log.info("emp details "+emp+ " isManager:"+isManager);				
			if(emp.getManagerId()==0  ) 
			{
				System.out.println("Verified Employee Login");
			    request.getRequestDispatcher("/Views/employeeHome.html").forward(request, response);
			}
			else {
				  log.info("Verified Manager Login");
				 // System.out.println("Verified Manager Login");
				  request.getRequestDispatcher("/Views/managerHome.html").forward(request, response);		
			}		
		} 
		
		else {
			 out.print("Unverified Login  To Register press Register ");
			//System.out.println(" Unverified Login  To Register press Register ");
			RequestDispatcher rd = request.getRequestDispatcher("/Views/Login.html");
			rd.include(request, response);
		}	
		out.close();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//System.out.println("post REQUEST");
		doGet(request, response);

	}
	
}

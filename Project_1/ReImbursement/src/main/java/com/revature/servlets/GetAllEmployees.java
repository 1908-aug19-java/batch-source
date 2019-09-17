package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

/**
 * Servlet implementation class GetAllEmployees
 */
//@WebServlet(name = "GetAllEmployeesServlet", urlPatterns = { "/GetAllEmployeesServlet" })
public class GetAllEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeDao em = new EmployeeDaoImpl();
		
		String username = null;
		String password = null;
		
		
		
		HttpSession session = request.getSession(false);
		
		
		if(session.getAttribute("user_name") == null && session.getAttribute("password") == null) {
			response.sendRedirect("/Reimbursement/login");
		}else {
						
			 username = (String) session.getAttribute("user_name");
			 password = (String) session.getAttribute("password");
			 
			 List<Employee> employees = em.getEmployees();
				System.out.println(employees);
				
				ObjectMapper om = new ObjectMapper();
				String employeesJSON = om.writeValueAsString(employees);
				System.out.println(employeesJSON);
				
				try(PrintWriter pw = response.getWriter()){
					
					pw.write(employeesJSON);
				}
			
		}
		
//		int emp_id = em.login(username, password);
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class GetEmployeeInfo extends HttpServlet {

	/**
	 * 
	 */
	
	EmployeeDao ed = new EmployeeDaoImpl();
	
	private static final long serialVersionUID = 1L;

	public GetEmployeeInfo() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("user_name");
		String password = (String) session.getAttribute("password");
		
		System.out.println(username);
		
		int emp_id = ed.login(username, password);
		
		Employee em = ed.getEmployeeById(emp_id);
		
		System.out.println(em);
		
		ObjectMapper om = new ObjectMapper();
		
		om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		
		String employeeJSON = om.writeValueAsString(em);
		
		System.out.println("--------JSON-------");
		System.out.println(employeeJSON);
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(employeeJSON);
		}
		
	}
}

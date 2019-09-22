package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;


public class GetEmployeesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static List<Employee> employees = new ArrayList<>();
	private EmployeeService empDaoSer = new EmployeeService();

  
    public GetEmployeesServlets() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("get employee servlet GET REQUEST");

		ObjectMapper om = new ObjectMapper();
		response.getOutputStream().write(om.writeValueAsBytes(empDaoSer.getAll(request, response)));

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("post employee servlet GET REQUEST");  	
		doGet(request, response);
	}

}

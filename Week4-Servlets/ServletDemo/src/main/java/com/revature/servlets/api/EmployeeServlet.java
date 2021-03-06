package com.revature.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmployeeService es = new EmployeeService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employees = es.getAll();
		
		ObjectMapper om = new ObjectMapper();
		String employeeJSON = om.writeValueAsString(employees);
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(employeeJSON);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("do post employee");
		String newName = request.getParameter("name");
		String newDeptId = request.getParameter("department");
		
		Employee e = new Employee();
		e.setName(newName);
		
		Department d = new Department();
		d.setId(Integer.parseInt(newDeptId));
		e.setDepartment(d);
		
		es.create(e);
//		response.setStatus(201);
		response.sendRedirect("directory");
		
	}

}

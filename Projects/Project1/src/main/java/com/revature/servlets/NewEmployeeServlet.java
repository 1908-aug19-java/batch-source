package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;


public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
   
    public NewEmployeeServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET create new employee REQUEST");
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		String n = request.getParameter("name");
		String e = request.getParameter("email");
		String pass = request.getParameter("password");
		
		System.out.println("name: " + n + " email: " + e+" pass "+pass );
		Employee  emp=new Employee();
	    emp.setEmail(e);
	    emp.setName(n);
	    emp.setPassword(pass);
	    
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		empDao.createEmployee(emp);
		response.setStatus(201);
	    request.getRequestDispatcher("/Views/managerHome.html").forward(request, response);


		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet post method");

		doGet(request, response);

  }
}
		

    


package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.models.Employee;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();

   
    public UpdateServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET update new employee REQUEST");
		String n = request.getParameter("name");
		String e = request.getParameter("email");
		String pass = request.getParameter("password");

		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee  emp=new Employee();
		emp = empDao.getEmployeeByEmail(e);
		log.info(emp);

	    emp.setName(n);
	    emp.setPassword(pass);
		
		empDao.updateEmployee(emp,e);
		
        log.info(emp);
	    request.getRequestDispatcher("/Views/Login.html").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursment;


public class GetEmployeeByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();

    public GetEmployeeByIdServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Views/showInfoById.html").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("GetRequestByIdServlet DOPOST");
		String s = request.getParameter("id");
		int id = Integer.valueOf(s);
		
		log.info("emp id"+id);	

		EmployeeDaoImpl empDao= new EmployeeDaoImpl();
		Employee emp=null;
	
		emp=empDao.getEmployeeById(id);
		log.info("employee info are "+emp);
		
		ObjectMapper om = new ObjectMapper();
		response.setHeader("Content-Type", "json");
		response.getOutputStream().write(om.writeValueAsBytes(emp));
	
		
		//doGet(request, response);
	}

}

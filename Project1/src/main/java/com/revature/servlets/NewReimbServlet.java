package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.models.Reimbursment;

/**
 * Servlet implementation class NewReimbServlet
 */
public class NewReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public NewReimbServlet() {
        super();
    System.out.println(" Servlet class REQUEST");

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	    System.out.println("Create Reimb servlet GET REQUEST");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String d = request.getParameter("description");
			double amt = Double.parseDouble(request.getParameter("amount"));
			int id = Integer.parseInt( request.getParameter("id"));

           
			System.out.println("reason: " + d + " | amout: " + amt+"id "+id );
			Reimbursment r=new Reimbursment();
			r.setAmount(amt);
			r.setEmplId(id);
			r.setReason(d);
			
			ReimbursmentDaoImpl resDao = new ReimbursmentDaoImpl();
			resDao.createReimbusment(r);
			response.setStatus(201);
		
			if (r.getManagerId()==0)//employee
			{
		    request.getRequestDispatcher("/Views/employeeHome.html").forward(request, response);
			}
			else { //manager
			    request.getRequestDispatcher("/Views/managerHome.html").forward(request, response);

			}
      
		    out.close();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet Submit Reimb post method");

		doGet(request, response);
	}

}

package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.daos.ReimbursmentsDao;
import com.revature.services.ReeimbursmentServices;


public class ViewReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReeimbursmentServices resDaoSer = new ReeimbursmentServices ();
   
    public ViewReimbServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		response.getOutputStream().write(om.writeValueAsBytes(resDaoSer.getReimbursements(request, response)));

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

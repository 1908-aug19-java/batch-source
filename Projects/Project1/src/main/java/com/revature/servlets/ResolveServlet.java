package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.daos.ReimbursmentDaoImpl;


public class ResolveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();

  
    public ResolveServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqNum =Integer.parseInt( request.getParameter("request-num"));
		String discion = request.getParameter("status");
		int manager_id = Integer.parseInt( request.getParameter("manager-id"));

		ReimbursmentDaoImpl res = new ReimbursmentDaoImpl();
		res.UpdateReimbursment(discion, reqNum,manager_id);
		request.getRequestDispatcher("Views/managerHome.html").forward(request, response);	

         log.info("Request # "+reqNum +" "+discion+" by Mnager Id # "+ manager_id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

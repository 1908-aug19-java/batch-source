package com.revature.projectone.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

public class FrontController extends DefaultServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getRootLogger();
	
	private RequestHelper rq = new RequestHelper();
	
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.error(request.getMethod()+" to "+request.getRequestURI());
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(request.getServletPath());
		if(path.startsWith("/static/")) {
			log.info("It's static.");
			super.doGet(request, response);	
		} else {
			//process request with a request helper 
			rq.processGet(request, response);
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println(request.getMethod()+" to "+request.getRequestURI());
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(request.getServletPath());
		if(path.startsWith("/static/")) {
			System.out.println("It's static.");
			super.doPost(request, response);	
		} else {
			
			rq.processPost(request, response);
		}
	}

}

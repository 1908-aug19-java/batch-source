package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;
/**
 * Servlet implementation class FrontController
 */
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = new RequestHelper();
	private static Logger log = Logger.getRootLogger();
       
    /**
     * @see DefaultServlet#DefaultServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		log.info("Front Controller doGet path: " + path);
		if(path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			requestHelper.processGet(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		log.info("Front Controller doPost path: " + path);
		requestHelper.processPost(request, response);
	}

}

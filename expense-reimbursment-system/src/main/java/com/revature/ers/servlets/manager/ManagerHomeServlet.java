package com.revature.ers.servlets.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.servlets.LoginServlet;
import com.revature.ers.util.HTMLReader;
import com.revature.ers.util.ResourceUrls;

/**
 * Servlet implementation class ManagerHomeServlet
 */
@WebServlet("/manager")
public class ManagerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ManagerHomeServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write(getHTML(request));
		out.flush();
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/manager/managerHome.html");
//		requestDispatcher.forward(request, response);
	}

	private String getHTML(HttpServletRequest request) {
		HTMLReader htmlReader = new HTMLReader(request, ResourceUrls.getHTMLURLS().get("application"));
		String htmlHeader = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("managerHeader"));
		String htmlMainBody = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("managerHome"));
		String htmlFooter = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("applicationFooter"));
		String [] headElements = {"<title>Manager Home</title>"};
		String [] body = {htmlHeader, htmlMainBody, htmlFooter};
		String [] scriptElements = {"<script type='text/javascript' src='/ers/js/manager/managerHome.js'></script>"};
		
        htmlReader.setHeadElements(headElements).setBodyElement(body).setScriptElements(scriptElements);
		return htmlReader.getHtml().toString();
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
//		response.setHeader("Pragma", "no-cache");
		LOGGER.error("no");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/manager/managerHome.html");
		requestDispatcher.forward(request, response);
	}

}

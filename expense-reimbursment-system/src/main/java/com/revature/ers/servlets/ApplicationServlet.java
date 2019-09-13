package com.revature.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.util.FileManager;
import com.revature.ers.util.ResourceUrls;

/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class ApplicationServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ApplicationServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.write(new FileManager().getFileContent(request, ResourceUrls.getHTMLURLS().get("application")));
		out.flush();
	}
}

package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.CalculatorService;

public class CalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CalculatorService cs = new CalculatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doGet handler method invoked in calculator servlet");
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter pw = response.getWriter();
//		pw.write("<h2>My Calculator Page</h2>");
//		pw.close();
		RequestDispatcher rd = request.getRequestDispatcher("Calculator.html");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("Post request made to calculator with the following query: "+request.getReader().readLine());
		
		String operation = request.getParameter("operation");
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");
		System.out.println(operation+" on "+ n1 + " and "+n2);
		
		
//		PrintWriter pw = null; 
		
		try {
			int num1 = Integer.parseInt(n1);
			int num2 = Integer.parseInt(n2);
			
			int result = cs.calculate(num1, num2, operation);
			
			request.setAttribute("result", result);
			
			request.getRequestDispatcher("answer").forward(request, response);;
			
//			pw = response.getWriter();
//			pw.write("<p> The answer is "+result+"</p>");
//			pw.close();
			
		} catch (NumberFormatException e) {
			response.sendError(400);
		} 
		
		
	}

}

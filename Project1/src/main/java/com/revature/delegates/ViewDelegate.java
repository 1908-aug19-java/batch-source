package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ViewDelegate {
	private static Logger log = Logger.getRootLogger();

	public void returnView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		log.info("ViewDelegate returnView path: " + request.getServletPath()); 
		switch(path) {
		case "/Login":
			request.getRequestDispatcher("/static/Views/Login.html").forward(request, response);
			break;
		case "/CreateAcc":
			request.getRequestDispatcher("/static/Views/CreateAcc.html").forward(request, response);
			break;
		case "/Reimbursement":
			request.getRequestDispatcher("/static/Views/Reimbursement.html").forward(request, response);
			break;
		case "/EmployeeHome":
			request.getRequestDispatcher("/static/Views/EmployeeHome.html").forward(request, response);
			break;
		case "/EmployeeInfo":
			request.getRequestDispatcher("/static/Views/EmployeeInfo.html").forward(request, response);
			break;
		case "/UpdateInfo":
			request.getRequestDispatcher("/static/Views/UpdateInfo.html").forward(request, response);
			break;
		case "/ManagerHome":
			request.getRequestDispatcher("/static/Views/ManagerHome.html").forward(request, response);
			break;
		default:
			response.sendError(404, "static resource not found");
		}
	}
}

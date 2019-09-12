package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.DepartmentDelegate;
import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private ViewDelegate vd = new ViewDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	private DepartmentDelegate dd = new DepartmentDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getServletPath();
		if(uri.startsWith("/api/")) {
			// we want to handle this as if it's requesting (a) record(s)
			String record = uri.substring(5);
			switch(record) {
			case "employees":
				// process with EmployeeDelegate
				ed.getEmployees(request, response);
				break;
			case "departments":
				// process with DepartmentDelegate
				dd.getDepartments(request, response);
				break;
			default:
				response.sendError(404, "record not supported");
			}
		} else {
			// we want to handle this as if it's requesting a view
			vd.returnView(request, response);
		}
	}

}

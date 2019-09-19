package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.ManagerDelegate;
import com.revature.delegates.ReimbursementDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private ViewDelegate vd = new ViewDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	private ManagerDelegate md = new ManagerDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private static Logger log = Logger.getRootLogger();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getServletPath();
		log.info("Request Helper process get path: " + uri);
		
		if(uri.startsWith("/api/")) {
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				
				return;
			}
			// we want to handle this as if it's requesting (a) record(s)
			String record = uri.substring(5);
			switch(record) {
			case "employees":
				// process with EmployeeDelegate
				ed.getEmployees(request, response);
				break;
			case "Reimbursement":
				rd.viewReimbursement(request, response);
				break;
			case "manager":
				md.getManager(request, response);
				break;
			default:
				response.sendError(404, "record not supported");
			}
		} else {
			// we want to handle this as if it's requesting a view
			vd.returnView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getServletPath();
		log.info("Request Helper process post path: " + path);
		switch(path) {
		case "/Login":
			authDelegate.authenticate(request, response);
			break;
		case "/CreateAcc":
			ed.createAcc(request, response);
			break;
		case "/RequestReimbursement":
			rd.addReimbursement(request, response);
			break;
		case "/UpdateInfo":
			ed.updateInfo(request, response);
			break;
		case "/ReimbursementResult":
			rd.reimbursementResult(request, response);
			break;
		default:
			response.sendError(405);
		}
	}

}


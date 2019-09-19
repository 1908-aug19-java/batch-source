package com.revature.delegates;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ManagerDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.dao.impl.ManagerDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;

public class AuthDelegate {
	private static Logger log = Logger.getRootLogger();
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private EmployeeDao ed = new EmployeeDaoImpl();
	private ManagerDao md = new ManagerDaoImpl();
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		log.info("AuthDelegate check isAuthorized with authToken " + authToken);
		// check to see if there is an auth header
		if(authToken!=null) {
			String[] tokenArr = authToken.split("/");
			// if the token is formatted the way we expect, we can take the id from it and query for that user
			if(tokenArr.length == 3) {
				String username = tokenArr[0];
				String toke = tokenArr[1];
				// check to see if there is a valid user and if timestamp match
				if(ed.getEmployeeByUN(username) != null || md.getManagerByUN(username) != null) {
					String timetoke = timestamp.toString();
					if(timetoke.equals(toke)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("AuthDelegate check authenticate path: " + request.getServletPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		if(position.equals("1")){
			Employee e = new Employee();
			e = ed.getEmployeeByUN(username);
			if(e == null) {
				response.sendError(401);
			}
			else {
				if(e.getPassword().equals(password)) {
					String token = e.getUsername() + "/" + timestamp + "/"  + position;
					response.setStatus(200);
					response.setHeader("Authorization", token);
				}
				else {
					response.sendError(401);
					
				}
			}
		}
		else {
			Manager m = new Manager();
			m = md.getManagerByUN(username);
			log.info("in manager");
			if(m == null) {
				log.info("manager not found");
				response.sendError(401);
			}
			else {
				log.info("manager found");
				if(m.getPassword().equals(password)) {
					log.info("create token");
					String token = m.getUsername() + "/" + timestamp + "/"  + position;
					response.setStatus(200);
					response.setHeader("Authorization", token);
				}
				else {
					log.info("password not match");
					response.sendError(401);
			
				}
			}
		}
	}
}
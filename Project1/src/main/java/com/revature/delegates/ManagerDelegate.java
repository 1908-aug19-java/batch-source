package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ManagerDao;
import com.revature.dao.impl.ManagerDaoImpl;
import com.revature.model.Manager;


public class ManagerDelegate {
	private static Logger log = Logger.getRootLogger();

	private ManagerDao md = new ManagerDaoImpl();


	public void getManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("ManagerDelegate getManager path: " + request.getServletPath()); 
		String username = request.getParameter("username");
		if(username == null) {
			response.sendError(404, "No user with given username");
		} else {
			Manager m = md.getManagerByUN(username);
			if(m == null) {
				response.sendError(404, "No user with given username");
			} else {
				try(PrintWriter pw = response.getWriter()){
					pw.write(new ObjectMapper().writeValueAsString(m));
				}
			}
		}
	}

}

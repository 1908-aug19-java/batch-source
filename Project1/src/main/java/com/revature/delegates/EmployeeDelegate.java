package com.revature.delegates;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeDelegate {
	private EmployeeDao ed = new EmployeeDaoImpl();
	private static Logger log = Logger.getRootLogger();
	
	public void createAcc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("EmployeeDelegate createAcc path: " + request.getServletPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		long phones = Long.parseLong(phone);
		Employee e = ed.getEmployeeByUN(username);
		if(e == null) {
			Employee newEmployee = new Employee(username, password, firstname, lastname, phones, address);
			ed.newEmployee(newEmployee);
			response.setStatus(200);
		}
		else {
			response.sendError(401);
		}
		
	}
	
	public void getEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("EmployeeDelegate getEmployees path: " + request.getServletPath());
		String username = request.getParameter("username");
		String position = request.getParameter("position");
		List <Employee> elist = new ArrayList<Employee>();
		if(username == null) {
			response.sendError(404, "No user with given username");
		} 
		if (position.equals("Manager")) {
			elist = ed.getEmployees();
			try(PrintWriter pw = response.getWriter()){
				pw.write(new ObjectMapper().writeValueAsString(elist));
			}
		}
		else {
			Employee e = ed.getEmployeeByUN(username);
			elist.add(e);
			if(e == null) {
				response.sendError(404, "No user with given username");
			} else {
				try(PrintWriter pw = response.getWriter()){
					pw.write(new ObjectMapper().writeValueAsString(elist));
				}
			}
		}
	}

	public void updateInfo(HttpServletRequest request, HttpServletResponse response) {
		log.info("EmployeeDelegate updateInfo path: " + request.getServletPath());
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Employee e = ed.getEmployeeByUN(username);
		if(!firstname.equals("")) {
			log.info("set first name");
			e.setFirstName(firstname);
		}
		if(!lastname.equals("")) {
			log.info("set last name");
			e.setLastName(lastname);
		}
		if(!phone.equals("")) {
			log.info("set phone");

			long phones = Long.parseLong(phone);
			e.setPhone(phones);
		}
		if(!address.equals("")) {
			log.info("address");
			e.setAddress(address);
		}
		ed.updateInfo(e);
		response.setStatus(200);
	}
}

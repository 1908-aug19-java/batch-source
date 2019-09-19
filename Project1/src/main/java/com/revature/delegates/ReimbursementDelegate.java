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
import com.revature.dao.ManagerDao;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.dao.impl.ManagerDaoImpl;
import com.revature.dao.impl.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ReimbursementDelegate {
	private EmployeeDao ed = new EmployeeDaoImpl();
	private ReimbursementDao rd = new ReimbursementDaoImpl();
	private ManagerDao md = new ManagerDaoImpl();	
	private static Logger log = Logger.getRootLogger();

	
	public void addReimbursement(HttpServletRequest request, HttpServletResponse response){
		log.info("ReimbursementDelegate addReimbursement path: " + request.getServletPath()); 
		String amount = request.getParameter("amount");
		String username = request.getParameter("username");
		int amounts = Integer.parseInt(amount);
		Employee e = new Employee();
		e = ed.getEmployeeByUN(username);
		rd.addReimbursement(e, amounts);
		response.setStatus(200);
		}
	
	public void viewReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("ReimbursementDelegate viewReimbursement path: " + request.getServletPath()); 
		String type = request.getParameter("type");
		String username = request.getParameter("username");
		String position = request.getParameter("position");
		List<Reimbursement> Reimbursement = new ArrayList<Reimbursement>();
		if(position.equals("Manager")) {
			if(type.equals("all")) {
				String employeeId = request.getParameter("employeeid");
				int eId = Integer.parseInt(employeeId);
				Reimbursement = rd.getReimbursementByEmId(eId);
			}
			else {
				Reimbursement = rd.getReimbursement(type);
				}
			try (PrintWriter pw = response.getWriter()){
				pw.write(new ObjectMapper().writeValueAsString(Reimbursement));
			}
			}
		else {
			Employee e = new Employee();
			e = ed.getEmployeeByUN(username);
			Reimbursement = rd.emGetReimbursement(type, e.getId());
			try (PrintWriter pw = response.getWriter()){
				pw.write(new ObjectMapper().writeValueAsString(Reimbursement));
				response.setStatus(200);
			}
		}
	}

	public void reimbursementResult(HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("ReimbursementDelegate reimbursementResult path: " + request.getServletPath()); 
		String username = request.getParameter("username");
		String rid = request.getParameter("rid");
		String result = request.getParameter("result");
		
		int id = Integer.parseInt(rid);
		Manager m = md.getManagerByUN(username);
		rd.updateReimbursement(id, m.getId(), result);
		response.setStatus(200);
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
		reimbursement = rd.getReimbursement("pending");
		try (PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(reimbursement));
		}
		
		
	}
	

}

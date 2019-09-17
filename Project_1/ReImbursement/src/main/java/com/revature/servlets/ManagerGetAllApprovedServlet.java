package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReceiptDaoImpl;
import com.revature.daos.ReceiptsDao;
import com.revature.models.Receipts;

public class ManagerGetAllApprovedServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	EmployeeDao ed = new EmployeeDaoImpl();
	ReceiptsDao rd = new ReceiptDaoImpl();

	public ManagerGetAllApprovedServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("user_name");
		String password = (String) session.getAttribute("password");
		
		int emp_id = ed.login(username, password);
		
		List<Receipts> receipts =  rd.getAllApprovedReceiptsForManager();
		
		System.out.println(receipts);
		
		ObjectMapper om = new ObjectMapper();
		  om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		  om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		  
		String receiptsJSON = om.writeValueAsString(receipts);
		System.out.println(receiptsJSON);
		
		try(PrintWriter pw = response.getWriter()){
			
			pw.write(receiptsJSON);
		}
		

		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
}

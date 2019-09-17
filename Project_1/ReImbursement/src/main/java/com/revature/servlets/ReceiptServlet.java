package com.revature.servlets;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReceiptDaoImpl;
import com.revature.daos.ReceiptsDao;
import com.revature.models.Receipts;

/**
 * Servlet implementation class ReceiptServlet
 */
//@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		EmployeeDao e = new EmployeeDaoImpl();
		
		String username = "";
		String password = "";
		
		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("user_name") == null && session.getAttribute("password") == null) {
			response.sendRedirect("/Reimbursement/login");
		}else {
						
			 username = (String) session.getAttribute("user_name");
			 password = (String) session.getAttribute("password");
		}
		
		int emp_id = e.login(username, password);
		
		ReceiptsDao rd = new ReceiptDaoImpl();
		
		List<Receipts> receipts = rd.getReceiptsByEmployeeId(emp_id);
		
		System.out.println("DEBUG: ");
		System.out.println(receipts);
		
		ObjectMapper om = new ObjectMapper();
	    om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	    om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);


		String receiptsJSON = om.writeValueAsString(receipts);
		
		System.out.println("DEBUG2:" + receiptsJSON);
		
		try(PrintWriter pw = response.getWriter();){
			
			pw.write(receiptsJSON);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ReceiptsDao rd = new ReceiptDaoImpl();
//		EmployeeDao ed = new EmployeeDaoImpl();
//		Receipts rr = new Receipts();
//		
//		HttpSession session = request.getSession(false);
//		
//		String username = (String) session.getAttribute("user_name");
//		String password = (String) session.getAttribute("password");
//		
//		
//		int emp_id = ed.login(username, password);
//		
//		
//
//		Double amount = Double.parseDouble(request.getParameter("amount"));
//     
//		String note = request.getParameter("note");
//		System.out.println("Hit post route");
//		
//		rr.setAmount(amount);
//		rr.setNote(note);
//		rr.setEmployee_id(emp_id);
//		
//		rd.createReceipt(rr);
//		
//		response.sendRedirect("employee");
		
	}

}

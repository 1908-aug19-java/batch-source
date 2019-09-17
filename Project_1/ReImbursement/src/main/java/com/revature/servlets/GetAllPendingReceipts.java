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

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReceiptDaoImpl;
import com.revature.daos.ReceiptsDao;
import com.revature.models.Receipts;

/**
 * Servlet implementation class GetAllPendingReceipts
 */
//@WebServlet("/GetAllPendingReceipts")
public class GetAllPendingReceipts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPendingReceipts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ReceiptsDao rd = new ReceiptDaoImpl();
		
		
		String username = null;
		String password = null;
//		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user_name") == null && session.getAttribute("password") == null) {
			response.sendRedirect("/Reimbursement/login");

		}else {
			username = (String) session.getAttribute("user_name");
			 password = (String) session.getAttribute("password");
			 
			 System.out.println(username);
			 
			 
			 List<Receipts> receipts = rd.getAllPendingReceipts();
			 
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
	
			 
//				List<Receipts> receipts = rd.getAllPendingReceipts();
//				
//				System.out.println(receipts);
//				
//				ObjectMapper om = new ObjectMapper();
//				 om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//				 om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//				String receiptsJSON = om.writeValueAsString(receipts);
//				System.out.println(receiptsJSON);
//				
//				try(PrintWriter pw = response.getWriter()){
//					
//					pw.write(receiptsJSON);
//				}
		
		
	
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

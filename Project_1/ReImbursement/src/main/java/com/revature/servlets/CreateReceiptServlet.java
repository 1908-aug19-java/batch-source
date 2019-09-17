package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReceiptDaoImpl;
import com.revature.daos.ReceiptsDao;
import com.revature.models.Receipts;

/**
 * Servlet implementation class CreateReceiptServlet
 */
@WebServlet("/CreateReceiptServlet")
public class CreateReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = null;
		String password = null;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user_name") == null && session.getAttribute("password")== null) {
			response.sendRedirect("/Reimbursement/login");
		}else {
						
			 username = (String) session.getAttribute("user_name");
			 password = (String) session.getAttribute("password");
			 request.getRequestDispatcher("Views/NewReceipt.html").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReceiptsDao rd = new ReceiptDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();
		Receipts rr = new Receipts();
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("user_name");
		String password = (String) session.getAttribute("password");
		
		
		int emp_id = ed.login(username, password);
		
		

		Double amount = Double.parseDouble(request.getParameter("amount"));
     
		String note = request.getParameter("note");
		
		rr.setAmount(amount);
		rr.setNote(note);
		rr.setEmployee_id(emp_id);
		
		rd.createReceipt(rr);
		
		response.sendRedirect("/Reimbursement/viewreceipts");
		
	}

}

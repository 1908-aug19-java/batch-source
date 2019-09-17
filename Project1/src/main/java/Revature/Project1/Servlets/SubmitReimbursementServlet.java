package Revature.Project1.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Revature.Project1.DAO.ReimbursementDao;
import Revature.Project1.DAOImpl.ReimbursementDaoImpl;
import Revature.Project1.Model.Reimbursement;

public class SubmitReimbursementServlet extends HttpServlet {
	public static Logger LOG = Logger.getLogger(LoginServlet.class.getName());
	private static final long serialVersionUID = 1L;
       

    public SubmitReimbursementServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Html/SubmitReimbursement.html").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//replace with service
		ReimbursementDao rd = new ReimbursementDaoImpl();
		/////////////////////////////////////////////
		///////Session Variables
		HttpSession session = request.getSession(false);
		int id = (int)session.getAttribute("id");
		//////////
		
		String amount = request.getParameter("amount").trim();
		double reAmount = Double.parseDouble(amount);
		String description = request.getParameter("description").trim();
		
		Reimbursement r = new Reimbursement(reAmount,description,id);
		rd.createReimbursement(r);
		
		LOG.info("Reimbursement Request Submitted");
		doGet(request,response);
		
	}

}

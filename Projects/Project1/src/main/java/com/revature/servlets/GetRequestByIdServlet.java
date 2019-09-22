package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.daos.ReimbursmentsDao;
import com.revature.models.EmployeeReimbursments;
import com.revature.models.Reimbursment;
import com.revature.services.EmployeeService;
import com.revature.services.ReeimbursmentServices;


public class GetRequestByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReeimbursmentServices rembSer = new ReeimbursmentServices();
	private static Logger log = Logger.getRootLogger();

    
    public GetRequestByIdServlet() {
    	
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Views/showReqById.html").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("GetRequestByIdServlet DOPOST");
		String s = request.getParameter("id");
		int id = Integer.valueOf(s);

		log.info("emp id"+id);	
		ObjectMapper om = new ObjectMapper();
		response.setHeader("Content-Type", "json");
		response.getOutputStream().write(om.writeValueAsBytes(rembSer.getReimbursementsById(id,request, response)));
	
	}

}

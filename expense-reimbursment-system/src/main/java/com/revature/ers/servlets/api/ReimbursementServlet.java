package com.revature.ers.servlets.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.dao.ReimbursementDAO;
import com.revature.ers.dao.ReimbursementDAOimpl;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.security.SecurityHandler;
import com.revature.ers.util.StatusEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class ReimbursementServlet
 */
@WebServlet("/api/reimbursements")
public class ReimbursementServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ReimbursementServlet.class);
	private static final UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	private static final ReimbursementDAO ReimbursementDAO = new ReimbursementDAOimpl();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReimbursementServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("ReimbursementServlet: running doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("ReimbursementServlet: running doGet");
		SecurityHandler securityHandler = new SecurityHandler();
		try {
			Jws<Claims> claims = securityHandler.getJwsClaims(request.getHeader("Authorization"));
			Long id = userAccountDAO.findByEmail((String) claims.getBody().get("email"))
					.orElseThrow(NullPointerException::new).getId();
			ReimbursementDAO.save(new Reimbursement(Double.parseDouble(request.getParameter("reimbursment_amount")),
					StatusEnum.PENDING.getName(), id, 0L));
		} catch (NullPointerException | NumberFormatException | ExpiredJwtException e) {
			response.sendError(400);
			LOGGER.error(e);
		} 
	}

}

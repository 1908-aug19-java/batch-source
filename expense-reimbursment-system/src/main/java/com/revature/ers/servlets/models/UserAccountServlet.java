package com.revature.ers.servlets.models;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.ers.dao.UserAccountDAO;
import com.revature.ers.dao.UserAccountDAOimpl;
import com.revature.ers.models.UserAccount;
import com.revature.ers.util.Pair;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/user-accounts")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccountDAO userAccountDAO = new UserAccountDAOimpl();
	private Gson gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pair emailPair = new Pair("email",request.getParameter("email"));
		Pair firstNamePair = new Pair("first_name",request.getParameter("first_name"));
		Pair lastNamePair = new Pair("last_name",request.getParameter("last_name"));
		Pair[] pairs = {emailPair,firstNamePair,lastNamePair};
		pairs = Arrays.stream(pairs).filter(a->a.getValue() != null).toArray(Pair[]::new);
		List<UserAccount> userAccounts = userAccountDAO.findByParams(pairs);
		String userAccountsJson = gson.toJson(userAccounts);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userAccountsJson);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

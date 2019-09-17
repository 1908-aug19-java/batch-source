package Revature.Project1.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Revature.Project1.DAO.EmployeeDao;
import Revature.Project1.DAO.ManagerDao;
import Revature.Project1.DAOImpl.EmployeeDaoImpl;
import Revature.Project1.DAOImpl.ManagerDaoImpl;

public class LoginServlet extends HttpServlet {
	public static Logger LOG = Logger.getLogger(LoginServlet.class.getName());
	
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		request.getRequestDispatcher("/Html/LoginPage.html").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		//To be updated with service methods
		ManagerDao md = new ManagerDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();
		//////////////////////////////////////
		
		String email = request.getParameter("email").trim();
		String pass = request.getParameter("password").trim();
		int login = ed.loginEmployee(email, pass);
		int login2 = md.loginManager(email, pass);
		
		HttpSession session = request.getSession();		

		
		if(login!=0) {
			session.setAttribute("id",login);
			LOG.info("Employee Logged In");
			request.getRequestDispatcher("/Html/EmployeeHome.html").forward(request, response);
		}else if(login2!=0){
			session.setAttribute("id",login2);
			LOG.info("Employee Logged In");
			request.getRequestDispatcher("/Html/ManagerHome.html").forward(request, response);
		} else {
			doGet(request,response);
		}
	}
}

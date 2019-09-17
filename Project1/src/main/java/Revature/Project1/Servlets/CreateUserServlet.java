package Revature.Project1.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import Revature.Project1.DAO.EmployeeDao;
import Revature.Project1.DAO.ManagerDao;
import Revature.Project1.DAOImpl.EmployeeDaoImpl;
import Revature.Project1.DAOImpl.ManagerDaoImpl;
import Revature.Project1.Model.Employee;
import Revature.Project1.Model.Manager;

public class CreateUserServlet extends HttpServlet {
	public static Logger LOG = Logger.getLogger(LoginServlet.class.getName());
	private static final long serialVersionUID = 1L;
       
    public CreateUserServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Html/CreateUser.html").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//replace with service methods
		EmployeeDao ed = new EmployeeDaoImpl();
		ManagerDao md = new ManagerDaoImpl();
		///////////////////////////////////
		
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim().toLowerCase();
		String pass = request.getParameter("password").trim();
		String isManager = request.getParameter("isManager");
		
		if(isManager == null) {
			Employee e = new Employee(name,email,pass);
			ed.createEmployee(e);
			LOG.info("Employee Created");
			request.getRequestDispatcher("/Html/LoginPage.html").forward(request, response);
		} else{
			Manager m = new Manager(name,email,pass);
			md.createManager(m);
			LOG.info("Manager Created");
			request.getRequestDispatcher("/Html/LoginPage.html").forward(request, response);
		}
		
		
	}

}

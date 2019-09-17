package Revature.Project1.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import Revature.Project1.DAO.EmployeeDao;
import Revature.Project1.DAOImpl.EmployeeDaoImpl;
import Revature.Project1.Model.Employee;

public class EmployeeViewInfoServlet extends HttpServlet {
	public static Logger LOG = Logger.getLogger(LoginServlet.class.getName());
	private static final long serialVersionUID = 1L;

    public EmployeeViewInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		EmployeeDao ed = new EmployeeDaoImpl();	
		HttpSession session = request.getSession(false);
		int id = (int)session.getAttribute("id");
		//////////
		
		List<Employee> emp = ed.getEmployee(id);
		
		ObjectMapper om = new ObjectMapper();
		String employeeJSON = om.writeValueAsString(emp);
		response.setHeader("employeeJSON", employeeJSON);
		request.getRequestDispatcher("/Html/EmployeeManageInfo.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDao ed = new EmployeeDaoImpl();
		HttpSession session = request.getSession(false);
		int id = (int)session.getAttribute("id");
		
		
		String newUname = request.getParameter("change-uname").toLowerCase();
		String newPass = request.getParameter("change-pass");
		
		if(!newUname.equals("") && newPass.equals("")) {
			LOG.info("Username Updated");
			ed.changeEmployeeUname(newUname, id);
		} else if(newUname.contentEquals("") && !newPass.equals("")) {
			LOG.info("Password Updated");
			ed.changeEmployeePass(newPass, id);
		} else if(!newUname.contentEquals("") && !newPass.equals("")) {
			LOG.info("Username and Password Updated");
			ed.changeEmployeeCredentials(newUname, newPass, id);
		}
		doGet(request,response);
	}
}

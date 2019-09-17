package Revature.Project1.Util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Revature.Project1.DAO.EmployeeDao;
import Revature.Project1.DAO.ManagerDao;
import Revature.Project1.DAO.ReimbursementDao;
import Revature.Project1.DAOImpl.EmployeeDaoImpl;
import Revature.Project1.DAOImpl.ManagerDaoImpl;
import Revature.Project1.DAOImpl.ReimbursementDaoImpl;


public class JUnitTest {
	private static EmployeeDao ed = new EmployeeDaoImpl();
	private static ManagerDao md = new ManagerDaoImpl();
	private static ReimbursementDao rd = new ReimbursementDaoImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void loginUserTest() {
		assertNotEquals(0,ed.loginEmployee("kaylanhusband@gmail.com", "kayjay709"));
	}
	
	@Test
	public void loginManagerTest() {
		assertNotEquals(0,md.loginManager("husband@gmail.com", "kayjay709"));
	}
	
	@Test
	public void employeeUnameChanged() {
		assertTrue(ed.changeEmployeeUname("anotherEmail@gmail.com", 1));
	}
	
	@Test
	public void employeePassChanged() {
		assertTrue(ed.changeEmployeePass("anotherpass", 1));
	}
	
	@Test
	public void reimbursmentApproved() {
		rd.approveReimbursement(1, 1);
	}
	
	@Test
	public void reimbursmentDenied() {
		rd.approveReimbursement(1, 1);
	}
}

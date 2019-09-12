package com.revature.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.DepartmentDao;
import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImplTest {
	
	DepartmentDao dd = new DepartmentDaoImpl();
	
	@BeforeClass 
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection();){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void testGetDepartmentById() {
		Department d = new Department(1, "HR", 5400);
		assertEquals(d, dd.getDepartmentById(1));
	}
	
	@Test
	public void returnNullWhenIdNotFound() {
		assertNull(dd.getDepartmentById(7));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try( Connection c = ConnectionUtil.getConnection();){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}	

}

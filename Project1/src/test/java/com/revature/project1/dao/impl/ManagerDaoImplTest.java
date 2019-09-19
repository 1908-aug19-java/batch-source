package com.revature.project1.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.revature.dao.ManagerDao;
import com.revature.dao.impl.ManagerDaoImpl;
import com.revature.model.Manager;

public class ManagerDaoImplTest {
	ManagerDao md = new ManagerDaoImpl();

	@Test
	public void testGetEmployeeByUN() {
		Manager m = new Manager(1, "manager1", "manager1");
		assertEquals(m, md.getManagerByUN("manager1"));
	}
	@Test
	public void testNotExistEmployeeByUN() {
		Manager m = new Manager(5, "manager5", "manager5");
		assertNotEquals(m, md.getManagerByUN("manager5"));
	}
}

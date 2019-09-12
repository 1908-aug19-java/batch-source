package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AnnotationTest {
	
	@Before
	public void beforeTest() {
		System.out.println("before test");
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("before class");
	}
	
	@Test
	public void testOne() {
		System.out.println("Called test one");
		assertEquals(5,5);
	}
	
	@Test
	public void testTwo() {
		System.out.println("Called test two");
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testThree() {
		System.out.println("Called test three");
		fail("test three failed");
	}
	
	@After
	public void afterTest() {
		System.out.println("after test");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("after class");
	}

}

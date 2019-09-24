package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		c.add(3, 4);
		c.subtract(4, 2);
		c.multiply(5,7);
		c.divide(12, 6);
		c.divide(8, 0);
	}
	

}

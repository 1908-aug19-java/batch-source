package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;

public class Driver {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Bear b = (Bear) ac.getBean("bear");
		Bear.setWinter(true);
		b.setFull(true);
		b.bearHibernates();
		System.out.println(b);
		b.wakeBear();
		System.out.println(b);
		

	}

}

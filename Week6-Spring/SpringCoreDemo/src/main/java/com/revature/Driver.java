package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.BearWithAutomagic;
import com.revature.beans.BearWithAutowiring;
import com.revature.beans.BearWithConstructor;
import com.revature.beans.BearWithSetter;
import com.revature.beans.Cave;

public class Driver {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Cave c = (Cave) ac.getBean("cave");
		System.out.println(c);
		
		BearWithConstructor bearWithConstructor = (BearWithConstructor) ac.getBean("bearWithConstructor");
		System.out.println(bearWithConstructor);
		
		BearWithSetter bearWithSetter = (BearWithSetter) ac.getBean("bearWithSetter");
		System.out.println(bearWithSetter);
		
		BearWithAutowiring bearWithAutowiring = (BearWithAutowiring) ac.getBean("bearWithAutowiring");
		System.out.println(bearWithAutowiring);
		
		BearWithAutomagic bearWithAutomagic = (BearWithAutomagic) ac.getBean("bearWithAutomagic");
		System.out.println(bearWithAutomagic);
	}

}

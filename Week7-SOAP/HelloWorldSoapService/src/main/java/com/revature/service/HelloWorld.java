package com.revature.service;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	/*
	 * this represents my SEI (Service Endpoint Interface)
	 */
	
	String sayHi(String text);

}

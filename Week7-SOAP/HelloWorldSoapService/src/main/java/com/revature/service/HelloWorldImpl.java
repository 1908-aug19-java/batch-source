package com.revature.service;

import javax.jws.WebService;
// SERVER: Service implementing bean


@WebService(endpointInterface="com.revature.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHi(String text) {
		System.out.println(text);
		return "processed: "+text;
	}

}

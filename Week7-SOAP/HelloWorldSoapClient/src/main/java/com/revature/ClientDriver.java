package com.revature;

import com.revature.service.HelloWorld;
import com.revature.service.HelloWorldImplService;

//CLIENT: Makes requests to invoke SIB methods

public class ClientDriver {
	
	// we ran wsimport -Xnocompile [WSDL] to generate client code

	public static void main(String[] args) {
		
		HelloWorldImplService helloService = new HelloWorldImplService();
		HelloWorld helloWorld = helloService.getHelloWorldImplPort();
		// we are now able to invoke any of the SEI methods on helloWorld
		
		String message = "Hello World";
		System.out.println("sending message from client: "+message);
		String response = helloWorld.sayHi(message);
		System.out.println("response from server:"+response);
		
		
	}

}

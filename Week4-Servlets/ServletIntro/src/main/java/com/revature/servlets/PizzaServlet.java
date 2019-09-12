package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Pizza;
import com.revature.services.PizzaService;

public class PizzaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PizzaService ps = new PizzaService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		List<Pizza> pizzaList = ps.getPizzas();
		
		ObjectMapper om = new ObjectMapper();
		String pizzaJSON = om.writeValueAsString(pizzaList);
//		System.out.println(pizzaJSON);
		try(PrintWriter pw = response.getWriter()){
			pw.print(pizzaJSON);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// get request body from my HTTP request
		String newPizzaJSON = request.getReader().readLine();
		
		System.out.println(newPizzaJSON);
		
		ObjectMapper om = new ObjectMapper();
		Pizza p = om.readValue(newPizzaJSON, Pizza.class);
		
		System.out.println(p);
		
		boolean success = ps.addPizza(p);
		if(success) {
			response.setStatus(201);
		} else {
			response.sendError(400);
		}
		
	
	}
	
	

}

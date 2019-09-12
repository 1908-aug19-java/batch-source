package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.models.Pizza;

public class PizzaService {
	
	private List<Pizza> pizzas = new ArrayList<>();
	
	public PizzaService() {
		pizzas.add(new Pizza(1, Arrays.asList(new String[] {"mushrooms", "pepperoni"}),"red","garlic"));
		pizzas.add(new Pizza(2, Arrays.asList(new String[] {"anchovies", "pineapple"}),"ranch","cheesy"));
		pizzas.add(new Pizza(3, Arrays.asList(new String[] {"sausage", "peppers", "onion"}),"pesto","thin"));
		pizzas.add(new Pizza(4, Arrays.asList(new String[] {}),"red","normal"));
	}

	public List<Pizza> getPizzas(){
		return new ArrayList<Pizza>(pizzas);
	}
	
	public boolean addPizza(Pizza p) {
		return pizzas.add(p);
	}
	
}

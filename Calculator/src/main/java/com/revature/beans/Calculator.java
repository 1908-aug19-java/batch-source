package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public Calculator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double add(double first, double second) {
		return first + second;
	}

	public double subtract(double first, double second) {
		return first - second;
	}

	public double multiply(double first, double second) {
		return first * second;
	}

	public double divide(double first, double second) {
		if (second == 0) {
			throw new IllegalArgumentException("Can not divide by 0");
		} else
			return first / second;
	}
}

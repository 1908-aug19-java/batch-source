package com.revature.Exercise1;

public class Gas extends Products {

	public Gas() {
		// TODO Auto-generated constructor stub
	}

	public Gas(float price, String name, double tax) {
		super(price, name, tax);
		setPrice(price);
		setName(name);
		setTax(tax);
	}

	@Override
	public String toString() {
		return "Gas [PaymentMethod()=" + PaymentMethod() + ", getName()=" + getName() + ", getPrice()=" + getPrice()
				+ ", getTax()=" + getTax() + "]";
	}

	@Override
	public String PaymentMethod() // add unimplemented method form abstract class
	{
		// TODO Auto-generated method stub
		return "Cash Only";
	}


}

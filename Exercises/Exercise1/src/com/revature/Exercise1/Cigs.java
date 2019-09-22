package com.revature.Exercise1;

public class Cigs extends beer  { // inherit behavior of beer class such as age restriction

	public Cigs() {
		// TODO Auto-generated constructor stub
	}

	public Cigs(String type, double price, String name, Boolean AgeRestriction, double tax) {
		super(type, price, name, AgeRestriction, tax);
		// TODO Auto-generated constructor stub
		setPrice(price);
		setName(name);
		setTax(tax);
	}

	public Cigs(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cigs [AgeRestriction=" + AgeRestriction + ", getType()=" + getType() + ", PaymentMethod()="
				+ PaymentMethod() + ", getName()=" + getName() + ", getPrice()=" + getPrice() + ", getTax()=" + getTax()
				+ "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}




}




	




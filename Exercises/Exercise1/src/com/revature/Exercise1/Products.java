package com.revature.Exercise1;

public abstract class Products {
	public double price;   //variables
	String name;
	private double tax;
	


	public Products() // constructor default
	{
	    super();
	}
	public Products(double price,String name,double tax) {
		super();
		this.price = price; // initialize variables
		this.tax=tax;
		this.name=name;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getTax() { // Encapsulation  Tax was assigned private
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "Products [getName()=" + getName() + ", getPrice()=" + getPrice() + ", getTax()=" + getTax() + "]";
	}
	
	public abstract  String PaymentMethod(); // abstarct method to be used by subclasses
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(tax) != Double.doubleToLongBits(other.tax))
			return false;
		return true;
	}

}

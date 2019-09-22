package com.revature.Exercise1;

public class beer extends Products implements checkingID  // beer class inherits from Products class
{
	private String type;
	public Boolean AgeRestriction=true; 


	public beer() {
		// TODO Auto-generated constructor stub
		super();

	}
	public beer(String type) {
		 this.type=type; // initialize
		 
	 }
	 public beer(String type,double price, String name, Boolean AgeRestriction, double tax) {
		super(price, name, tax); 
		// TODO Auto-generated  super constructor stub
		setPrice(price);
		setName(name);
		setTax(tax);
	}

    // remove ageRestriction since interface is created
	public String getType() {
	    return type;
     }
     public void setType(String type) {
	      this.type = type;
     }


	@Override
	public String toString() {
		return "beer [type=" + type + ", AgeRestriction=" + AgeRestriction + ", PaymentMethod()=" + PaymentMethod()
				+ ", getName()=" + getName() + ", getPrice()=" + getPrice() + ", getTax()=" + getTax() + "]";
	}
	//ADD iMPLEMENTED METHOD
	public boolean AgeRestriction() {
		// TODO Auto-generated method stub
		return AgeRestriction();
		
	}
	@Override
	public String PaymentMethod() // add unimplemented method form abstract class
	{
		// TODO Auto-generated method stub
		return "Cash or Cards";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((AgeRestriction == null) ? 0 : AgeRestriction.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		beer other = (beer) obj;
		if (AgeRestriction == null) {
			if (other.AgeRestriction != null)
				return false;
		} else if (!AgeRestriction.equals(other.AgeRestriction))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


}





/*

*/
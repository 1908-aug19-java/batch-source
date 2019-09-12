package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private List<String> toppings;
	private String sauceType;
	private String crustType;
	
	public Pizza(int id, List<String> toppings, String sauceType, String crustType) {
		super();
		this.id = id;
		this.toppings = toppings;
		this.sauceType = sauceType;
		this.crustType = crustType;
	}

	public Pizza() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getToppings() {
		return toppings;
	}

	public void setToppings(ArrayList<String> toppings) {
		this.toppings = toppings;
	}

	public String getSauceType() {
		return sauceType;
	}

	public void setSauceType(String sauceType) {
		this.sauceType = sauceType;
	}

	public String getCrustType() {
		return crustType;
	}

	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crustType == null) ? 0 : crustType.hashCode());
		result = prime * result + id;
		result = prime * result + ((sauceType == null) ? 0 : sauceType.hashCode());
		result = prime * result + ((toppings == null) ? 0 : toppings.hashCode());
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
		Pizza other = (Pizza) obj;
		if (crustType == null) {
			if (other.crustType != null)
				return false;
		} else if (!crustType.equals(other.crustType))
			return false;
		if (id != other.id)
			return false;
		if (sauceType == null) {
			if (other.sauceType != null)
				return false;
		} else if (!sauceType.equals(other.sauceType))
			return false;
		if (toppings == null) {
			if (other.toppings != null)
				return false;
		} else if (!toppings.equals(other.toppings))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", toppings=" + toppings + ", sauceType=" + sauceType + ", crustType=" + crustType
				+ "]";
	}
	
	
	
	
	

}

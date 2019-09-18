package com.revature.model;

import java.io.Serializable;

public class Location implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	
	int id;
	String name;
	String address;
	int showType; //(0 conference, 1 concert, 2 festival, 3 convention)
	int capacity;
	int avaiability;//(0 not avaible 1 avaible)	
	double price;
	public Location() {
		super();
	}
	public Location(
			int id, 
			String name, 
			String address, 
			int showType, 
			int capacity, 
			int avaiability, 
			double price) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.showType = showType;
		this.capacity = capacity;
		this.avaiability = avaiability;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getAvaiability() {
		return avaiability;
	}
	public void setAvaiability(int avaiability) {
		this.avaiability = avaiability;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + avaiability;
		result = prime * result + capacity;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + showType;
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
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (avaiability != other.avaiability)
			return false;
		if (capacity != other.capacity)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (showType != other.showType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", address=" + address + ", showType=" + showType
				+ ", capacity=" + capacity + ", avaiability=" + avaiability + ", price=" + price + "]";
	}
	
	
	
	
}

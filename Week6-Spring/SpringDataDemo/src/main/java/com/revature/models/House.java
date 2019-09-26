package com.revature.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
public class House implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private Integer propertyId;
	
	@Positive
	private int numOfBedrooms;
	
	@Positive
	private int numOfBaths;
	
	private String street;
	
	@Size(min=2,max=2)
	private String state;
	
	private String city;
	

	public House() {
		super();
	}
	
	public House(Integer propertyId) {
		super();
		this.propertyId = propertyId;
	}


	public House(Integer propertyId, int numOfBedrooms, int numOfBaths, String street, String state, String city) {
		super();
		this.propertyId = propertyId;
		this.numOfBedrooms = numOfBedrooms;
		this.numOfBaths = numOfBaths;
		this.street = street;
		this.state = state;
		this.city = city;
	}


	public Integer getPropertyId() {
		return propertyId;
	}


	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}


	public int getNumOfBedrooms() {
		return numOfBedrooms;
	}


	public void setNumOfBedrooms(int numOfBedrooms) {
		this.numOfBedrooms = numOfBedrooms;
	}


	public int getNumOfBaths() {
		return numOfBaths;
	}


	public void setNumOfBaths(int numOfBaths) {
		this.numOfBaths = numOfBaths;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + numOfBaths;
		result = prime * result + numOfBedrooms;
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		House other = (House) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (numOfBaths != other.numOfBaths)
			return false;
		if (numOfBedrooms != other.numOfBedrooms)
			return false;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "House [propertyId=" + propertyId + ", numOfBedrooms=" + numOfBedrooms + ", numOfBaths=" + numOfBaths
				+ ", street=" + street + ", state=" + state + ", city=" + city + "]";
	}
	
	

}

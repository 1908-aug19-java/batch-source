package com.revature.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int id;
	String firstName;
	String lastName;
	String email;
	String userName;
	String password;
	int numberRequestsDone;
	double totalSpends;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(int id, String firstName, String lastName, 
			String email, String userName, String password,
			int numberRequestsDone, double totalSpends) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.numberRequestsDone = numberRequestsDone;
		this.totalSpends = totalSpends;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumberRequestsDone() {
		return numberRequestsDone;
	}
	public void setNumberRequestsDone(int numberRequestsDone) {
		this.numberRequestsDone = numberRequestsDone;
	}
	public double getTotalSpends() {
		return totalSpends;
	}
	public void setTotalSpends(double totalSpends) {
		this.totalSpends = totalSpends;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + numberRequestsDone;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalSpends);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (numberRequestsDone != other.numberRequestsDone)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (Double.doubleToLongBits(totalSpends) != Double.doubleToLongBits(other.totalSpends))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userName=" + userName + ", password=" + password + ", numberRequestsDone=" + numberRequestsDone
				+ ", totalSpends=" + totalSpends + "]";
	}
	
	
	
	

}

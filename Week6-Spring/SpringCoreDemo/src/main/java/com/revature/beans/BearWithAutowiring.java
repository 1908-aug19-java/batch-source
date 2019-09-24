package com.revature.beans;

public class BearWithAutowiring extends Bear {
	
	// when using autowiring by type, the ioc container will match based on the type of the argument
	// when using autowiring by name, the ioc container will match based on the name of the bean and the setter declaration
		// (using "setHome(..)" here would not work with autowiring by type) 
	public void setCave(Cave c) {
		this.cave = c;
	}

	@Override
	public String toString() {
		return "BearWithAutowiring [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

}

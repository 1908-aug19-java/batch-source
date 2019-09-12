package com.revature.factory;

public class AnimalFactory {
	
	public Animal getAnimal(String type){
		if("bird".equals(type)) {
			return new Bird();
		} else if ("dog".equals(type)) {
			return new Dog();
		} else if ("fish".equals(type)) {
			return new Fish();
		}
		return null;
	}

}

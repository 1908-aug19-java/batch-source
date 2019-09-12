package com.revature.factory;

public class AnimalDriver {

	public static void main(String[] args) {
		AnimalFactory af = new AnimalFactory();
		Animal a1 = af.getAnimal("dog");
		Animal a2 = af.getAnimal("bird");
		Animal a3 = af.getAnimal("fish");
		
		a1.makeNoise();
		a2.makeNoise();
		a3.makeNoise();
		
	}

}

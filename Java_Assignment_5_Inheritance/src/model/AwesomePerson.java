package model;

public class AwesomePerson extends Person {

	public AwesomePerson() {
		super();
	}
	
	public void talk() {
		
		System.out.printf("Name: %s\nAge: %d\n", this.name, this.age);
	}
}

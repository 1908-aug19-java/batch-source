package main;

public class Wheels extends CarPart{

	public int state;
	
	public Wheels() {
		
		this.condition = 100;
		this.state = 0;
	}
	
	public void status() {
		
		System.out.printf("Simulating Wheels: Condition %3d| Wear Level %d\n", this.condition, this.state);
	}
	public void function() {
		
		this.condition -= 4;
		if(this.condition % 20 == 0) this.state ++;
	}
}

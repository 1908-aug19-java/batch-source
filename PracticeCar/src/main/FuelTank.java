package main;

public class FuelTank extends CarPart{
	
	public int state;
	
	public FuelTank() {
		
		this.condition = 100;
		this.state = 100;
	}
	
	public void status() {
		
		System.out.printf("Simulating Fuel Tank: Condition %3d | Fuel Level %3d\n", this.condition, this.state);
	}
	
	public void function() {
		
		if(this.state == 5) {
			
			this.condition -= 20;
		}
		
		this.state -= 5;
	}

}

package main;
import java.util.List;
import java.util.ArrayList;

public class Car {

	public List<CarPart> parts = new ArrayList<CarPart>();
	
	public Car() {
		
		parts.add(new Engine());
		parts.add(new FuelTank());
		parts.add(new Wheels());
	}
	
	public void run() {
		
		for(CarPart p : this.parts) {
			
			p.function();
			p.status();
		}
	}
}

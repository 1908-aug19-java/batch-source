package com.revature.Exercise1;
import java.util.Scanner;
public class Store {

	public Store() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the Item Price");
		
		double B_Price=input.nextDouble();
		//double C_Price=input.nextDouble();
		//catch error
		try {
			double beerPrice= ErrorHandler(B_Price);
			// create second beer 1 objects/////////////////////////
			beer beer1=new beer();
			beer1.setType("6_Pack");
			beer1.setName("corona");
			beer1.setPrice(beerPrice);
			beer1.setTax(0.1);
			displayProducts(beer1);
			
			// create second beer 2 objects/////////////////////////
			beer beer2=new beer();
			beer2.setName("Budlight");
			beer2.setType("9_Pack");
			beer2.setPrice(beerPrice-1);
			displayProducts(beer2);//DISPLAY9
			
			
			
		}catch(NullPointerException e) {
			System.out.println(e);
			
		}


		
		// create Cigarette object//////////////////////
		Cigs cig1= new Cigs();
		cig1.setType(" pack");
        cig1.setName(" Marlboro");
		cig1.setPrice(7.05);
		cig1.setTax(0.09);
		displayProducts(cig1);
		
		// create regular product objects/////////////////////////
		Gas OneGallon=new Gas();
		OneGallon.setName("Gas");
		OneGallon.setPrice(2.99);
		OneGallon.setTax(0.00);
		displayProducts(OneGallon);//DISPLAY
		
		
		
	}
	// display objects using Polymorphism BY using one method to display multiple objts
	private static void displayProducts(Products productObj) {
		System.out.println(productObj.toString());	
	}
	//Exception Handler if price is 0
	public static double ErrorHandler(double B_Price) {
		  if (B_Price==0) {
			throw new NullPointerException(" Price Cannot be Zero");			
		    }
		return B_Price;
		}

}

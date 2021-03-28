package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Car {
	
	private String make, model;
	private int mileage, year, dominationCount;
	private float price;
	
	public Car(
		String make, 
		String model,
		int mileage, 
		int year,
		float price
			) {
		
		this.make    = make;
		this.model   = model;
		this.mileage = mileage;
		this.year    = year;
		this.price   = price;
		
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public float getPrice() {
		return price;
	}
	
	public int getDominationCount() {
		return dominationCount;
	}
	
	public void setDominationCount(ArrayList<Car> cars) {
		
		int dominationCount = 0;
				
		for(Car c: cars) {
			
			int yearC;
			int mileC;
			int priceC;
			
			if (this.getYear() > c.getYear()) {
				yearC = 1;
			} else if (this.getYear() == c.getYear()) {
				yearC = 0;
			} else {
				yearC = -1;
			}
			
			if (this.getMileage() > c.getMileage()) {
				mileC = 1;
			} else if (this.getMileage() == c.getMileage()) {
				mileC = 0;
			} else {
				mileC = -1;
			}
			
			if (this.getPrice() > c.getPrice()) {
				priceC = 1;
			} else if (this.getPrice() == c.getPrice()) {
				priceC = 0;
			} else {
				priceC = -1;
			}

			
			boolean notInferior = yearC >= 0 && mileC >= 0 && priceC >= 0;
			boolean isSuperior  = yearC >  0 || mileC >  0 || priceC >  0;
			
			if (isSuperior && notInferior) {
				dominationCount++;
			}
		}
		
		this.dominationCount = dominationCount;
	}

}

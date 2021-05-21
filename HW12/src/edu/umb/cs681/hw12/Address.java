package edu.umb.cs681.hw12;

public class Address {
	
	final private String street, city, state;
	final private int zipcode;
	
	public Address(String street, String city, String state, int zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public int getZipcode() {
		return zipcode;
	}
	
	public Address change(String street, String city, String state, int zipcode) {
		return new Address(street, city, state, zipcode);
	}

}

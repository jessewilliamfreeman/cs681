package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicReference;


public class Customer {
	
	private AtomicReference<Address> address;
	
	public Customer(Address addr) {
		this.address = new AtomicReference<Address>(addr);
	}
	
	public void setAddress(Address addr) {
		this.address.set(addr);
	}
	
	public Address getAddress() {
		return this.address.get();
	}
	
	public static void main(String[] args) {
		System.out.println("Two Threads with Once Customer");
		Customer cust = new Customer(new Address("Oak St.", "Cambridge", "MA", 23454));
		
		Thread bostonThread = new Thread(() -> {
			System.out.println("Setting Boston Address");
			cust.setAddress(new Address("Oak St.", "Boston", "MA", 23454));
			System.out.println("Boston Address Thread getAddress().getCity(): " + cust.getAddress().getCity());
		});
		
		Thread providenceThread = new Thread(() -> {
			System.out.println("Setting Providence Address");
			cust.setAddress(new Address("Oak St.", "Providence", "MA", 23454));
			System.out.println("Providence Address Thread getAddress().getCity(): " + cust.getAddress().getCity());
		});
		
		bostonThread.start();
		providenceThread.start();
		try {
			bostonThread.join();
			providenceThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

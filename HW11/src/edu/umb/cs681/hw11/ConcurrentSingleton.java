package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
	private ConcurrentSingleton() {};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(null);
	
	public static ConcurrentSingleton getInstance() {
		if (instance.get() == null) {
			instance.set(new ConcurrentSingleton()); 
		}
		return instance.get();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Two threads getting ConcurrentSingleton:");
		Thread t1 = new Thread(() -> {
			System.out.println(ConcurrentSingleton.getInstance());
		});
		Thread t2 = new Thread(() -> {
			System.out.println(ConcurrentSingleton.getInstance());
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}
	}
}

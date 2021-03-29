package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
	private ConcurrentSingleton() {};
	private static ConcurrentSingleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	
	public static ConcurrentSingleton getInstance() {
		lock.lock();
		try {
			if (instance==null) {
				instance = new ConcurrentSingleton(); 
			}
			return instance;
		}
		finally {
			lock.unlock();
		}
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

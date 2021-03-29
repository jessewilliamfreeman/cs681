package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator implements Runnable {
	
	private ReentrantLock lock;
	private boolean       done;
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
		
		this.lock = new ReentrantLock();
		this.done = false;
	}
	
	public void setDone() {
		this.lock.lock();
		try {
			this.done = true;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			this.lock.lock();
			try {
				if (this.done) {
					break;
				}
				else if( isPrime(n) ){ 
					primes.add(n); 
				}
			}
			finally {
				this.lock.unlock();
			}
		}
	}	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1L, 1000000L);
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(1L, 1000000L);
		gen1.setDone();
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}
		
		long primeNumCancel = gen1.getPrimes().size();
		long primeNumFull   = gen2.getPrimes().size();
		
		System.out.println("\n" + primeNumCancel + " prime numbers are found in canceled generator.");
		System.out.println("\n" + primeNumFull + " prime numbers are found in fully run generator.");
        

	}

}

package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

import edu.umb.cs681.hw10.RunnablePrimeFactorizer;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer implements Runnable {
	
	private ReentrantLock lock;
	private boolean       done;

	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		
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
	
	public ReentrantLock getLock() {
		return this.lock;
	}
	
	
	public boolean getDone() {
		return this.done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	    	this.lock.lock();
			try {
				if (this.done) {
					break;
				}
		    	if( divisor > 2 && isEven(divisor)) {
		    		divisor++;
		    		continue;
		    	}
			    if(dividend % divisor == 0) {
			        factors.add(divisor);
			        dividend /= divisor;
			    }else {
			    	if(divisor==2){ divisor++; }
			    	else{ divisor += 2; }
			    }
			}
			finally {
				this.lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		// Factorization of 36 with a separate thread
		System.out.println("Factorization of 36");	
		
		RunnableCancellablePrimeFactorizer runnableFull = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread threadFull = new Thread(runnableFull);
		
		RunnableCancellablePrimeFactorizer runnableCancel = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread threadCancel = new Thread(runnableCancel);
		
		runnableCancel.setDone();

		
		threadCancel.start();
		threadFull.start();
		try {
			threadCancel.join();
			threadFull.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n" + "Final result canceled thread: " + runnableCancel.getPrimeFactors());
		System.out.println("\n" + "Final result full run thread: " + runnableFull.getPrimeFactors());
	}
	
	

}

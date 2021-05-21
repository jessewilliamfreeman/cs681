package edu.umb.cs681.hw10;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer implements Runnable {

	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		// TODO Auto-generated constructor stub
	}
	
	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	    	this.getLock().lock();
			try {
				if (this.getDone()) {
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
				this.getLock().unlock();
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Factorization of 36");	
		RunnableCancellableInterruptiblePrimeFactorizer gen =
				new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(gen);
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\n" + "Final result thread interupted prime factors of 36: " + gen.getPrimeFactors());
	}


}

package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		ArrayList<Thread> withdrawThread = new ArrayList<Thread>();
		ArrayList<Thread> depositThread = new ArrayList<Thread>();
		ArrayList<DepositRunnable> depositRun = new ArrayList<DepositRunnable>();
		ArrayList<WithdrawRunnable> withdrawRun = new ArrayList<WithdrawRunnable>(); 
		
		for(int i = 0; i < 5; i++){
			depositRun.add(new DepositRunnable(bankAccount));
			depositThread.add(new Thread(depositRun.get(i)));
			depositThread.get(i).start();
			
			withdrawRun.add(new WithdrawRunnable(bankAccount));
			withdrawThread.add(new Thread(withdrawRun.get(i)));
			withdrawThread.get(i).start();
		}
		

		for(int i = 0; i < 5; i++){
			try {
				System.out.println("Deposit Thread " + i + " Termination");
				depositRun.get(i).setDone();
				depositThread.get(i).interrupt();
				depositThread.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("Withdraw Thread " + i + " Termination");
				withdrawRun.get(i).setDone();
				withdrawThread.get(i).interrupt();
				withdrawThread.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

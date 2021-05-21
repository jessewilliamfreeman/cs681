package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class AdmissionMonitor{
	private int currentVisitors = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition tooManyVisitorsCondition = lock.newCondition();
	private Condition noVisitorsCondition = lock.newCondition();
	
	public void enter(){
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + 
					" (en): current visitors: " + currentVisitors);
			while (currentVisitors >= 5) {
				System.out.println("Too many visitors. Please wait for a while!");
				tooManyVisitorsCondition.await();
			}
			currentVisitors++;
			System.out.println(Thread.currentThread().getId() + 
					" (en): current visitors: " + currentVisitors);
			noVisitorsCondition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void exit(){
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + 
					" (ex): current visitors: " + currentVisitors);
			while (currentVisitors <= 0) {
				System.out.println("No visitors, how could someone exit?");
				noVisitorsCondition.await();
			}
			currentVisitors--;
			System.out.println(Thread.currentThread().getId() + 
					" (ex): current visitors: " + currentVisitors);
			tooManyVisitorsCondition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public int countCurrentVisitors(){
		return currentVisitors; 
	}

	
	public static void main(String[] args){
		AdmissionMonitor admissionMonitor = new AdmissionMonitor();
		EntranceHandler enterRun = new EntranceHandler(admissionMonitor);
		ExitHandler exitRun = new ExitHandler(admissionMonitor); 
		Thread enterThread = new Thread(enterRun);
		Thread exitThread = new Thread(exitRun);
		
		enterThread.start();
		exitThread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			System.out.println("Enter Thread Termination");
			enterRun.setDone();
			enterThread.interrupt();
			enterThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		try {
			System.out.println("Exit Thread Termination");
			exitRun.setDone();
			exitThread.interrupt();
			exitThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

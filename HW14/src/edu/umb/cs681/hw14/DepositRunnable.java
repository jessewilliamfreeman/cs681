package edu.umb.cs681.hw14;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private boolean done;
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
		this.done = false;
	}
	
	public void setDone() {
		this.done = true;
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				if (this.done) {
					break;
				}
				account.deposit(100);
				Thread.sleep(2);
			}
		}catch(InterruptedException exception){}
	}
}

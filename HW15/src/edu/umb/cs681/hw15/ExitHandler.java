package edu.umb.cs681.hw15;

class ExitHandler implements Runnable{
	private AdmissionMonitor monitor;
	private boolean done;
	
	public ExitHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
		this.done = false;
	}
	
	public void setDone() {
		this.done = true;
	}
	
	public void run(){
		try{
			while(!this.done){
				monitor.exit(); 
				Thread.sleep(2);
			}
		}catch(InterruptedException exception){}
	} 
}
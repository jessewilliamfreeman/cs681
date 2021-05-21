package edu.umb.cs681.hw17;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class RequestHandler implements Runnable{
	
	private boolean notDone;
	private ArrayList<Path> fileList;
	
	@Override
	public void run() {
		this.notDone = true;
		
		this.fileList = new ArrayList<Path>();
		this.fileList.add(Paths.get("a.html"));
		this.fileList.add(Paths.get("b.html"));
		
		AccessCounter accessCounter = AccessCounter.getInstance();
		
		while(notDone) {
			Collections.shuffle(this.fileList);
			Path filePath = this.fileList.get(0);
			accessCounter.increment(filePath);
			
			System.out.println(
					"File Access " 
					+ filePath.toString() 
					+ ": " 
					+ accessCounter.getCount(filePath).toString());
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void setDone() {
		this.notDone = false;
	}
	

	public static void main(String[] args) {
		
		ArrayList<RequestHandler> handlers = new ArrayList<RequestHandler>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i++) {
			handlers.add(new RequestHandler());
			threads.add(new Thread(handlers.get(i)));
		}
		
		for (int i = 0; i < 10; i++) {
			threads.get(i).start();
		}
		
		
		for (int i = 0; i < 10; i++) {
			handlers.get(i).setDone();
			try {
				threads.get(i).interrupt();
				threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

package edu.umb.cs681.hw13;

import java.util.HashMap;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;


public class AccessCounter {
	private HashMap<Path, Integer> files;
	private ReentrantLock fileLock;
	private static ReentrantLock instanceLock = new ReentrantLock();
	private static AccessCounter instance;
	
	
	public static AccessCounter getInstance() {
		instanceLock.lock();
		try {
			if (instance==null) {
				instance = new AccessCounter(); 
				instance.fileLock = new ReentrantLock();
				instance.files = new HashMap<Path, Integer>();
			}
			return instance;
		}
		finally {
			instanceLock.unlock();
		}
	}
	
	public void increment(Path path) {
		this.fileLock.lock();
		
		if(this.files.containsKey(path)) {
			this.files.put(path, this.files.get(path) + 1);
		} else {
			this.files.put(path, 1);
		}
		
		this.fileLock.unlock();
	}
	
	public Integer getCount(Path path) {
		Integer returnValue;
		
		this.fileLock.lock();
		
		returnValue = this.files.getOrDefault(path, 0);
		
		this.fileLock.unlock();
		
		return returnValue;
	}
	
}

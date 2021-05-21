package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


public class AccessCounter {
	private ConcurrentHashMap<Path, Integer> files;
	private static ReentrantLock instanceLock = new ReentrantLock();
	private static AccessCounter instance;
	
	
	public static AccessCounter getInstance() {
		instanceLock.lock();
		try {
			if (instance==null) {
				instance = new AccessCounter(); 
				instance.files = new ConcurrentHashMap<Path, Integer>();
			}
			return instance;
		}
		finally {
			instanceLock.unlock();
		}
	}
	
	public void increment(Path path) {
		this.files.put(path, this.getCount(path) + 1);
	}
	
	public Integer getCount(Path path) {
		return this.files.getOrDefault(path, 0);
	}
	
}

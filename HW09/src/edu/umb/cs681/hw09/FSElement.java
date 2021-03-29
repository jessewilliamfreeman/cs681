package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	
	protected String        name;
	protected int           size;
	protected LocalDateTime creationTime;
	protected FSElement     parent;
	protected ReentrantLock lock;
	
	public FSElement(
			FSElement     parent,
			String        name,
			int           size,
			LocalDateTime creationTime
			) {
		
		this.parent       = parent;
		this.name         = name;
		this.size         = size;
		this.creationTime = creationTime;
		this.lock         = new ReentrantLock();
		
	}
	
	public String getName() {
		lock.lock();
		try {
			return name;
		}
		finally {
			lock.unlock();
		}
	}

	public void setName(String name) {
		lock.lock();
		try {
			this.name = name;
		}
		finally {
			lock.unlock();
		}
	}

	public int getSize() {
		lock.lock();
		try {
			return size;
		}
		finally {
			lock.unlock();
		}
	}

	public void setSize(int size) {
		lock.lock();
		try {
			this.size = size;
		}
		finally {
			lock.unlock();
		}
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setParent(FSElement parent) {
		lock.lock();
		try {
			this.parent = parent;
		}
		finally {
			lock.unlock();
		}
	}

	public FSElement getParent() {
		lock.lock();
		try {
			return parent;
		}
		finally {
			lock.unlock();
		}
	}
	
	public abstract boolean isDirectory();
}

package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public abstract class APFSElement extends FSElement {
	
	protected String        owner;
	protected LocalDateTime lastModified;

	public APFSElement(APFSDirectory parent, String name, int size, LocalDateTime creationTime, String owner) {
		super(parent, name, size, creationTime);
		
		this.owner        = owner;
		this.lastModified = creationTime;
		
		if (parent != null) {
			parent.appendChild(this);
		}
		
	}

	public String getOwner() {
		lock.lock();
		try {
			return owner;
		}
		finally {
			lock.unlock();
		}
	}

	public void setOwner(String owner) {
		lock.lock();
		try {
			this.owner = owner;
		}
		finally {
			lock.unlock();
		}
	}
	
	public LocalDateTime getLastModified() {
		lock.lock();
		try {
			return lastModified;
		}
		finally {
			lock.unlock();
		}
	}

	public void setLastModified(LocalDateTime lastModified) {
		lock.lock();
		try {
			this.lastModified = lastModified;
		}
		finally {
			lock.unlock();
		}
	}

}

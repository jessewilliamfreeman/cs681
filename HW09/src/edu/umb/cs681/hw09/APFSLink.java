package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class APFSLink extends APFSElement {
	
	private APFSElement target;

	public APFSLink(APFSDirectory parent, String name, LocalDateTime creationTime, String owner, APFSElement target) {
		super(parent, name, 0, creationTime, owner);
		this.target = target;
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	public FSElement getTarget() {
		lock.lock();
		try {
			return target;
		}
		finally {
			lock.unlock();
		}
	}


}

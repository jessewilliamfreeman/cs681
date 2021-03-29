package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class APFS extends FileSystem {
	
	private static APFS instance = new APFS();
	
	private APFS() {}
	
	public static APFS getFileSystem() {
		return instance;
	}
	
	public void addRootDir(String name, LocalDateTime creationTime, String owner) {
		instance.setRoot(new APFSDirectory(null, name, creationTime, owner));
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new APFSDirectory(null, "root", LocalDateTime.now(), "owner");
	}
}

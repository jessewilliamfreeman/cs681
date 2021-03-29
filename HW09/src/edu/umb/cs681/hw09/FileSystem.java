package edu.umb.cs681.hw09;

import java.util.LinkedList;

public abstract class FileSystem {
	
	protected String    name;
	protected int       capacity;
	protected int       id;
	
	private LinkedList<FSElement> rootDirs;
	
	protected abstract FSElement createDefaultRoot();
	
	public FSElement initFileSystem(String name, int capacity) {
		rootDirs = new LinkedList<FSElement>();
		this.name = name;
		this.capacity = capacity;
		FSElement root = createDefaultRoot();
		if(root.isDirectory() && 
				capacity >= root.getSize()){
					setRoot(root);
					this.id = root.hashCode();
					return root;
				} else {
					return null;
				}
	}
	
	public LinkedList<FSElement> getRootDirs() {
		return rootDirs;
	}

	protected void setRoot(FSElement root) {
		rootDirs.add(root);
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getCapacity() {
		return capacity;
	}

	protected void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}
		
}

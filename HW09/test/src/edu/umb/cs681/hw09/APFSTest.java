package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class APFSTest {
	
	@Test
	public void initFileSystemTest() {
				
		APFS fs = APFS.getFileSystem();

		fs.initFileSystem("my FS", 200);

		
		LinkedList<FSElement> rootDirs = fs.getRootDirs();
		
		APFSDirectory root = (APFSDirectory) rootDirs.get(0);
		
		
		assertEquals("my FS",  fs.getName());
		assertEquals(200,      fs.getCapacity());
		assertEquals("root",  root.getName());
		assertEquals("owner",  root.getOwner());
		
	}
	
	@Test
	public void addRootDirTest() {
		
		LocalDateTime ld1 = LocalDateTime.of(1990, 12, 20, 9, 20);
		
		APFS fs = APFS.getFileSystem();
		
		fs.initFileSystem("my FS", 200);
		
		fs.addRootDir("root2", ld1, "owner");
		
		LinkedList<FSElement> rootDirs = fs.getRootDirs();
		
		APFSDirectory root2 = (APFSDirectory) rootDirs.get(1);
		
		assertEquals("root2",  root2.getName());
		assertEquals("owner",  root2.getOwner());
		
	}

}

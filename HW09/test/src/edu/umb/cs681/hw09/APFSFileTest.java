package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class APFSFileTest {
	
	private static APFSDirectory root;
	private static APFSDirectory applications;
	private static APFSDirectory home;
	private static APFSDirectory code;
	private static LocalDateTime ld1;
	private static LocalDateTime ld2;
	private static LocalDateTime ld3;
	private static LocalDateTime ld4;
	
	private static int           appAPFSFileSize;
	private static int           homeAPFSFileSize;
	private static int           codeAPFSFileSize;
	private static String        owner;
	
	private static APFSFile APFSFileA;
	
	@BeforeAll
	private static void buildMainDir() {
		
		ld1 = LocalDateTime.of(1990, 12, 20, 9, 20);
		ld2 = LocalDateTime.of(1991, 11, 21, 5, 30);
		ld3 = LocalDateTime.of(1992, 10, 15, 2, 40);
		ld4 = LocalDateTime.of(1994, 10, 15, 2, 40);
		
		appAPFSFileSize  = 100;
		homeAPFSFileSize = 200;
		codeAPFSFileSize = 20000;
		
		owner = "owner";
		
		root = new APFSDirectory(null, "root", ld1, owner);
		
		applications = new APFSDirectory(root, "applications", ld2, owner);
		
		APFSFileA = new APFSFile(applications, "a", appAPFSFileSize, ld3, owner);
		new APFSFile(applications, "b", appAPFSFileSize, ld3, owner);
		
		home = new APFSDirectory(root, "home", ld2, owner);
		
		new APFSFile(home, "c", homeAPFSFileSize, ld3, owner);
		new APFSFile(home, "d", homeAPFSFileSize, ld3, owner);
		
		code = new APFSDirectory(home, "code", ld3, owner);
		
		new APFSFile(code, "e", codeAPFSFileSize, ld4, owner);
		new APFSFile(code, "f", codeAPFSFileSize, ld4, owner);
	}
	
	private static String[] APFSFileToStringArray(APFSFile f){
		ArrayList<String> dirInfo = new ArrayList<String>();
		
		dirInfo.add(f.getName());
		dirInfo.add(Integer.toString(f.getSize()));
		dirInfo.add(f.getCreationTime().toString());
		dirInfo.add(f.getLastModified().toString());
		dirInfo.add(f.getOwner());
		
		FSElement parent = f.getParent();
		
		dirInfo.add(parent != null ? parent.getName() : "null");
		
		String[] result = dirInfo.toArray(new String[dirInfo.size()]);
		
		return result; 
	}

	@Test
	void isAPFSDirectory() {
		boolean expected = false;
		
		assertEquals(expected, APFSFileA.isDirectory());
		
	}
	
	@Test
	public void verifyAPFSFileEqualityA(){
		String[] expected = {
				"a",
				"100",
				ld3.toString(),
				ld3.toString(),
				"owner",
				"applications"
		};
		
		assertArrayEquals(expected, APFSFileToStringArray(APFSFileA));
	}

}

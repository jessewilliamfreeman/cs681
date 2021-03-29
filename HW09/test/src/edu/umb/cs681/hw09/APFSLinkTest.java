package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;
import java.util.ArrayList;

class APFSLinkTest {

	
	private static APFSDirectory     root;
	private static APFSDirectory     applications;
	private static APFSDirectory     home;
	private static APFSDirectory     code;
	private static APFSLink          x;
	private static APFSLink          y;
	private static APFSFile          a;
	private static APFSFile          b;
	private static APFSFile          c;
	private static APFSFile          d;
	private static APFSFile          e;
	private static APFSFile          f;
	private static LocalDateTime     ld1;
	private static LocalDateTime     ld2;
	private static LocalDateTime     ld3;
	private static LocalDateTime     ld4;
	
	private static String            owner;
	
	private static int appAPFSFileSize;
	private static int homeAPFSFileSize;
	private static int codeAPFSFileSize;
	
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
		
		a = new APFSFile(applications, "a", appAPFSFileSize, ld3, owner);
		b = new APFSFile(applications, "b", appAPFSFileSize, ld3, owner);
		
		home = new APFSDirectory(root, "home", ld2, owner);
		
		x = new APFSLink(home, "x", ld3, owner, applications);
		c = new APFSFile(home, "c", homeAPFSFileSize, ld3, owner);
		d = new APFSFile(home, "d", homeAPFSFileSize, ld3, owner);
		
		code = new APFSDirectory(home, "code", ld3, owner);

		y = new APFSLink(code, "y", ld4, owner, b);
		e = new APFSFile(code, "e", codeAPFSFileSize, ld4, owner);
		f = new APFSFile(code, "f", codeAPFSFileSize, ld4, owner);
	}
	
	private static String[] APFSLinkToStringArray(APFSLink l){
		ArrayList<String> APFSLinkInfo = new ArrayList<String>();
		
		APFSLinkInfo.add(l.getName());
		APFSLinkInfo.add(Integer.toString(l.getSize()));
		APFSLinkInfo.add(l.getCreationTime().toString());
		APFSLinkInfo.add(l.getLastModified().toString());
		APFSLinkInfo.add(l.getOwner());
		
		FSElement parent = l.getParent();
		
		APFSLinkInfo.add(parent != null ? parent.getName() : "null");
		
		FSElement target = l.getTarget();
		
		APFSLinkInfo.add(target.getName());
		APFSLinkInfo.add(Integer.toString(target.getSize()));
		APFSLinkInfo.add(target.getCreationTime().toString());
		
		
		String[] result = APFSLinkInfo.toArray(new String[APFSLinkInfo.size()]);
		
		return result; 
	}
	
	@Test
	public void APFSLinkInfoTest1() {
		String[] expected = {
				"x",
				"0",
				ld3.toString(),
				ld3.toString(),
				"owner",
				"home",
				"applications",
				"0",
				ld2.toString()
		};
		
		assertArrayEquals(expected, APFSLinkToStringArray(x));
	}

	@Test
	public void APFSLinkInfoTest2() {
		String[] expected = {
				"y",
				"0",
				ld4.toString(),
				ld4.toString(),
				"owner",
				"code",
				"b",
				Integer.toString(appAPFSFileSize),
				ld3.toString()
		};
		
		assertArrayEquals(expected, APFSLinkToStringArray(y));
	}
	
	@Test
	public void isAPFSDirectoryTest() {
		assertEquals(false, x.isDirectory());
	}

}

package org.lightframework.test.test;

import java.util.List;

public class TestApplication {
	public void test00Success() {
		
	}
	
	public int test01Return1() {
		return 1;
	}
	
	public List test31ReturnList() {
		return null;
	}
	
	public void test51RuntimeException() {
		throw new RuntimeException();
	}
	
	public void test52Exception() throws Exception {
		throw new Exception();
	}
}

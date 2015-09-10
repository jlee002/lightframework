package org.lightframework.test.test;

public class TestApplication {
	public void test00Success() {
		
	}
	
	public int test01Return1() {
		return 1;
	}
	
	public void test11RuntimeException() {
		throw new RuntimeException();
	}
	
	public void test12Exception() throws Exception {
		throw new Exception();
	}
}

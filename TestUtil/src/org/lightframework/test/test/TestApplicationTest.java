package org.lightframework.test.test;

import static org.lightframework.test.check.Assert.assertThat;
import static org.lightframework.test.check.Matchers.is;

import org.lightframework.test.annotation.After;
import org.lightframework.test.annotation.Before;
import org.lightframework.test.annotation.Test;
import org.lightframework.test.runner.TestRunner;

public class TestApplicationTest {
	TestApplication ta = new TestApplication();
	
	@Before
	public void setUp() {
		System.out.println("============================================================");
		System.out.println("@Before running. Should be run before every @Test method");
		System.out.println("------------------------------------------------------------");
	}
	
	@After
	public void tearDown() {
		System.out.println("------------------------------------------------------------");
		System.out.println("@After running. Should be run before every @Test method");
		System.out.println("============================================================");
	}
	
	@Test
	public void test00SuccessTest() {
		ta.test00Success();
	}
	
	@Test
	public void test01Return1Test() {
		assertThat(ta.test01Return1(), is(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void test11RuntimeExceptionTest() {
		ta.test11RuntimeException();
	}
	
	@Test(expected=Exception.class)
	public void test12ExceptionTest() throws Exception {
		ta.test12Exception();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		TestRunner.main(TestApplicationTest.class.getName());
	}
}

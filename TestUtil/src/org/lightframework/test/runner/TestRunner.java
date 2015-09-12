package org.lightframework.test.runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lightframework.test.annotation.After;
import org.lightframework.test.annotation.Before;
import org.lightframework.test.annotation.Test;
import org.lightframework.test.exception.DefaultExpectedThrowable;

public class TestRunner {
	public static TestResult run(String... args) {
		Class<?> clazz = null;
		
		for (String classname: args) {
			try {
				clazz = Class.forName(classname);
				runTest(clazz);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	public static TestResult run(Class<?>... classes) {
		for (Class<?> clazz: classes) {
			runTest(clazz);
		}
		return null;
		
	}
	
	private static void runTest(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		
		List<Method> testMethods = new ArrayList<Method>();
		Method beforeMethod = null;
		Method afterMethod = null;
		
		for (Method method: methods) {
			if (method.isAnnotationPresent(Test.class)) {
				testMethods.add(method);
			} else if (method.isAnnotationPresent(Before.class)) {
				beforeMethod = method;
			} else if (method.isAnnotationPresent(After.class)) {
				afterMethod = method;
			}
		}
		
		Object object = null;
		if (testMethods.size() > 0) {
			try {
				object = clazz.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		
		for (Method method: testMethods) {
			try {
				if (beforeMethod != null) {
					beforeMethod.invoke(object);
				}
				
				Test test = method.getAnnotation(Test.class);
				
				Throwable cause = null;
				try {
					method.invoke(object);
				} catch (Throwable t) {
					cause = t.getCause();
				}
				
				if (cause == null && test.expected() != DefaultExpectedThrowable.class) {
					System.out.println("Exception should have occured");
					// Exception should have occurred
				} else if (cause != null && !test.expected().isInstance(cause)) {
					// Wrong exception
					System.out.println("Wrong exception occurred");
				} else {
					System.out.println("Success");
				}
				
				
				if (afterMethod != null) {
					afterMethod.invoke(object);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

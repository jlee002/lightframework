package org.lightframework.test.runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lightframework.test.annotation.After;
import org.lightframework.test.annotation.Before;
import org.lightframework.test.annotation.Test;

public class TestUnit {
	public static void main(String classname) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
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
				method.invoke(object);
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

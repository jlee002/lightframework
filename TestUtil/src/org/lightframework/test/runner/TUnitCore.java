package org.lightframework.test.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.lightframework.test.annotation.Test;

public class TUnitCore {
	public static void main(String classname) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		Object object = null;
		try {
			object = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Method[] methods = clazz.getDeclaredMethods();
		
		for (Method method: methods) {
			if (method.isAnnotationPresent(Test.class)) {
				Test test = method.getAnnotation(Test.class);
				try {
					method.invoke(object);
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
}

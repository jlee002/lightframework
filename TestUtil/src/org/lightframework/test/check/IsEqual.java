package org.lightframework.test.check;

public class IsEqual<T> implements Matcher<T> {
	private T equalArg = null; 
	
	public IsEqual(T equalArg) {
		this.equalArg = equalArg;
	}
	
	public static <T> Matcher<T> equalTo(T operand) {
		return new IsEqual<T>(operand);
	}

	public boolean matches(Object actualValue) {
		return equalArg.equals(actualValue);
	}
}

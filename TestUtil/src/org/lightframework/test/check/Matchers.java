package org.lightframework.test.check;

public class Matchers {
	public static <T> Matcher<T> equalTo(T operand) {
		return IsEqual.equalTo(operand);
	}

	//public static <T> Matcher<T> is(Matcher<T> matcher) {
	//	return matcher;
	//}

	public static <T> Matcher<T> is(T value) {
		//return is(equalTo(value));
		return IsEqual.equalTo(value);
	}
}

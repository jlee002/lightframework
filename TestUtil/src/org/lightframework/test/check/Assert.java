package org.lightframework.test.check;

import org.lightframework.test.exception.AssertException;

public class Assert {
	public static <T> void assertThat(T actual, Matcher<T> matcher) {
		if (!matcher.matches(actual)) {
			throw new AssertException("Not matched: " + actual);
		}
	}
}

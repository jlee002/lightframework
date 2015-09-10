package org.lightframework.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lightframework.test.exception.DefaultExpectedThrowable;

/**
 * @author Jeonghoon Lee
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	Class<? extends Throwable> expected() default DefaultExpectedThrowable.class;
}

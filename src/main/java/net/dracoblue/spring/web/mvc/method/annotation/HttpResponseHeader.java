package net.dracoblue.spring.web.mvc.method.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpResponseHeader {
	/**
	 *  
	 */
	String name() default "";

	/**
	 * 
	 */
	String value() default "";
	
	/**
	 * 
	 */
	boolean valueExpression() default false;

	/**
	 * 
	 */
	boolean nameExpression() default false;
}

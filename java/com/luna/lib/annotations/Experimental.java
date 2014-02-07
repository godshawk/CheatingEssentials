package com.luna.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as experimental. Any class that has this annotation is to be
 * treated as experimental, and not be expected to work.
 * 
 * @author godshawk
 * 
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface Experimental {
	
}

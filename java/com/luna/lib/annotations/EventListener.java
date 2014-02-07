package com.luna.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.luna.lib.event.EventBase;
import com.luna.lib.event.enums.EnumEventPriority;

/**
 * Tells the EventManager that the given method handles Events.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface EventListener {
	Class< ? extends EventBase > event( );
	
	EnumEventPriority priority( ) default EnumEventPriority.NORMAL;
}

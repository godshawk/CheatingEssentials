package com.luna.lib.event;

/**
 * Base of all events
 * 
 * @author godshawk
 * 
 */
public abstract class EventBase {
	/**
	 * Class the event originated from
	 */
	private final Object	source;
	
	/**
	 * Constructor
	 * 
	 * @param source
	 */
	public EventBase( final Object source ) {
		this.source = source;
	}
	
	/**
	 * Returns the class that this event was called from
	 * 
	 * @return
	 */
	public final Object getSource( ) {
		return source;
	}
}

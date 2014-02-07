package com.luna.lib.event;

/**
 * If an event can be cancelled, ie for things like Freecam, the event would
 * have to extend this class.
 * 
 * @author godshawk
 * 
 */
public abstract class EventCancellable extends EventBase {
	private boolean	isCancelled	= false;
	
	public EventCancellable( final Object source ) {
		super( source );
	}
	
	public void cancel( ) {
		isCancelled = true;
	}
	
	public boolean getCancelled( ) {
		return isCancelled;
	}
}

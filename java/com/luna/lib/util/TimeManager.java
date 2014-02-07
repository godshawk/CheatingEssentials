package com.luna.lib.util;

import java.util.concurrent.TimeUnit;

/**
 * @author Ramisme
 * @since Jul 25, 2013
 */
public final class TimeManager {
	
	/**
	 * The last variable is assigned to the last set time in nanoseconds.
	 */
	private long	last;
	
	/**
	 * Reset the last time.
	 */
	public final synchronized void resetTime( ) {
		last = System.nanoTime( );
	}
	
	/**
	 * Return whether the amount of specified time has elapsed in milliseconds.
	 * 
	 * @param time
	 * @return
	 */
	public final synchronized boolean sleep( final long time ) {
		return sleep( time, TimeUnit.MILLISECONDS );
	}
	
	/**
	 * Return whether the amount of specified time has elapsed in the specified
	 * time unit converted from nanoseconds.
	 * 
	 * @param time
	 * @param timeUnit
	 * @return
	 */
	public synchronized boolean sleep( final long time, final TimeUnit timeUnit ) {
		return timeUnit.convert( last, TimeUnit.NANOSECONDS ) >= time;
	}
	
	/**
	 * Convert rate per second to milliseconds.
	 * 
	 * @param rate
	 */
	public final long convertToMillis( final double rate ) {
		return TimeUnit.MILLISECONDS.convert( ( long ) rate, TimeUnit.SECONDS );
	}
	
}

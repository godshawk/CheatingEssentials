package com.luna.lib.event.enums;

/**
 * EventListeners, how important are you?
 * 
 * @author godshawk
 * 
 */
public enum EnumEventPriority {
	/**
	 * Event is of the <strong>lowest</strong> priority
	 */
	LOWEST,
	
	/**
	 * Event is more important than <code>LOWEST</code>, but still low priority
	 */
	LOW,
	
	/**
	 * Event is middle priority
	 */
	NORMAL,
	
	/**
	 * Event is important
	 */
	HIGH,
	
	/**
	 * Event is the most important
	 */
	HIGHEST;
}

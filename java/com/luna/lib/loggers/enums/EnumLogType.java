package com.luna.lib.loggers.enums;

import com.luna.lib.util.string.StringUtil;

/**
 * An enum of all the log "levels".
 */
public enum EnumLogType {
	
	/**
	 * Information
	 */
	INFO,
	
	/**
	 * Warning
	 */
	WARNING,
	
	/**
	 * Total wipeout
	 */
	FATAL,
	
	/**
	 * Stacktrace
	 */
	TRACE,
	
	/**
	 * Hooking (a) class(es)
	 */
	HOOK,
	
	/**
	 * Scanning, either the JAR or elsewhere
	 */
	SCAN,
	
	/**
	 * Debug info
	 */
	DEBUG,
	
	/**
	 * Woot! Werk'd!
	 */
	SUCCESS,
	
	/**
	 * Just for IO
	 */
	IO,

    /**
     * For Reflection related stuff, obviously
     */
    REFLECTION;
	
	/**
	 * Returns the name of the Enum object capitalized properly; eg. "INFO"
	 * becomes "Info".
	 * 
	 * @return
	 */
	public final String getName( ) {
		return StringUtil.capitalize( name( ) ).trim( );
	}
}

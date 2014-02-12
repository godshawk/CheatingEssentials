package com.luna.ce.log;

import com.luna.lib.loggers.AbstractLogger;
import com.luna.lib.loggers.enums.EnumLogType;

public class CELogger extends AbstractLogger {
	private static CELogger	instance;
	
	@Override
	public void log( final EnumLogType level, final Object data ) {
		System.out.println( String.format( "[CheatingEssentials] [%s] %s", level.getName( ), data ) );
	}
	
	public static CELogger getInstance( ) {
		if( instance == null ) {
			instance = new CELogger( );
		}
		return instance;
	}
}

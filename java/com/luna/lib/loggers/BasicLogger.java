package com.luna.lib.loggers;

import com.luna.lib.loggers.enums.EnumLogType;

public class BasicLogger extends AbstractLogger {
	
	private static final BasicLogger	instance		= new BasicLogger( );
	
	private boolean						debugEnabled	= false;
	
	@Override
	public void log( final EnumLogType level, final Object data ) {
		if( level.equals( EnumLogType.DEBUG ) && !debugEnabled ) {
			// If it's a debug message and the debug option is not enabled, skip
			return;
		}
		System.out.println( String.format( "[%s] %s", level.getName( ), data.toString( ) ) );
	}
	
	public static BasicLogger getInstance( ) {
		return instance;
	}
	
	public void setDebug( final boolean e ) {
		debugEnabled = e;
	}
	
	public boolean getDebug( ) {
		return debugEnabled;
	}
}

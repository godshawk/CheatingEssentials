package com.luna.lib.loggers;

import com.luna.lib.loggers.enums.EnumLogType;

/**
 * The base of all loggers
 */
public abstract class AbstractLogger {
	public void log( final Object data ) {
		this.log( EnumLogType.INFO, data );
	}
	
	public abstract void log( EnumLogType level, Object data );
}

package com.luna.lib.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.luna.lib.loggers.BasicLogger;
import com.luna.lib.loggers.enums.EnumLogType;

/**
 * Various Reflection methods
 * 
 * @author godshawk
 * 
 */
public final class ReflectionHelper {
	public static Field findFieldOfTypeInClass( final Class< ? > source, final Class< ? > type ) {
		for( final Field e : source.getDeclaredFields() ) {
			if( e.getType().equals( type ) ) {
				return e;
			}
		}
		return null;
	}
	
	public static Method getMethodByReturnType( final Class< ? > source, final Class< ? > type ) {
		for( final Method e : source.getDeclaredMethods() ) {
			if( e.getReturnType().isAssignableFrom( type ) ) {
				return e;
			}
		}
		return null;
	}
	
	public static < T extends Object > T callMethod( final Method e ) {
		return callMethod( e, ( Object[ ] ) null );
	}
	
	@SuppressWarnings( "unchecked" )
	public static < T extends Object > T callMethod( final Method e, final Object... params ) {
		
		try {
			final T retVal = ( T ) e.invoke( e.getDeclaringClass(), params );
			return retVal;
		} catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns the Class specified by the given name
	 * 
	 * @param fullName
	 *            - Ie com.luna.inkaria.core.Inkaria;
	 * @return
	 * 
	 * @see {@link Class#getCanonicalName()}
	 */
	public static Class< ? > findClass( final String fullName ) {
		try {
			return Class.forName( fullName );
		} catch( final ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings( "unchecked" )
	public static < T > T getClassType( final T clazz ) {
		return ( T ) clazz.getClass();
	}
	
	/**
	 * Casts <code>source</code> to <code>cast</code>
	 * 
	 * @param source
	 * @param cast
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public static < T > T cast( final T source, final Class< ? > cast ) {
		if( source.getClass().equals( cast ) ) {
			BasicLogger.getInstance().log(
					EnumLogType.WARNING,
					"Source (" + source.toString() + ") is equal to Cast (" + cast.toString()
							+ ") - returning Source." );
			return source;
		}
		return ( T ) cast.cast( source );
	}
}

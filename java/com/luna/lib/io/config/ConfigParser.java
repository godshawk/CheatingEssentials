package com.luna.lib.io.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.luna.lib.io.managers.BufferedIOManager;

/**
 * Used for parsing config files; ie files that use "#" for comments.
 * 
 * @author godshawk
 * 
 */
public final class ConfigParser extends BufferedIOManager {
	private ConfigParser( final File file ) {
		super( file );
	}
	
	public static ConfigParser getInstance( final File file ) {
		return new ConfigParser( file );
	}
	
	@Override
	public synchronized List< String > read( ) {
		final List< String > readLines = new ArrayList< String >( );
		String line;
		
		try {
			while ( ( line = reader.readLine( ) ) != null ) {
				if ( line.startsWith( "#" ) ) {
					continue;
				}
				
				readLines.add( line );
			}
			
			return readLines;
		} catch ( final IOException e ) {
			e.printStackTrace( );
			return null;
		}
	}
}

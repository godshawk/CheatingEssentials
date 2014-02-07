package com.luna.lib.io.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.luna.lib.io.IOManager;

/**
 * Reads/Writes files, using {@link java.io.BufferedReader} and
 * {@link java.io.BufferedWriter}
 * 
 * @author godshawk
 * 
 */
public class BufferedIOManager implements IOManager {
	
	/**
	 * The file that gets read/written from/to.
	 */
	protected final File file;
	
	protected BufferedReader reader;
	protected BufferedWriter writer;
	
	protected BufferedIOManager( final File file ) {
		this.file = file;
	}
	
	public static BufferedIOManager getInstance( final File file ) {
		return new BufferedIOManager( file );
	}
	
	@Override
	public synchronized void setupRead( ) {
		try {
			reader = new BufferedReader( new FileReader( file ) );
		} catch ( final FileNotFoundException e ) {
			e.printStackTrace( );
		}
	}
	
	@Override
	public synchronized void setupWrite( ) {
		try {
			writer = new BufferedWriter( new FileWriter( file ) );
		} catch ( final IOException e ) {
			e.printStackTrace( );
		}
	}
	
	@Override
	public synchronized void closeStream( ) {
		try {
			if ( reader != null ) {
				reader.close( );
			}
		} catch ( final IOException e1 ) {
			e1.printStackTrace( );
		}
		try {
			if ( writer != null ) {
				writer.close( );
			}
		} catch ( final IOException e ) {
			e.printStackTrace( );
		}
	}
	
	@Override
	public synchronized List< String > read( ) {
		final List< String > readLines = new ArrayList< String >( );
		String line;
		
		try {
			while ( ( line = reader.readLine( ) ) != null ) {
				readLines.add( line );
			}
			
			return readLines;
		} catch ( final IOException e ) {
			e.printStackTrace( );
			return null;
		}
	}
	
	@Override
	public synchronized void write( final List< String > data ) {
		for ( final String e : data ) {
			try {
				writer.write( e );
			} catch ( final IOException e1 ) {
				e1.printStackTrace( );
			}
		}
	}
}

package com.luna.lib.io;

import java.util.List;

/**
 * Yes, I did look at other people's code to help.
 * 
 * @author godshawk
 * 
 */
public interface IOManager {
	
	/**
	 * Sets up the input stream
	 */
	void setupRead( );
	
	/**
	 * Sets up the output stream
	 */
	void setupWrite( );
	
	/**
	 * Closes all streams
	 */
	void closeStream( );
	
	/**
	 * Reads all lines from the file, returns them as a list
	 * 
	 * @return
	 */
	List< String > read( );
	
	/**
	 * Writes the list into the file
	 * 
	 * @param data
	 */
	void write( List< String > data );
}

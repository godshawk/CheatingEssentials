package com.luna.lib.io;

/**
 * Implemented by classes that manage config files
 * 
 * @author godshawk
 */
public interface IFileParser {
	void readFile( );
	
	void writeFile( );
	
	void parseLine( String line );
}

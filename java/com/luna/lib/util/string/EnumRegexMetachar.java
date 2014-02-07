package com.luna.lib.util.string;

/**
 * Metacharacters for regexes. No more having to remember them! (Yes, I'm aware
 * that this is pointless.)
 * 
 * @author godshawk
 * 
 */
public enum EnumRegexMetachar {
	/**
	 * Can be any one character. Like ? in *nix.
	 */
	ANY_CHARACTER( "." ),
	
	/**
	 * Any digit; ie [0-9]
	 */
	DIGIT( "\\d" ),
	
	/**
	 * Any NON-digit; ie [^0-9]
	 */
	NON_DIGIT( "\\D" ),
	
	/**
	 * Any whitespace character; ie [ \t\n\x0B\f\r]
	 */
	WHITESPACE( "\\s" ),
	
	/**
	 * Any NON-whitespace; ie [^\s]
	 */
	NON_WHITESPACE( "\\S" ),
	
	/**
	 * Any character that can be part of a word; ie [a-zA-Z_0-9]
	 */
	WORD( "\\w" ),
	
	/**
	 * Any NON-word character; ie [^\w]
	 */
	NON_WORD( "\\W" );
	
	final String metacharacter;
	
	EnumRegexMetachar( final String meta ) {
		metacharacter = meta;
	}
	
	public final String getMetachar( ) {
		return metacharacter;
	}
}

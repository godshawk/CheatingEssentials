package com.luna.lib.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex-related utils.
 * 
 * @author godshawk
 * 
 */
public class RegexUtil {
	/**
	 * Just a basic {@link java.util.regex.Pattern}
	 * 
	 * @see {@link java.util.regex.Pattern}
	 * @see {@link java.util.regex.Pattern#compile(String)}
	 * 
	 * @param regex
	 * @return
	 */
	public static Pattern genPattern( final String regex ) {
		return Pattern.compile( regex );
	}
	
	/**
	 * Generate a {@link java.util.regex.Pattern} with flags.
	 * 
	 * @see {@link java.util.regex.Pattern}
	 * @see {@link java.util.regex.Pattern#compile(String, int)}
	 * 
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static Pattern genPatternWithFlags( final String regex, final int flags ) {
		return Pattern.compile( regex, flags );
	}
	
	/**
	 * Returns a {@link java.util.regex.Matcher}, using a
	 * {@link java.util.regex.Pattern} compiled with the givenregex.
	 * 
	 * @see {@link java.util.regex.Pattern}
	 * @see {@link java.util.regex.Matcher}
	 * 
	 * @param text
	 * @param regex
	 * @return
	 */
	public static Matcher getMatcherForRegex( final String text, final String regex ) {
		return genPattern( regex ).matcher( text );
	}
	
	/**
	 * Returns a {@link java.util.regex.Matcher}, using a
	 * {@link java.util.regex.Pattern} compiled with the given regex and flags.
	 * 
	 * @see {@link java.util.regex.Pattern}
	 * @see {@link java.util.regex.Matcher}
	 * 
	 * @param text
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static Matcher getMatcherForRegexWithFlags( final String text, final String regex, final int flags ) {
		return genPatternWithFlags( regex, flags ).matcher( text );
	}
	
	/**
	 * Returns <code>true</code> if the regex is found.
	 * 
	 * @param in
	 * @param regex
	 * @return
	 */
	public static boolean isInString( final String in, final String regex ) {
		return getMatcherForRegex( in, regex ).find( );
	}
}

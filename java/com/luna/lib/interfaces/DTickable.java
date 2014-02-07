package com.luna.lib.interfaces;

/**
 * Like {@link Tickable}, but this has a <code>delta</code> passed in
 * 
 * @author godshawk
 */
public interface DTickable {
	void tick( int delta );
}

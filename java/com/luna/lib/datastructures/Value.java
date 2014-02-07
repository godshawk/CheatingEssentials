package com.luna.lib.datastructures;

/**
 * Usage example: Sliders in a GUI
 * 
 * @author godshawk
 * 
 */
public class Value {
	
	private final String	name;
	private float			value, minimalValue, maxValue;
	private final float		defaultValue;
	private final float		incrementValue;
	private final boolean	round;
	
	public Value( final String valName, final float defaultVal, final float minVal,
			final float maxVal ) {
		this( valName, defaultVal, minVal, maxVal, -1 );
	}
	
	public Value( final String valName, final float defaultVal, final float minVal,
			final float maxVal, final float incrementVal ) {
		this( valName, defaultVal, minVal, maxVal, incrementVal, true );
	}
	
	public Value( final String valName, final float defaultVal, final float minVal,
			final float maxVal, final float incrementVal, final boolean round ) {
		name = valName;
		value = defaultVal;
		defaultValue = defaultVal;
		minimalValue = minVal;
		maxValue = maxVal;
		incrementValue = incrementVal;
		this.round = round;
	}
	
	public float getValue( ) {
		return value;
	}
	
	public void setValue( final float value ) {
		this.value = round ? Math.round( value ) : value;
	}
	
	public float getMin( ) {
		return minimalValue;
	}
	
	public void setMin( final float min ) {
		minimalValue = min;
	}
	
	public float getMax( ) {
		return maxValue;
	}
	
	public void setMax( final float max ) {
		maxValue = max;
	}
	
	public float getDef( ) {
		return defaultValue;
	}
	
	public float getIncrementValue( ) {
		return incrementValue;
	}
	
	public String getName( ) {
		return name;
	}
	
}

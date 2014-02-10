package com.luna.ce.gui.widget.base;

/**
 * Base of all skins for the client.
 * 
 * @update 6/14/2013: Seperate methods for drawing components individually
 *         added.
 * 
 * @author luna
 * 
 */
public interface ISkin {
	/*
	 * Component methods
	 */
	
	void drawComponent( double x, double y, double width, double height, boolean isOver );
	
	void drawButton( double x, double y, double width, double height, boolean isOver );
	
	void drawLabel( double x, double y, double width, double height, boolean isOver );
	
	void drawRadioButton( double x, double y, double width, double height, boolean isActive );
	
	/*
	 * Window methods
	 */
	
	void drawWindow( double x, double y, double width, double height, boolean isOver );
	
	void drawControls( double x, double y, double width, double height, boolean isOver );
	
	void drawSlider( int x, int y, int width, int height, boolean b );
	
	/*
	 * Other methods
	 */
	
	/**
	 * Returns \u00a7[0-9A-FK-OR]
	 * 
	 * @return
	 */
	int getTextColor( boolean window );
}

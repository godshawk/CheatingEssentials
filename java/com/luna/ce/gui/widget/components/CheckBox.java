package com.luna.ce.gui.widget.components;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

/**
 * Override update() for stuff to happen.
 * 
 * @author godshawk
 * 
 */
public class CheckBox extends Component {
	
	protected boolean	state	= false;
	
	public CheckBox( final String q ) {
		this( q, true );
	}
	
	public CheckBox( final String q, final boolean z ) {
		setText( q );
		state = z;
		setWidth( 15 + getFontRenderer( ).getStringWidth( getText( ) ) );
		setHeight( 14 );
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		// It's entirely possible that the text is dynamic ._.
		setWidth( 15 + getFontRenderer( ).getStringWidth( getText( ) ) );
		skin.drawRadioButton( getX( ), getY( ), getWidth( ), getHeight( ), state );
		getFontRenderer( ).drawString( getText( ), getX( ) + 15, getY( ) + 4, skin.getTextColor( false ) );
	}
	
	/**
	 * Override me!
	 */
	@Override
	public void update( ) {
	}
	
	@Override
	public void drawExtra( ) {
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		playSound( "random.click" );
		state = !state;
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
	}
	
	public boolean getState( ) {
		return state;
	}
	
	public void setState( final boolean e ) {
		state = e;
	}
}

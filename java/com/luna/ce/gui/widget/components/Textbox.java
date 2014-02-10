package com.luna.ce.gui.widget.components;

import org.lwjgl.input.Keyboard;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

/**
 * Created with IntelliJ IDEA. User: audrey Date: 10/20/13 Time: 5:08 PM To
 * change this template use File | Settings | File Templates.
 */
public class Textbox extends Component {
	private boolean	focused	= false;
	
	public Textbox( ) {
		this( "" );
	}
	
	public Textbox( final String string ) {
		setText( string );
		setWidth( 90 );
		setHeight( 12 );
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		final String cursor = focused ? "_" : "";
		setWidth( getFontRenderer( ).getStringWidth( getText( ).concat( cursor ) ) + 2 );
		skin.drawSlider( getX( ), getY( ), getWidth( ), getHeight( ), true );
		getFontRenderer( ).drawString( getText( ).concat( cursor ), getX( ) + 1, getY( ) + 2, 0xffffffff );
	}
	
	@Override
	public void update( ) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}
	
	@Override
	public void drawExtra( ) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		focused = !focused;
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		if( !focused ) {
			return;
		}
		
		switch( key ) {
			case Keyboard.KEY_BACK:
				if( getText( ).length( ) > 0 ) {
					setText( getText( ).substring( 0, getText( ).length( ) - 1 ) );
				}
				break;
			default:
				setText( getText( ) + c );
				break;
		}
	}
}

package com.luna.ce.gui.widget.components;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

/**
 * Just draws a String in a Window
 * 
 * @author luna
 * 
 */
public class Label extends Component {
	
	public Label( final String text ) {
		super( text, ( getFontRenderer( ).getStringWidth( text ) ) + 5, 14 );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		skin.drawLabel( getX( ), getY( ), getX( ) + getWidth( ), getY( ) + getHeight( ), false );
		getFontRenderer( ).drawString( getText( ), getX( ) + 3, getY( ) + 3, skin.getTextColor( false ) );
	}
	
	@Override
	public void update( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void drawExtra( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
	
}

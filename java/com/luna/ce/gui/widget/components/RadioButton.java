package com.luna.ce.gui.widget.components;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

/**
 * It's a radio button...
 * 
 * @author luna
 * 
 */
public class RadioButton extends Component {
	/**
	 * Will be true if this radio button is selected.
	 */
	private boolean	selected	= false;
	
	public RadioButton( final String text ) {
		super( text, ( getFontRenderer( ).getStringWidth( text ) ) + 6, 14 );
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		skin.drawRadioButton( getX( ), getY( ), getX( ) + getWidth( ), getY( ) + getHeight( ), selected
				|| mouseOver( ) );
		getFontRenderer( ).drawString( getText( ), getX( ) + 18, getY( ) + 4, skin.getTextColor( false ) );
	}
	
	@Override
	public void update( ) {
		
	}
	
	@Override
	public void drawExtra( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		playSound( "random.click" );
		resetOtherButtons( );
		setSelected( true );
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getSelected( ) {
		return selected;
	}
	
	public void setSelected( final boolean e ) {
		selected = e;
	}
	
	protected void resetOtherButtons( ) {
		try {
			for( final Component e : getParent( ).getComponentList( ) ) {
				if( e instanceof RadioButton ) {
					if( ( ( RadioButton ) e ).getSelected( ) ) {
						( ( RadioButton ) e ).setSelected( false );
					}
				}
			}
		} catch( final NullPointerException e ) {
			e.printStackTrace( );
		}
	}
}

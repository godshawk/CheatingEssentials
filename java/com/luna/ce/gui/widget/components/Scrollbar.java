package com.luna.ce.gui.widget.components;

import java.awt.Point;

import org.lwjgl.input.Mouse;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

public class Scrollbar extends Component {
	private float	curval		= 0;
	
	private boolean	dragging	= false;
	
	public Scrollbar( ) {
		this( 8, 90, 0.0F, 10.0F );
	}
	
	private Scrollbar( final int w, final int h, final float min, final float max ) {
		super( );
	}
	
	public float getCurval( ) {
		return curval;
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		setWidth( 8 );
		setHeight( getParent( ).getWindowHeight( ) + getWidth( )
				+ ( getParent( ).getSpacer( ) ? 0 : -getParent( ).getSpacerHeight( ) ) );
		
		skin.drawSlider( getX( ), getY( ), getWidth( ), getHeight( ) + ( getWidth( ) / 2 ), false );
		float sx = ( getY( ) + ( ( curval ) * getHeight( ) ) ) - ( getWidth( ) / 2 );
		float sxw = getWidth( );
		if( sxw > ( ( getY( ) + getHeight( ) ) ) ) {
			sxw = ( getY( ) + getHeight( ) );
		}
		if( sx < getY( ) ) {
			sx = getY( );
		}
		skin.drawSlider( getX( ), ( int ) sx, getWidth( ), ( int ) sxw, true );
	}
	
	@Override
	public void update( ) {
		
		/*
		 * for (final Window e : Inkaria.getInstance().getGui().getWindows()) {
		 * if (e.getDragging()) { dragging = false; } }
		 */
		
		if( !Mouse.isButtonDown( 0 ) && dragging ) {
			dragging = false;
		}
		if( dragging ) {
			if( !mouseOver( ) ) {
				dragging = false;
				return;
			} else {
				final Point m = calculateMouseLocation( );
				mouseClicked( getParent( ), m.x, m.y, 0 );
			}
		} else {
			if( mouseOver( ) ) {
				if( Mouse.isButtonDown( 0 ) ) {
					final Point m = calculateMouseLocation( );
					mouseClicked( getParent( ), m.x, m.y, 0 );
				}
			} else {
				dragging = false;
				return;
			}
		}
		
		getParent( ).setScrollOffset( curval );
	}
	
	@Override
	public void drawExtra( ) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings( "unused" )
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		if( !getParent( ).getDragging( ) ) {
			
			// How many windows are currently dragging
			final int count = 0;
			
			// Not unused!
			if( count > 0 ) {
				dragging = false;
				return;
			}
			
			final Point q = new Point( mouseX, mouseY );
			if( mouseOver( ) ) {
				dragging = true;
				curval = ( float ) ( q.y - getY( ) ) / ( float ) getHeight( );
				if( curval < 0.04 ) {
					curval = 0;
				}
				if( curval > 0.96 ) {
					curval = 1;
				}
			} else {
				dragging = false;
			}
		}
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
}

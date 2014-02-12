package com.luna.ce.gui.widget.components;

import org.lwjgl.input.Keyboard;

import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.module.Module;

public class KeyButton extends Button {
	
	private final Module	module;
	
	private boolean			enabled	= false;
	
	public KeyButton( final Module m ) {
		super( m.getName( ), m );
		module = m;
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		skin.drawButton( getX( ), getY( ), getWidth( ), getHeight( ), enabled );
		setText( ( enabled ? "\u00a7a" : "\u00a7r" ) + module.getName( ) + " - "
				+ ( module.getKey( ) > 0 ? Keyboard.getKeyName( module.getKey( ) ) : "???" ) );
		setWidth( getFontRenderer( ).getStringWidth( getText( ) ) + 5 );
		getFontRenderer( ).drawString( getText( ), getX( ) + 3, getY( ) + 3, skin.getTextColor( false ) );
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		playSound( "random.click" );
		enabled = !enabled;
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		if( enabled ) {
			enabled = false;
			if( key == Keyboard.KEY_BACK ) {
				module.setKey( -1 );
				return;
			}
			module.setKey( key );
		}
	}
}

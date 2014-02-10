package com.luna.ce.gui.widget.components;

import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.gui.widget.skin.SkinCE;

/**
 * Used for changing the GUI skin/theme
 * 
 * @author godshawk
 * 
 */
public class SkinButton extends RadioButton {
	private final ISkin	skin;
	
	public SkinButton( final String text ) {
		this( text, new SkinCE( ) );
	}
	
	public SkinButton( final String text, final ISkin change ) {
		super( text );
		skin = change;
		
		/*
		 * if (change.equals(Inkaria.retrieveDefaultSkin())) {
		 * resetOtherButtons(); setSelected(true); }
		 */
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		super.mouseClicked( window, mouseX, mouseY, button );
		if( getParent( ).getSkin( ) != skin ) {
			/*
			 * for (final Window e :
			 * Inkaria.getInstance().getGui().getWindows()) { e.setSkin(skin); }
			 */
		}
	}
	
	public ISkin getSkin( ) {
		return skin;
	}
}

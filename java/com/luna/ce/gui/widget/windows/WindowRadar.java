package com.luna.ce.gui.widget.windows;

import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.gui.widget.components.Radar;
import com.luna.ce.gui.widget.skin.SkinCE;

public class WindowRadar extends Window {
	
	public WindowRadar( final int x, final int y ) {
		super( "Radar", new SkinCE( ), x, y, 0, 0, true );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addComponents( ) {
		addChild( new Radar( ) );
	}
	
}

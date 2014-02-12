package com.luna.ce.gui.widget.windows;

import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.gui.widget.components.KeyButton;
import com.luna.ce.gui.widget.skin.SkinCE;
import com.luna.ce.manager.ManagerModule;
import com.luna.ce.module.Module;

public class WindowKeybinds extends Window {
	public WindowKeybinds( final int x, final int y ) {
		super( "Keybinds", new SkinCE( ), x, y, 0, 0, true );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addComponents( ) {
		for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
			addChild( new KeyButton( e ) );
		}
	}
}

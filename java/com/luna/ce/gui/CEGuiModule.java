package com.luna.ce.gui;

import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.gui.widget.gui.WindowMenuBase;
import com.luna.ce.gui.widget.windows.WindowKeybinds;
import com.luna.ce.gui.widget.windows.WindowModule;
import com.luna.ce.gui.widget.windows.WindowRadar;
import com.luna.ce.module.EnumModuleType;

public class CEGuiModule extends WindowMenuBase {
	private int	yOffset	= 0;
	
	public CEGuiModule( ) {
		for( final EnumModuleType e : EnumModuleType.values( ) ) {
			addAWindow( new WindowModule( e, 2, yOffset ) );
		}
		addAWindow( new WindowRadar( 2, yOffset ) );
		addAWindow( new WindowKeybinds( 2, yOffset ) );
	}
	
	private void addAWindow( final Window w ) {
		super.addWindow( w );
		yOffset += 15;
	}
}

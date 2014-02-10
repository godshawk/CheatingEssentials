package com.luna.ce.gui.widget.windows;

import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.gui.widget.components.Button;
import com.luna.ce.gui.widget.skin.SkinCE;
import com.luna.ce.manager.ManagerModule;
import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;

public class WindowModule extends Window {
	private final EnumModuleType	type;
	
	public WindowModule( final EnumModuleType type, final int x, final int y ) {
		super( type.getRealName( ), new SkinCE( ), x, y, 0, 0, true );
		this.type = type;
		addComponentsLater( );
	}
	
	@Override
	public void addComponents( ) {
	}
	
	private void addComponentsLater( ) {
		for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
			if( e.getType( ).equals( type ) ) {
				final Button b = new Button( e.getName( ), e );
				b.setParent( this );
				addChild( b );
			}
		}
	}
}

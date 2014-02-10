package com.luna.ce.gui.widget.components;

import com.luna.ce.gui.GuiUtils;
import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.module.Module;

/**
 * Button for toggling Modules
 * 
 * @author luna
 * 
 */
public class Button extends Component {
	private final Module	module;
	
	public Button( final String text, final Module m ) {
		super( text, ( getFontRenderer( ).getStringWidth( text ) ) + 6, 14 );
		module = m;
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		skin.drawButton( getX( ), getY( ), getWidth( ), getHeight( ), mouseOver( ) || module.getActive( ) );
		getFontRenderer( ).drawString( ( module.getActive( ) ? "\u00a7a" : "" ) + getText( ), getX( ) + 3,
				getY( ) + 3, skin.getTextColor( false ) );
		
		if( mouseOver( ) ) {
			GuiUtils.disableScissoring( );
			renderTooltip( );
			GuiUtils.enableScissoring( );
		}
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
		module.toggle( );
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
	
	private void renderTooltip( ) {
		final int h = GuiUtils.getHeight( );
		final int w = getFontRenderer( ).getStringWidth( module.getDesc( ) );
		
		GuiUtils.drawRect( 0, h - 11, w + 3, h, 0x77000000 );
		
		getFontRenderer( ).drawString( module.getDesc( ), 1, h - 10, 0xffffffff );
	}
}

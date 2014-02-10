package com.luna.ce.gui.widget.skin;

import com.luna.ce.gui.GuiUtils;
import com.luna.ce.gui.widget.base.ISkin;

public class SkinCE implements ISkin {
	@Override
	public void drawComponent( final double x, final double y, final double width, final double height,
			final boolean isOver ) {
		GuiUtils.drawGradientBorderedRect( ( int ) x, ( int ) y, ( int ) width, ( int ) height, 1.0F,
				0xffffffff, 0xff777777, isOver ? 0xff008888 : 0xff888888 );
	}
	
	@Override
	public void drawWindow( final double x, final double y, final double width, final double height,
			final boolean isOver ) {
		GuiUtils.drawGradientRect( ( int ) x, ( int ) y, ( int ) ( width ), ( int ) ( height ), 0x98989898,
				0x98787878 );
	}
	
	@Override
	public void drawControls( final double x, final double y, final double width, final double height,
			final boolean isOver ) {
		GuiUtils.drawRect( ( int ) x, ( int ) y, ( int ) ( width ), ( int ) ( height ), isOver ? 0x77000000
				: 0x33000000 );
	}
	
	@Override
	public void drawButton( final double x, final double y, final double width, final double height,
			final boolean isOver ) {
		GuiUtils.drawRect( ( int ) x, ( int ) y, ( int ) width, ( int ) height, isOver ? 0x77000000 : 0x0 );
	}
	
	@Override
	public void drawLabel( final double x, final double y, final double width, final double height,
			final boolean isOver ) {
		// TODO If you want Labels to have an outline or something
	}
	
	@Override
	public void drawRadioButton( final double x, final double y, final double width, final double height,
			final boolean isActive ) {
		GuiUtils.drawRect( ( 2 ), ( 2 ), ( 12 ), ( 12 ), isActive ? 0x77000000 : 0x0 );
	}
	
	@Override
	public void drawSlider( final int x, final int y, final int width, final int height, final boolean b ) {
		if( b ) {
			GuiUtils.drawRect( x, y, width, height, 0xaa000000 );
		} else if( !b ) {
			GuiUtils.drawRect( x, y, width, height, 0x77000000 );
		}
	}
	
	@Override
	public int getTextColor( final boolean window ) {
		// TODO Auto-generated method stub
		return 0xffffff;
	}
}

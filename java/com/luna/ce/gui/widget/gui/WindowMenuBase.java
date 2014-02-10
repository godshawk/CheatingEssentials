package com.luna.ce.gui.widget.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import com.luna.ce.gui.widget.base.Window;

/**
 * Created with IntelliJ IDEA. User: audrey Date: 10/13/13 Time: 4:43 PM To
 * change this template use File | Settings | File Templates.
 */
public abstract class WindowMenuBase extends GuiScreen {
	private final ArrayList< Window >	windowsList	= new ArrayList<>( );
	
	@Override
	public void initGui( ) {
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	public void onGuiClosed( ) {
		Keyboard.enableRepeatEvents( false );
		super.onGuiClosed( );
	}
	
	@Override
	public void drawScreen( final int par1, final int par2, final float par3 ) {
		renderWindows( par1, par2 );
		super.drawScreen( par1, par2, par3 );
	}
	
	private void renderWindows( final int par1, final int par2 ) {
		try {
			synchronized( windowsList ) {
				for( int i = windowsList.size( ) - 1; i >= 0; i-- ) {
					final Window window = windowsList.get( i );
					window.drawWindow( par1, par2 );
				}
			}
		} catch( final Exception e ) {
			e.printStackTrace( );
		}
	}
	
	private void moveToFrontOfList( final Window window ) {
		synchronized( windowsList ) {
			windowsList.remove( window );
			windowsList.add( 0, window );
		}
	}
	
	private boolean isInFrontOfList( final Window window ) {
		synchronized( windowsList ) {
			return windowsList.get( 0 ) == window;
		}
	}
	
	public ArrayList< Window > getWindows( ) {
		synchronized( windowsList ) {
			return windowsList;
		}
	}
	
	@Override
	protected void keyTyped( final char par1, final int par2 ) {
		synchronized( windowsList ) {
			for( final Window window : windowsList ) {
				if( isInFrontOfList( window ) ) {
					window.keyTyped( par2, par1 );
					break;
				}
			}
		}
		super.keyTyped( par1, par2 );
	}
	
	public void addWindow( final Window window ) {
		synchronized( windowsList ) {
			if( windowsList.contains( window ) ) {
				return;
			}
			windowsList.add( window );
		}
	}
	
	public void removeWindow( final Window window ) {
		synchronized( windowsList ) {
			if( !windowsList.contains( window ) ) {
				return;
			}
			windowsList.remove( window );
		}
	}
	
	@Override
	protected void mouseClicked( final int par1, final int par2, final int par3 ) {
		synchronized( windowsList ) {
			for( final Window window : windowsList ) {
				if( window.mouseClicked( par1, par2, par3 ) ) {
					if( !isInFrontOfList( window ) ) {
						moveToFrontOfList( window );
					}
					break;
				}
			}
		}
		
		super.mouseClicked( par1, par2, par3 );
	}
}

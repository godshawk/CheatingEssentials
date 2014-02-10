package com.luna.ce.forge;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ServerChatEvent;

import org.lwjgl.input.Keyboard;

import com.luna.ce.gui.widget.base.Window;
import com.luna.ce.log.CELogger;
import com.luna.ce.manager.ManagerCommand;
import com.luna.ce.manager.ManagerModule;
import com.luna.ce.module.Module;
import com.luna.ce.module.classes.ModuleGui;
import com.luna.lib.loggers.enums.EnumLogType;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ForgeEventManager {
	private final boolean[ ]	keyStates	= new boolean[ 256 ];
	
	public ForgeEventManager( ) {
		CELogger.getInstance( ).log( "Setting up Forge event stuff..." );
	}
	
	@SubscribeEvent
	public void onServerTick( final TickEvent.ServerTickEvent ev ) {
		if( Minecraft.getMinecraft( ).theWorld != null ) {
			for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
				if( e.getActive( ) ) {
					e.onWorldTick( );
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onWorldRender( final RenderWorldLastEvent ev ) {
		if( Minecraft.getMinecraft( ).theWorld != null ) {
			for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
				if( e.getActive( ) ) {
					e.onWorldRender( );
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onClientTick( final TickEvent.ClientTickEvent ev ) {
		if( Minecraft.getMinecraft( ).theWorld != null ) {
			for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
				if( checkKey( e.getKey( ) ) ) {
					CELogger.getInstance( ).log( EnumLogType.DEBUG,
							"Found Module " + e.getName( ) + " for key " + e.getKey( ) );
					e.toggle( );
					return;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onChatSend( final ServerChatEvent ev ) {
		ManagerCommand.getInstance( ).parseCommands( ev );
	}
	
	@SubscribeEvent
	public void onGuiRender( final RenderGameOverlayEvent.Chat ev ) {
		if( Minecraft.getMinecraft( ).theWorld != null ) {
			if( Minecraft.getMinecraft( ).currentScreen == null ) {
				for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
					if( e.getActive( ) ) {
						e.onGuiRender( );
					}
				}
				for( final Window e : ManagerModule.getInstance( ).getModuleByClass( ModuleGui.class )
						.getGui( ).getWindows( ) ) {
					// if( e.getVisible( ) ) {
					if( e.getPinned( ) ) {
						// final Point p = calculateMouseLocation( );
						e.drawWindow( 0, 0 );
					}
					// }
				}
			}
		}
	}
	
	private boolean checkKey( final int key ) {
		if( Minecraft.getMinecraft( ).currentScreen != null ) {
			return false;
		}
		
		try {
			if( Keyboard.getEventKey( ) > -1 ) {
				if( Keyboard.isKeyDown( key ) != keyStates[ key ] ) {
					return keyStates[ key ] = !keyStates[ key ];
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch( final IndexOutOfBoundsException e ) {
			// Don't understand why this happens, but it does. =|
			// e.printStackTrace( );
			return false;
		}
	}
}

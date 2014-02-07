package com.luna.ce.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.module.Module;

public class ManagerCommand {
	private static ManagerCommand	instance;
	
	public ManagerCommand( ) {
		
	}
	
	public static ManagerCommand getInstance( ) {
		if( instance == null ) {
			instance = new ManagerCommand( );
		}
		return instance;
	}
	
	public void parseCommands( final ServerChatEvent ev ) {
		if( ev.message.startsWith( CheatingEssentials.getInstance( ).getCommandPrefix( ) ) ) {
			ev.setCanceled( true );
		}
		final String[ ] args = ev.message.split( " " );
		parseCommand( args );
	}
	
	private void parseCommand( final String[ ] args ) {
		if( parseEmbeddedCommands( args ) ) {
			return;
		}
		
		for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
			if( args[ 0 ].toLowerCase( ).equals( e.getName( ).replaceAll( " ", "" ).toLowerCase( ) ) ) {
				e.onCommand( args );
			}
		}
	}
	
	private boolean parseEmbeddedCommands( final String[ ] args ) {
		switch( args[ 0 ] ) {
			case "help":
				
				return true;
			default:
				return false;
		}
	}
	
	private void addChatMessage( final String... message ) {
		for( final String e : message ) {
			Minecraft.getMinecraft( ).thePlayer.addChatMessage( new ChatComponentText( e ) );
		}
	}
}

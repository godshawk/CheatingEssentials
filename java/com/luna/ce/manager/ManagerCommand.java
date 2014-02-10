package com.luna.ce.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

import org.lwjgl.input.Keyboard;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.log.CELogger;
import com.luna.ce.module.Module;
import com.luna.lib.loggers.enums.EnumLogType;

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
		final String[ ] args = ev.message.substring( 1 ).split( " " );
		parseCommand( args );
	}
	
	private void parseCommand( final String[ ] args ) {
		if( parseEmbeddedCommands( args ) ) {
			return;
		}
		
		for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
			CELogger.getInstance( ).log( EnumLogType.DEBUG,
					"Name: " + e.getName( ).replaceAll( " ", "" ).toLowerCase( ) + ", args[0]: " + args[ 0 ] );
			if( args[ 0 ].toLowerCase( ).equals( e.getName( ).replaceAll( " ", "" ).toLowerCase( ) ) ) {
				e.onCommand( args );
				return;
			}
		}
	}
	
	private boolean parseEmbeddedCommands( final String[ ] args ) {
		switch( args[ 0 ] ) {
			case "help":
				if( args.length == 1 ) {
					addChatMessage( String.format(
							"Sorry, but a generic %shelp%s command is not supported yet :(",
							CheatingEssentials.getInstance( ).getChatColor( 'c' ), CheatingEssentials
									.getInstance( ).getChatColor( 'r' ) ) );
				} else if( args.length > 2 ) {
					addChatMessage( String.format( "Unrecognized arguments for %shelp%s!", CheatingEssentials
							.getInstance( ).getChatColor( 'c' ), CheatingEssentials.getInstance( )
							.getChatColor( 'r' ) ) );
				} else {
					String[ ] halp = new String[ ] { };
					Module beingHelped = null;
					for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
						if( e.getName( ).replaceAll( " ", "" ).toLowerCase( )
								.equals( args[ 1 ].toLowerCase( ) ) ) {
							halp = e.getHelp( );
							beingHelped = e;
						}
					}
					if( halp.length > 0 ) {
						for( int i = 0; i < halp.length; i++ ) {
							halp[ i ] = String.format( "[%s%s%s] %s", CheatingEssentials.getInstance( )
									.getChatColor( 'a' ), beingHelped.getName( ), CheatingEssentials
									.getInstance( ).getChatColor( 'r' ), halp[ i ] );
						}
						addChatMessage( halp );
					} else {
						addChatMessage( String.format( "Unknown module: %s%s%s!", CheatingEssentials
								.getInstance( ).getChatColor( 'c' ), args[ 1 ], CheatingEssentials
								.getInstance( ).getChatColor( 'r' ) ) );
					}
				}
				return true;
			case "bind":
				if( args.length < 2 ) {
					addChatMessage( String.format( "Sorry, but %sbind%s requires more arguments!",
							CheatingEssentials.getInstance( ).getChatColor( 'c' ), CheatingEssentials
									.getInstance( ).getChatColor( 'r' ) ), String.format(
							"Usage: %sbind%s %s<module> [key]%s", CheatingEssentials.getInstance( )
									.getChatColor( 'c' ), CheatingEssentials.getInstance( )
									.getChatColor( 'r' ), CheatingEssentials.getInstance( )
									.getChatColor( 'a' ), CheatingEssentials.getInstance( )
									.getChatColor( 'r' ) ),
							"Hint: Modules that have spaces in their name are used here as one word" );
					return true;
				} else if( args.length == 2 ) {
					final Module m = ManagerModule.getInstance( ).getModuleByName( args[ 1 ] );
					String suchKey = "";
					try {
						suchKey = Keyboard.getKeyName( m.getKey( ) );
					} catch( final Exception e ) {
						suchKey = "NONE";
					}
					addChatMessage( String.format( "Current key for %s%s%s: %s%s%s", CheatingEssentials
							.getInstance( ).getChatColor( 'c' ), m.getName( ), CheatingEssentials
							.getInstance( ).getChatColor( 'r' ), CheatingEssentials.getInstance( )
							.getChatColor( 'a' ), suchKey,
							CheatingEssentials.getInstance( ).getChatColor( 'r' ) ) );
				} else if( args.length == 3 ) {
					final Module m = ManagerModule.getInstance( ).getModuleByName( args[ 1 ] );
					m.setKey( Keyboard.getKeyIndex( args[ 2 ] ) );
					addChatMessage( String.format( "Bound %s%s%s to %s%s%s", CheatingEssentials.getInstance( )
							.getChatColor( 'c' ), m.getName( ), CheatingEssentials.getInstance( )
							.getChatColor( 'r' ), CheatingEssentials.getInstance( ).getChatColor( 'a' ),
							Keyboard.getKeyName( m.getKey( ) ), CheatingEssentials.getInstance( )
									.getChatColor( 'r' ) ) );
				} else {
					addChatMessage( String.format( "Too many arguments for %sbind%s!" ), CheatingEssentials
							.getInstance( ).getChatColor( 'c' ), CheatingEssentials.getInstance( )
							.getChatColor( 'r' ) );
				}
				return true;
			default:
				return false;
		}
	}
	
	private void addChatMessage( final String... message ) {
		for( final String e : message ) {
			Minecraft.getMinecraft( ).thePlayer.addChatMessage( new ChatComponentText( String.format(
					"[CE] %s", e ) ) );
		}
	}
}

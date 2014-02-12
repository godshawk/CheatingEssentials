package com.luna.ce.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.StringUtils;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.manager.ManagerCommand;
import com.luna.ce.manager.ManagerModule;
import com.luna.ce.module.Module;

public class CommandHelp extends ACommand {
	/**
	 * Max commands/page. Each page is 7 lines, formatted as
	 * 
	 * <pre>
	 * --------HEADER--------
	 * Command - Help
	 * Command - Help
	 * Command - Help
	 * Command - Help
	 * Command - Halp
	 * --------FOOTER--------
	 * </pre>
	 */
	private final int	HELP_MAX_SIZE	= 5;
	
	public CommandHelp( ) {
		super( "help" );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCommand( final String[ ] args ) {
		if( args.length > 2 ) {
			addChatMessage( String
					.format( "Too many arguments for %shelp%s!", CheatingEssentials.getInstance( )
							.getChatColor( 'c' ), CheatingEssentials.getInstance( ).getChatColor( 'r' ) ) );
			return;
		}
		try {
			for( final String e : createHelpPage( Integer.parseInt( args[ 1 ] ) ) ) {
				addChatMessage( e );
			}
		} catch( final Exception e ) {
			// e.printStackTrace( );
			try {
				final Module m = ManagerModule.getInstance( ).getModuleByName( args[ 1 ] );
				final String[ ] halp = m.getHelp( );
				for( int i = 0; i < halp.length; i++ ) {
					halp[ i ] = String.format( "[%s%s%s] %s",
							CheatingEssentials.getInstance( ).getChatColor( 'a' ), m.getName( ),
							CheatingEssentials.getInstance( ).getChatColor( 'r' ), halp[ i ] );
				}
				addChatMessage( halp );
			} catch( final Exception f ) {
				// f.printStackTrace( );
				addChatMessage( String
						.format( "Invalid command: %s%s%s",
								CheatingEssentials.getInstance( ).getChatColor( 'c' ), args[ 0 ],
								CheatingEssentials.getInstance( ).getChatColor( 'r' ) ) );
			}
		}
	}
	
	private List< String > createHelpPage( int p ) {
		final List< String > dump = ManagerCommand.getInstance( ).dumpCommands( );
		final int pageCount = Math.round( dump.size( ) / HELP_MAX_SIZE );
		
		if( p > pageCount ) {
			p = pageCount;
		}
		
		final List< String > page = new ArrayList<>( );
		page.add( getHeader( p ) );
		for( int i = p * HELP_MAX_SIZE; i < ( ( p * HELP_MAX_SIZE ) + HELP_MAX_SIZE ); i++ ) {
			if( i >= dump.size( ) ) {
				break;
			}
			page.add( dump.get( i ) );
		}
		page.add( getFooter( p ) );
		
		return page;
	}
	
	private String getHeader( final int p ) {
		return String.format( "%s--------%sHELP %s%s%s--------%s", CheatingEssentials.getInstance( )
				.getChatColor( '6' ), CheatingEssentials.getInstance( ).getChatColor( 'c' ), p,
				CheatingEssentials.getInstance( ).getChatColor( 'a' ), CheatingEssentials.getInstance( )
						.getChatColor( '6' ), CheatingEssentials.getInstance( ).getChatColor( 'r' ) );
	}
	
	private String getFooter( final int p ) {
		String footer = "";
		for( int i = 0; i < StringUtils.stripControlCodes( getHeader( p ) ).length( ); i++ ) {
			footer = footer + "-";
		}
		return footer;
	}
	
	@Override
	public String getSyntax( ) {
		return "help [commmand/page]";
	}
}

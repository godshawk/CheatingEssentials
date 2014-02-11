package com.luna.ce.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.log.CELogger;
import com.luna.ce.manager.ManagerModule;
import com.luna.ce.module.Module;
import com.luna.lib.io.config.ConfigParser;
import com.luna.lib.loggers.enums.EnumLogType;

public class Config {
	private static final File		moduleFile					= new File( String.format(
																		"%s%smodules.cheat",
																		CheatingEssentials.getInstance( )
																				.getDataDir( ),
																		File.separator ) );
	private static boolean			initialized					= false;
	private static Config			instance;
	private static final boolean	FORCE_INITIAL_FILE_RECREATE	= false;
	private static final String		SEP_CHAR					= ";";
	
	private final ConfigParser		ioModule;
	
	private Config( ) {
		ioModule = ConfigParser.getInstance( moduleFile );
	}
	
	public static Config getInstance( ) {
		if( !initialized ) {
			CELogger.getInstance( ).log( "Initializing config singleton..." );
			instance = new Config( );
			initialize( );
		}
		return instance;
	}
	
	private static void initialize( ) {
		if( !moduleFile.exists( ) || FORCE_INITIAL_FILE_RECREATE ) {
			try {
				CELogger.getInstance( ).log( EnumLogType.IO,
						"Creating module file because it was not found..." );
				moduleFile.createNewFile( );
				instance.saveModuleConfig( );
			} catch( final IOException e ) {
				e.printStackTrace( );
			}
		}
		instance.loadModuleConfig( );
		initialized = true;
	}
	
	private void createModuleFileComments( final List< String > moduleInfo ) {
		moduleInfo.add( "# Format:\n" );
		moduleInfo.add( "# module_name;module_key;module_state\n" );
		moduleInfo.add( "# \n" );
		moduleInfo.add( "# The state and keys can be manually edited, \n" );
		moduleInfo.add( "# just refer to the LWJGL Keyboard documentation.\n" );
	}
	
	private void recreate( final File f ) {
		if( f.exists( ) ) {
			f.delete( );
		}
		try {
			f.createNewFile( );
		} catch( final IOException e ) {
			CELogger.getInstance( ).log( EnumLogType.WARNING,
					String.format( "Failed to recreate file '%s', this is VERY bad.", f.getName( ) ) );
			e.printStackTrace( );
		}
	}
	
	public void saveModuleConfig( ) {
		recreate( moduleFile );
		final List< String > moduleInfo = new ArrayList<>( );
		createModuleFileComments( moduleInfo );
		for( final Module e : ManagerModule.getInstance( ).getModules( ) ) {
			moduleInfo.add( String.format( "%s%s%d%s%b\n", e.getName( ).replaceAll( " ", "" ).toLowerCase( ),
					SEP_CHAR, e.getKey( ), SEP_CHAR, e.getActive( ) ) );
		}
		ioModule.setupWrite( );
		ioModule.write( moduleInfo );
		ioModule.closeStream( );
	}
	
	public void loadModuleConfig( ) {
		ioModule.setupRead( );
		final List< String > configLines = ioModule.read( );
		ioModule.closeStream( );
		
		for( final String e : configLines ) {
			final String[ ] args = e.split( SEP_CHAR );
			CELogger.getInstance( ).log( String.format( "Searching for module %s...", args[ 0 ] ) );
			final Module m = ManagerModule.getInstance( ).getModuleByName( args[ 0 ] );
			CELogger.getInstance( ).log( String.format( "Updating keybind for %s...", args[ 0 ] ) );
			m.setKey( Integer.parseInt( args[ 1 ] ) );
			if( Boolean.parseBoolean( args[ 2 ] ) ) {
				CELogger.getInstance( ).log( String.format( "Toggling %s...", args[ 0 ] ) );
				m.toggle( );
			}
		}
	}
}

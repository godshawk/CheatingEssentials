package com.luna.ce.api;

import java.util.HashSet;

import com.luna.ce.module.Module;

public final class APIModuleSetup {
	private static final HashSet< Module >	setupModules	= new HashSet<>( );
	
	public static void addModuleToSetupQueue( final Module mod ) {
		synchronized( setupModules ) {
			setupModules.add( mod );
		}
	}
	
	public static void setupModules( ) {
		synchronized( setupModules ) {
			for( final Module e : setupModules ) {
				e.initializeLater( );
			}
		}
	}
}

package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleAutoRespawn extends Module {
	public ModuleAutoRespawn( ) {
		super( "AutoRespawn", "Automagically respawns the player", EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		if( getPlayer( ).isDead ) {
			getPlayer( ).respawnPlayer( );
		}
	}
}

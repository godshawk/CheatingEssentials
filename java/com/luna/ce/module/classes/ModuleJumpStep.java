package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleJumpStep extends Module {
	
	public ModuleJumpStep( ) {
		super( "JumpStep", "\"Step\" up blocks by jumping", EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		if( shouldJump( ) ) {
			getPlayer( ).jump( );
		}
	}
	
	private boolean shouldJump( ) {
		return !getPlayer( ).isOnLadder( ) && getPlayer( ).onGround && getPlayer( ).isCollidedHorizontally;
	}
}

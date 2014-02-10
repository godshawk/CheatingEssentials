package com.luna.ce.module.classes;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleFly extends Module {
	public ModuleFly( ) {
		super( "Fly", "Lets you fly like you\'re in creative", Keyboard.KEY_J, EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).capabilities.isFlying = false;
	}
	
	@Override
	public void onEnable( ) {
		getPlayer( ).capabilities.isFlying = true;
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		if( !getPlayer( ).capabilities.isFlying ) {
			getPlayer( ).capabilities.isFlying = true;
		}
	}
}

package com.luna.ce.module.classes;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;

// @Loadable
public class ModuleNoFall extends Module {
	
	public ModuleNoFall( ) {
		super( "NoFall", "Take no fall damage", Keyboard.KEY_O, EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		if( !getPlayer( ).onGround ) {
			getPlayer( ).onGround = true;
			getPlayer( ).fallDistance = 0.0F;
			
		}
	}
	
}

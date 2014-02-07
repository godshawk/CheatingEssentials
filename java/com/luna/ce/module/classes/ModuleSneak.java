package com.luna.ce.module.classes;

import net.minecraft.client.settings.KeyBinding;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleSneak extends Module {
	
	public ModuleSneak( ) {
		super( "Sneak", "Always sneak without holding the key", EnumModuleType.PLAYER );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
		KeyBinding.setKeyBindState( getGameSettings( ).keyBindSneak.getKeyCode( ), true );
	}
	
	@Override
	public void onDisable( ) {
		KeyBinding.setKeyBindState( getGameSettings( ).keyBindSneak.getKeyCode( ), false );
	}
}

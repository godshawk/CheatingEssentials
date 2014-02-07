package com.luna.ce.module.classes;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleHighJump extends Module {
	
	public ModuleHighJump( ) {
		super( "HighJump", "Jump higher than normal", EnumModuleType.PLAYER );
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).removePotionEffect( Potion.jump.getId( ) );
	}
	
	@Override
	public void onEnable( ) {
		getPlayer( ).addPotionEffect( new PotionEffect( Potion.jump.getId( ), 99999999, 2 ) );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
	}
}

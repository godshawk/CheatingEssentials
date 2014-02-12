package com.luna.ce.module.classes;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleFullbright extends Module {
	public ModuleFullbright( ) {
		super( "Fullbright", "Brightens up the world!", Keyboard.KEY_F, EnumModuleType.WORLD );
	}
	
	@Override
	public void onWorldRender( ) {
		getPlayer( ).addPotionEffect( new PotionEffect( Potion.nightVision.getId( ), 999999999, 255 ) );
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).removePotionEffect( Potion.nightVision.getId( ) );
	}
}

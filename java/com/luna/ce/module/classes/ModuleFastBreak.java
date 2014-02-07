package com.luna.ce.module.classes;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleFastBreak extends Module {
	
	public ModuleFastBreak( ) {
		super( "FastBreak", "Break blocks faster than normal", Keyboard.KEY_G, EnumModuleType.PLAYER );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
		getPlayer( ).addPotionEffect( new PotionEffect( Potion.digSpeed.getId( ), 99999999, 255 ) );
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).removePotionEffect( Potion.digSpeed.getId( ) );
	}
}

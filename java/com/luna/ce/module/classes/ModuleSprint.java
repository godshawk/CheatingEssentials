package com.luna.ce.module.classes;

import net.minecraft.block.material.Material;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleSprint extends Module {
	
	public ModuleSprint( ) {
		super( "Sprint", "Always sprint when you\'re able to", Keyboard.KEY_K, EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		if( canSprint( ) ) {
			getPlayer( ).setSprinting( true );
		}
	}
	
	private boolean canSprint( ) {
		return !getPlayer( ).isOnLadder( )
				&& getPlayer( ).onGround
				&& !getPlayer( ).isInWater( )
				&& !getPlayer( ).isInsideOfMaterial( Material.lava )
				&& !getPlayer( ).isInsideOfMaterial( Material.vine )
				&& !getPlayer( ).isSneaking( )
				&& getPlayer( ).isEntityAlive( )
				&& ( ( getPlayer( ).getFoodStats( ).getFoodLevel( ) > 6 ) || getPlayer( ).capabilities.isCreativeMode )
				&& !getPlayer( ).isBlocking( ) && !getPlayer( ).isEating( )
				&& ( getPlayer( ).moveForward > 0.0F );
	}
}

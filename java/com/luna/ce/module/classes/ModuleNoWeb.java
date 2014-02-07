package com.luna.ce.module.classes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Broken;

//@Loadable
@Broken
public class ModuleNoWeb extends Module {
	
	public ModuleNoWeb( ) {
		super( "NoWeb", "Don't get caught in those nasty webs!", EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		final Block b = getWorld( ).getBlock( ( int ) getPlayer( ).posX, ( int ) getPlayer( ).posY,
				( int ) getPlayer( ).posZ );
		if( getPlayer( ).isInsideOfMaterial( Material.web ) || ( b instanceof BlockWeb ) ) {
			getPlayer( ).motionX *= Math.pow( 0.25D, -1 );
			getPlayer( ).motionY *= Math.pow( 0.05000000074505806D, -1 );
			getPlayer( ).motionZ *= Math.pow( 0.25D, -1 );
		}
	}
}

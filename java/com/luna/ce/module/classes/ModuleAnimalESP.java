package com.luna.ce.module.classes;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.ce.render.GLHelper;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleAnimalESP extends Module {
	
	public ModuleAnimalESP( ) {
		super( "AnimalESP", "Find animals through walls", EnumModuleType.RENDER );
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public void onWorldRender( ) {
		for( final Entity e : ( List< Entity > ) getWorld( ).getLoadedEntityList( ) ) {
			final float halfWidth = e.width / 2.0F;
			final AxisAlignedBB bb = AxisAlignedBB.getAABBPool( )
					.getAABB( e.posX - halfWidth, e.posY, e.posZ - halfWidth, ( e.posX + halfWidth ),
							( e.posY + e.height ), ( e.posZ + halfWidth ) );
			
			if( ( e instanceof EntityAnimal ) ) {
				GLHelper.drawESP( bb, 0.1, 0.7, 0.1 );
			}
		}
	}
	
	@Override
	public void onWorldTick( ) {
		// TODO Auto-generated method stub
		
	}
}

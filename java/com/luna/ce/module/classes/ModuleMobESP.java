package com.luna.ce.module.classes;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.AxisAlignedBB;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.ce.render.GLHelper;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleMobESP extends Module {
	
	public ModuleMobESP( ) {
		super( "MobESP", "Find mobs (And slimes!) through walls", EnumModuleType.RENDER );
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public void onWorldRender( ) {
		for( final Entity e : ( List< Entity > ) getWorld( ).getLoadedEntityList( ) ) {
			final float halfWidth = e.width / 2.0F;
			final AxisAlignedBB bb = AxisAlignedBB.getAABBPool( )
					.getAABB( e.posX - halfWidth, e.posY, e.posZ - halfWidth, ( e.posX + halfWidth ),
							( e.posY + e.height ), ( e.posZ + halfWidth ) );
			
			if( ( e instanceof EntityMob ) ) {
				GLHelper.drawESP( bb, 0.7, 0.1, 0.1 );
			}
			
			if( ( e instanceof EntitySlime ) ) {
				GLHelper.drawESP( bb, 0.4, 0.7, 0.4 );
			}
		}
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
}

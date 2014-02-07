package com.luna.ce.module.classes;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleAntiArrow extends Module {
	
	public ModuleAntiArrow( ) {
		super( "AntiArrow", "Prevents arrows from hitting you", EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public void onWorldTick( ) {
		for( final Entity e : ( List< Entity > ) getWorld( ).loadedEntityList ) {
			if( e instanceof EntityArrow ) {
				final EntityArrow a = ( EntityArrow ) e;
				if( !a.shootingEntity.equals( getPlayer( ) ) ) {
					if( getPlayer( ).getDistanceToEntity( a ) <= 5.0F ) {
						getWorld( ).removeEntity( a );
					}
				}
			}
		}
	}
}

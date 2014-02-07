package com.luna.ce.module.classes;

import java.util.List;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.ce.render.GLHelper;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleChestESP extends Module {
	
	public ModuleChestESP( ) {
		super( "ChestESP", "Find chests through walls", Keyboard.KEY_C, EnumModuleType.RENDER );
	}
	
	@Override
	@SuppressWarnings( "unchecked" )
	public void onWorldRender( ) {
		for( final TileEntity e : ( List< TileEntity > ) getWorld( ).loadedTileEntityList ) {
			if( e instanceof TileEntityChest ) {
				final int tx = e.xCoord;
				final int ty = e.yCoord;
				final int tz = e.zCoord;
				
				if( getWorld( ).blockExists( tx, ty, tz ) ) {
					GLHelper.drawESP( AxisAlignedBB.getBoundingBox( tx - RenderManager.renderPosX, ty
							- RenderManager.renderPosY, tz - RenderManager.renderPosZ,
							( tx - RenderManager.renderPosX ) + 1, ( ty - RenderManager.renderPosY ) + 1,
							( tz - RenderManager.renderPosZ ) + 1 ), 0.1, 0.7, 0.7 );
				}
			}
		}
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
}

package com.luna.ce.module.classes;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.ce.render.GLHelper;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleBlockOverlay extends Module {
	
	public ModuleBlockOverlay( ) {
		super( "Block Overlay", "Renders a better block selection box", EnumModuleType.RENDER );
	}
	
	@Override
	public void onWorldRender( ) {
		final MovingObjectPosition pos = getMinecraft( ).objectMouseOver;
		final Block b = getWorld( ).getBlock( pos.blockX, pos.blockY, pos.blockZ );
		if( Block.getIdFromBlock( b ) != 0 ) {
			GLHelper.drawESP( AxisAlignedBB.getBoundingBox( pos.blockX, pos.blockY, pos.blockZ,
					pos.blockX + 1, pos.blockY + 1, pos.blockZ + 1 ), 0.2, 0.9, 0.2 );
		}
	}
	
	@Override
	public void onWorldTick( ) {
		
	}
	
}

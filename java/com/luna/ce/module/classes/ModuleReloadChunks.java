package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;

// @Loadable
public class ModuleReloadChunks extends Module {
	
	public ModuleReloadChunks( ) {
		super( "Reload Chunks", "Reloads all chunks in the world", EnumModuleType.MISC );
	}
	
	@Override
	public void onEnable( ) {
		getRenderGlobal( ).loadRenderers( );
		toggle( );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
	}
}

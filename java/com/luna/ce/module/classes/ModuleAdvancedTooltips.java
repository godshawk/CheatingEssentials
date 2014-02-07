package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleAdvancedTooltips extends Module {
	
	public ModuleAdvancedTooltips( ) {
		super( "Advanced Tooltips", "Enables the advanced item tooltips", EnumModuleType.MISC );
	}
	
	@Override
	public void onEnable( ) {
		getGameSettings( ).advancedItemTooltips = true;
	}
	
	@Override
	public void onDisable( ) {
		getGameSettings( ).advancedItemTooltips = false;
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
		getGameSettings( ).advancedItemTooltips = true;
	}
	
}

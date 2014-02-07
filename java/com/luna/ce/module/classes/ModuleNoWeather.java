package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleNoWeather extends Module {
	
	public ModuleNoWeather( ) {
		super( "NoWeather", "Prevents rain and snow", EnumModuleType.WORLD );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		getWorld( ).setRainStrength( 0F );
	}
	
}

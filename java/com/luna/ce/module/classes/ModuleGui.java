package com.luna.ce.module.classes;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import com.luna.ce.api.APIModuleSetup;
import com.luna.ce.gui.CEGuiModule;
import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleGui extends Module {
	private CEGuiModule	gui;
	
	public ModuleGui( ) {
		super( "Gui", "Opens up the GUI", Keyboard.KEY_Y, EnumModuleType.MISC );
		APIModuleSetup.addModuleToSetupQueue( this );
	}
	
	@Override
	public void initializeLater( ) {
		gui = new CEGuiModule( );
	}
	
	@Override
	public void onEnable( ) {
		Minecraft.getMinecraft( ).displayGuiScreen( gui );
		toggle( );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
	public CEGuiModule getGui( ) {
		return gui;
	}
}

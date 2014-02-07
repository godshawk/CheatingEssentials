package com.luna.ce.module.classes;

import org.lwjgl.input.Keyboard;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Experimental;
import com.luna.lib.annotations.TestClass;

// @Loadable
@Experimental
@TestClass
public class ModuleTest extends Module {
	public ModuleTest( ) {
		super( "Test", "The test module :D", Keyboard.KEY_Z, EnumModuleType.MISC );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onEnable( ) {
		addChatMessage( String.format( "%sTest module enabled!%s", getChatColor( 'a' ), getChatColor( 'r' ) ) );
	}
	
	@Override
	protected void onDisable( ) {
		addChatMessage( String.format( "%sTest module disabled!%s", getChatColor( 'c' ), getChatColor( 'r' ) ) );
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		// TODO Auto-generated method stub
		
	}
}

package com.luna.ce.module.classes;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleHighJump extends Module {
	private int	jumpValue	= 2;
	
	public ModuleHighJump( ) {
		super( "HighJump", "Jump higher than normal", EnumModuleType.PLAYER );
		setHelp( getDesc( ), String.format( "Usage: %s [set <value>]", getName( ) ) );
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).removePotionEffect( Potion.jump.getId( ) );
	}
	
	@Override
	public void onEnable( ) {
		getPlayer( ).addPotionEffect( new PotionEffect( Potion.jump.getId( ), 99999999, jumpValue ) );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	protected void childSetCommand( final String[ ] args ) {
		try {
			changeStepHeight( Integer.parseInt( args[ 2 ] ) );
		} catch( final NumberFormatException e ) {
			addChatMessage( String.format(
					"The %sset%s subcommand of %s%s%s requires a parameter of type %sint%s!",
					getChatColor( 'c' ), getChatColor( 'r' ), getChatColor( 'a' ), getName( ),
					getChatColor( 'r' ), getChatColor( 'c' ), getChatColor( 'r' ) ) );
		}
	}
	
	private void changeStepHeight( final int newVal ) {
		jumpValue = newVal;
		// Because this TOTALLY isn't a bad way to do it...
		onDisable( );
		onEnable( );
	}
}

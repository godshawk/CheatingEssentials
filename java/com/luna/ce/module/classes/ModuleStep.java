package com.luna.ce.module.classes;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleStep extends Module {
	
	private final float	DEFAULT_STEP_HEIGHT	= 0.5F;
	private float		newStepHeight		= 1.0F;
	
	public ModuleStep( ) {
		super( "Step", "Step up blocks without jumping.", EnumModuleType.PLAYER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable( ) {
		getPlayer( ).stepHeight = newStepHeight;
	}
	
	@Override
	public void onDisable( ) {
		getPlayer( ).stepHeight = DEFAULT_STEP_HEIGHT;
	}
	
	@Override
	public void onWorldRender( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onWorldTick( ) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	protected void childSetCommand( final String[ ] args ) {
		try {
			changeStepHeight( Float.parseFloat( args[ 2 ] ) );
		} catch( final NumberFormatException e ) {
			addChatMessage( String.format(
					"The %sset%s subcommand of %s%s%s requires a parameter of type %sfloat%s!",
					getChatColor( 'c' ), getChatColor( 'r' ), getChatColor( 'a' ), getName( ),
					getChatColor( 'r' ), getChatColor( 'c' ), getChatColor( 'r' ) ) );
		}
	}
	
	private void changeStepHeight( final float newStep ) {
		newStepHeight = newStep;
		onEnable( );
	}
}

package com.luna.ce.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.config.Config;
import com.luna.lib.interfaces.Command;

public abstract class Module implements Command {
	private final String			name;
	private int						key;
	private final String			desc;
	private final String			author;
	private final EnumModuleType	type;
	
	private String[ ]				help	= new String[ ] {
			"Default help.", "Definitely shouldn't be seeing this."
											};
	
	private boolean					active	= false;
	
	public Module( final String name, final String desc, final EnumModuleType type ) {
		this( name, desc, -1, type );
	}
	
	public Module( final String name, final String desc, final int key, final EnumModuleType type ) {
		this( name, desc, "godshawk", key, type );
	}
	
	public Module( final String name, final String desc, final String author, final int key,
			final EnumModuleType type ) {
		this.name = name;
		this.desc = desc;
		this.author = author;
		this.key = key;
		this.type = type;
		help = new String[ ] {
				desc, String.format( "Usage: %s", name.toLowerCase( ).replaceAll( " ", "" ) )
		};
	}
	
	public abstract void onWorldRender( );
	
	public abstract void onWorldTick( );
	
	/**
	 * Not abstract simply because most modules won't use this; only one or two
	 * will actually find a use for it, and it's a waste to have an empty method
	 * in EVERY class
	 */
	public void onGuiRender( ) {
	}
	
	protected void onDisable( ) {
	}
	
	protected void onEnable( ) {
	}
	
	/**
	 * Note that the args String[] is passed to subcommand methods UNCHANGED;
	 * you need to parse it yourself
	 */
	@Override
	public void onCommand( final String[ ] args ) {
		if( args.length == 1 ) {
			if( args[ 0 ].toLowerCase( ).equals( getName( ).replaceAll( " ", "" ).toLowerCase( ) ) ) {
				toggle( );
				return;
			}
		} else {
			if( args[ 1 ].equals( "set" ) ) {
				setCommand( args );
				return;
			}
		}
	}
	
	private void setCommand( final String[ ] args ) {
		if( !testArrayItem( args, 2 ) ) {
			addChatMessage( String.format( "The %sset%s subcommand of %s%s%s requires a parameter!",
					getChatColor( 'c' ), getChatColor( 'r' ), getChatColor( 'a' ), getName( ),
					getChatColor( 'r' ) ) );
		} else {
			childSetCommand( args );
		}
	}
	
	protected void childSetCommand( final String[ ] args ) {
		addChatMessage( String
				.format( "This is the default %sset%s subcommand for %s%s%s", getChatColor( 'c' ),
						getChatColor( 'r' ), getChatColor( 'a' ), getName( ), getChatColor( 'r' ) ) );
		addChatMessage( String.format(
				"If you are seeing this, this generally means that %s%s%s does not use this subcommand",
				getChatColor( 'a' ), getName( ), getChatColor( 'r' ) ) );
	}
	
	public void toggle( ) {
		active = !active;
		if( active ) {
			onEnable( );
		} else {
			onDisable( );
		}
		if( getWorld( ) != null ) {
			Config.getInstance( ).saveModuleConfig( );
		}
	}
	
	public void initializeLater( ) {
	}
	
	public boolean getActive( ) {
		return active;
	}
	
	public void setActive( final boolean active ) {
		this.active = active;
	}
	
	@Override
	public String getName( ) {
		return name;
	}
	
	public int getKey( ) {
		return key;
	}
	
	public void setKey( final int e ) {
		key = e;
	}
	
	public String getDesc( ) {
		return desc;
	}
	
	public String getAuthor( ) {
		return author;
	}
	
	public EnumModuleType getType( ) {
		return type;
	}
	
	protected Minecraft getMinecraft( ) {
		return Minecraft.getMinecraft( );
	}
	
	protected EntityPlayer getPlayer( ) {
		return getMinecraft( ).thePlayer;
	}
	
	protected WorldClient getWorld( ) {
		return getMinecraft( ).theWorld;
	}
	
	protected GameSettings getGameSettings( ) {
		return getMinecraft( ).gameSettings;
	}
	
	protected RenderGlobal getRenderGlobal( ) {
		return getMinecraft( ).renderGlobal;
	}
	
	protected EntityRenderer getEntityRenderer( ) {
		return getMinecraft( ).entityRenderer;
	}
	
	protected FontRenderer getFontRenderer( ) {
		return getMinecraft( ).fontRenderer;
	}
	
	protected void addChatMessage( final String text ) {
		getPlayer( ).addChatMessage( new ChatComponentText( String.format( "[CE] %s", text ) ) );
	}
	
	/**
	 * The "unused" variable is actually used to check if the array index exists
	 */
	@SuppressWarnings( "unused" )
	protected < T > boolean testArrayItem( final T[ ] array, final int index ) {
		try {
			final T testItem = array[ index ];
			return true;
		} catch( final Exception e ) {
			return false;
		}
	}
	
	public String[ ] getHelp( ) {
		return help;
	}
	
	public void setHelp( final String... newHelp ) {
		help = newHelp;
	}
	
	@Deprecated
	protected String getChatColor( final char col ) {
		return CheatingEssentials.getInstance( ).getChatColor( col );
	}
}

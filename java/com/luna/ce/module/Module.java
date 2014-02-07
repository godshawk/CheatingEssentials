package com.luna.ce.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;

import com.luna.ce.CheatingEssentials;
import com.luna.ce.manager.ManagerCommand;
import com.luna.lib.interfaces.Command;

public abstract class Module implements Command {
	private final String			name;
	private int						key;
	private final String			desc;
	private final String			author;
	private final EnumModuleType	type;
	
	private String[ ]				help	= new String[ ] {
			"Default help.", "Probably shouldn't be seeing this."
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
			desc
		};
	}
	
	public abstract void onWorldRender( );
	
	public abstract void onWorldTick( );
	
	protected void onDisable( ) {
	}
	
	protected void onEnable( ) {
	}
	
	/**
	 * When writing a custom onCommand, do NOT forget to call the superclass
	 * method to avoid having to parse this part on your own!
	 * {@link ManagerCommand} has some redundancy to help alleviate this, but it
	 * doesn't hurt to be safe~
	 * <p>
	 * <p>
	 * Module commands are the name of the module with no spaces, ie.
	 * "Chest Finder" would be "chestfinder"
	 */
	@Override
	public void onCommand( final String[ ] args ) {
		if( args[ 0 ].toLowerCase( ).equals( getName( ).replaceAll( " ", "" ).toLowerCase( ) ) ) {
			toggle( );
		}
	}
	
	public void toggle( ) {
		active = !active;
		if( active ) {
			onEnable( );
		} else {
			onDisable( );
		}
	}
	
	public boolean getActive( ) {
		return active;
	}
	
	public void setActive( final boolean active ) {
		this.active = active;
	}
	
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
	
	protected EntityClientPlayerMP getPlayer( ) {
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
	
	protected void addChatMessage( final String text ) {
		getPlayer( ).addChatMessage( new ChatComponentText( String.format( "[CE] %s", text ) ) );
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

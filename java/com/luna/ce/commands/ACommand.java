package com.luna.ce.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import com.luna.lib.interfaces.Command;

public abstract class ACommand implements Command {
	private final String	name;
	
	public ACommand( final String args ) {
		name = args;
	}
	
	@Override
	public String getName( ) {
		return name;
	}
	
	public abstract String getSyntax( );
	
	protected void addChatMessage( final String... message ) {
		for( final String e : message ) {
			Minecraft.getMinecraft( ).thePlayer.addChatMessage( new ChatComponentText( e ) );
		}
	}
}

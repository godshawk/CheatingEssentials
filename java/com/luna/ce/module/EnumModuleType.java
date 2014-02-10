package com.luna.ce.module;

public enum EnumModuleType {
	// TODO Add colors maybe?
	PLAYER, WORLD, RENDER, MISC;
	
	public String getRealName( ) {
		return name( ).substring( 0, 1 ).toUpperCase( ).concat( name( ).substring( 1 ).toLowerCase( ) );
	}
}

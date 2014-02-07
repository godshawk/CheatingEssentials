package com.luna.lib.datastructures;

public class Vector3 {
	private double	x, y, z;
	
	public Vector3( final double x, final double y, final double z ) {
		setX( x );
		setY( y );
		setZ( z );
	}
	
	public double getX( ) {
		return x;
	}
	
	public void setX( final double x ) {
		this.x = x;
	}
	
	public double getY( ) {
		return y;
	}
	
	public void setY( final double y ) {
		this.y = y;
	}
	
	public double getZ( ) {
		return z;
	}
	
	public void setZ( final double z ) {
		this.z = z;
	}
}

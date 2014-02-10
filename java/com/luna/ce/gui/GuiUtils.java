package com.luna.ce.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class GuiUtils {
	public static void color( final int color ) {
		final float alpha = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float red = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float green = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float blue = ( color & 255 ) / 255.0F;
		GL11.glColor4f( red, green, blue, alpha );
	}
	
	/**
	 * Returns RRGGBBAA
	 * 
	 * @param color
	 * @return
	 */
	public static float[ ] getColor( final int color ) {
		final float alpha = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float red = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float green = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float blue = ( color & 255 ) / 255.0F;
		return new float[ ] {
				red, green, blue, alpha
		};
	}
	
	/**
	 * Returns the instance of the FontRenderer
	 * 
	 * @return
	 */
	public static FontRenderer getFontRenderer( ) {
		return Minecraft.getMinecraft( ).fontRenderer;
	}
	
	/**
	 * SCALED width
	 * 
	 * @return
	 */
	public static int getWidth( ) {
		final ScaledResolution sr = getScaledResolution( );
		return sr.getScaledWidth( );
	}
	
	/**
	 * SCALED height
	 * 
	 * @return
	 */
	public static int getHeight( ) {
		final ScaledResolution sr = getScaledResolution( );
		return sr.getScaledHeight( );
	}
	
	public static ScaledResolution getScaledResolution( ) {
		final ScaledResolution sr = new ScaledResolution( Minecraft.getMinecraft( ).gameSettings,
				Minecraft.getMinecraft( ).displayWidth, Minecraft.getMinecraft( ).displayHeight );
		return sr;
	}
	
	/*
	 * Various OpenGL methods
	 */
	
	public static void enableScissoring( ) {
		GL11.glEnable( GL11.GL_SCISSOR_TEST );
	}
	
	/**
	 * @author Jonalu
	 * @return Used for nifty things ;)
	 * */
	public static void scissor( final float x, final float y, final float x2, final float y2 ) {
		final ScaledResolution sr = getScaledResolution( );
		final int factor = sr.getScaleFactor( );
		GL11.glScissor( ( int ) ( x * factor ), ( int ) ( ( sr.getScaledHeight( ) - y2 ) * factor ),
				( int ) ( ( x2 - x ) * factor ), ( int ) ( ( y2 - y ) * factor ) );
	}
	
	public static void disableScissoring( ) {
		GL11.glDisable( GL11.GL_SCISSOR_TEST );
	}
	
	public static void drawRect( final double x, final double y, final double w, final double h,
			final int color ) {
		final Tessellator tess = Tessellator.instance;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		
		color( color );
		
		tess.startDrawingQuads( );
		tess.addVertex( x, y, 0 );
		tess.addVertex( x, y + h, 0 );
		tess.addVertex( x + w, y + h, 0 );
		tess.addVertex( x + w, y, 0 );
		tess.draw( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
	}
	
	public static void drawGradientRect( final double x, final double y, final double w, final double h,
			final int color, final int color2 ) {
		final float var7 = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float var8 = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float var9 = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float var10 = ( color & 255 ) / 255.0F;
		final float var11 = ( ( color2 >> 24 ) & 255 ) / 255.0F;
		final float var12 = ( ( color2 >> 16 ) & 255 ) / 255.0F;
		final float var13 = ( ( color2 >> 8 ) & 255 ) / 255.0F;
		final float var14 = ( color2 & 255 ) / 255.0F;
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_ALPHA_TEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		final Tessellator var15 = Tessellator.instance;
		var15.startDrawingQuads( );
		var15.setColorRGBA_F( var8, var9, var10, var7 );
		var15.addVertex( x + w, y, 0 );
		var15.addVertex( x, y, 0 );
		var15.setColorRGBA_F( var12, var13, var14, var11 );
		var15.addVertex( x, y + h, 0 );
		var15.addVertex( x + w, y + h, 0 );
		var15.draw( );
		GL11.glShadeModel( GL11.GL_FLAT );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_ALPHA_TEST );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
	}
	
	/**
	 * 
	 * @param x
	 *            - X
	 * @param y
	 *            - Y
	 * @param w
	 *            - Width
	 * @param h
	 *            - Height
	 * @param color
	 *            - Border color
	 * @param color1
	 *            - Fill color
	 */
	public static void drawBorderedRect( final double x, final double y, final double w, final double h,
			final float lineWidth, final int color, final int color1 ) {
		drawRect( x, y, w, h, color1 );
		
		final float var7 = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float var8 = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float var9 = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float var10 = ( color & 255 ) / 255.0F;
		
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_ALPHA_TEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		
		GL11.glLineWidth( lineWidth );
		
		final Tessellator var15 = Tessellator.instance;
		var15.startDrawing( GL11.GL_LINE_LOOP );
		
		var15.setColorRGBA_F( var8, var9, var10, var7 );
		
		var15.addVertex( x, y, 1 );
		var15.addVertex( x + w, y, 1 );
		var15.addVertex( x + w, y + h, 1 );
		var15.addVertex( x, y + h, 1 );
		var15.draw( );
		
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_ALPHA_TEST );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
	}
	
	/**
	 * 
	 * @param x
	 *            - X
	 * @param y
	 *            - Y
	 * @param w
	 *            - Width
	 * @param h
	 *            - Height
	 * @param color
	 *            - Border color
	 * @param color1
	 *            - Gradient color 1
	 * @param color2
	 *            - Gradient color 2
	 */
	public static void drawGradientBorderedRect( final double x, final double y, final double w,
			final double h, final float lineWidth, final int color, final int color1, final int color2 ) {
		drawGradientRect( x, y, w, h, color1, color2 );
		
		final float var7 = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float var8 = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float var9 = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float var10 = ( color & 255 ) / 255.0F;
		
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_ALPHA_TEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		
		GL11.glLineWidth( lineWidth );
		
		final Tessellator var15 = Tessellator.instance;
		var15.startDrawing( GL11.GL_LINE_LOOP );
		
		var15.setColorRGBA_F( var8, var9, var10, var7 );
		
		var15.addVertex( x, y, 0 );
		var15.addVertex( x + w, y, 0 );
		var15.addVertex( x + w, y + h, 0 );
		var15.addVertex( x, y + h, 0 );
		var15.draw( );
		
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_ALPHA_TEST );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
	}
	
	public static void drawSideGradientRect( final double x, final double y, final double w, final double h,
			final int color, final int color2 ) {
		final float var7 = ( ( color >> 24 ) & 255 ) / 255.0F;
		final float var8 = ( ( color >> 16 ) & 255 ) / 255.0F;
		final float var9 = ( ( color >> 8 ) & 255 ) / 255.0F;
		final float var10 = ( color & 255 ) / 255.0F;
		final float var11 = ( ( color2 >> 24 ) & 255 ) / 255.0F;
		final float var12 = ( ( color2 >> 16 ) & 255 ) / 255.0F;
		final float var13 = ( ( color2 >> 8 ) & 255 ) / 255.0F;
		final float var14 = ( color2 & 255 ) / 255.0F;
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_ALPHA_TEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		final Tessellator var15 = Tessellator.instance;
		var15.startDrawingQuads( );
		var15.setColorRGBA_F( var8, var9, var10, var7 );
		var15.addVertex( x, y, 0 );
		var15.addVertex( x, y + h, 0 );
		var15.setColorRGBA_F( var12, var13, var14, var11 );
		var15.addVertex( x + w, y + h, 0 );
		var15.addVertex( x + w, y, 0 );
		var15.draw( );
		GL11.glShadeModel( GL11.GL_FLAT );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_ALPHA_TEST );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
	}
	
	public static void drawCircle( final double x, final double y, final double r, final int c ) {
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_POLYGON_SMOOTH );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		color( c );
		
		final Tessellator tess = Tessellator.instance;
		
		tess.startDrawing( GL11.GL_POLYGON );
		for( int i = 0; i <= 360; i++ ) {
			final double x2 = Math.sin( ( ( i * 3.141526D ) / 180 ) ) * r;
			final double y2 = Math.cos( ( ( i * 3.141526D ) / 180 ) ) * r;
			tess.addVertex( x + x2, y + y2, 0 );
		}
		tess.draw( );
		GL11.glDisable( GL11.GL_POLYGON_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
	}
	
	/**
	 * Compatibility with older code
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param c
	 */
	@Deprecated
	public static void drawFilledCircle( final double x, final double y, final double r, final int c ) {
		drawCircle( x, y, r, c );
	}
	
	/**
	 * 
	 * @param x
	 *            - X Location
	 * @param y
	 *            - Y Location
	 * @param rotation
	 *            - How far to rotate the wedge
	 * @param r
	 *            - Radius
	 * @param size
	 *            - Size of the wedge in degrees
	 * @param c
	 *            - Color
	 */
	public static void drawWedge( final double x, final double y, final double rotation, final double r,
			final double size, final int c ) {
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_POLYGON_SMOOTH );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glTranslated( x, y, 0D );
		GL11.glRotated( rotation, 0D, 0D, 1D );
		color( c );
		
		final Tessellator tess = Tessellator.instance;
		
		tess.startDrawing( GL11.GL_POLYGON );
		tess.addVertex( 0D, 0D, 0D );
		for( int i = 0; i <= size; i++ ) {
			final double x2 = Math.sin( ( ( i * 3.141526D ) / 180D ) ) * r;
			final double y2 = Math.cos( ( ( i * 3.141526D ) / 180D ) ) * r;
			tess.addVertex( x2, y2, 0D );
		}
		tess.draw( );
		
		GL11.glRotated( -rotation, 0D, 0D, 1D );
		GL11.glTranslated( -x, -y, 0D );
		GL11.glDisable( GL11.GL_POLYGON_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
	}
	
	public static void drawRotatedString( final String text, final double x, final double y,
			final double rotation ) {
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_POLYGON_SMOOTH );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glTranslated( x, y, 0 );
		GL11.glRotated( rotation, 0, 0, 1 );
		
		Minecraft.getMinecraft( ).fontRenderer.drawString( text, ( int ) x, ( int ) y, 0xffffff );
		
		GL11.glRotated( -rotation, 0, 0, 1 );
		GL11.glTranslated( -x, -y, 0 );
		GL11.glDisable( GL11.GL_POLYGON_SMOOTH );
		GL11.glDisable( GL11.GL_BLEND );
	}
	
	public static void drawGradientCircle( final double x, final double y, final double r, final int c,
			final int c2 ) {
		// 0xAARRGGBB
		final float f = ( ( c >> 24 ) & 0xff ) / 255F;
		final float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		final float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		final float f3 = ( c & 0xff ) / 255F;
		final float g = ( ( c2 >> 24 ) & 0xff ) / 255F;
		final float g1 = ( ( c2 >> 16 ) & 0xff ) / 255F;
		final float g2 = ( ( c2 >> 8 ) & 0xff ) / 255F;
		final float g3 = ( c2 & 0xff ) / 255F;
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_POLYGON_SMOOTH );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		
		final Tessellator tess = Tessellator.instance;
		
		tess.startDrawing( GL11.GL_POLYGON );
		tess.setColorRGBA_F( f1, f2, f3, f );
		tess.addVertex( x, y, 0 );
		tess.setColorRGBA_F( g1, g2, g3, g );
		for( int i = 1; i <= 360; i++ ) {
			final double x2 = Math.sin( ( ( i * 3.141526D ) / 180 ) ) * r;
			final double y2 = Math.cos( ( ( i * 3.141526D ) / 180 ) ) * r;
			tess.addVertex( x + x2, y + y2, 0 );
		}
		tess.draw( );
		
		GL11.glShadeModel( GL11.GL_FLAT );
		GL11.glDisable( GL11.GL_POLYGON_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
	}
}

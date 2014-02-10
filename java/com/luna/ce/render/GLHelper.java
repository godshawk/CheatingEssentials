package com.luna.ce.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.opengl.GL11;

public final class GLHelper {
	public static void drawESP( final AxisAlignedBB bb, final double r, final double g, final double b ) {
		Minecraft.getMinecraft( ).entityRenderer.disableLightmap( 0 );
		GL11.glPushMatrix( );
		GL11.glEnable( 3042 );
		GL11.glBlendFunc( 770, 771 );
		GL11.glLineWidth( 1.5F );
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glDisable( 2929 );
		GL11.glDepthMask( false );
		GL11.glColor4d( r, g, b, 0.1825F );
		drawBoundingBox( bb );
		GL11.glColor4d( r, g, b, 1.0F );
		drawOutlinedBoundingBox( bb );
		GL11.glLineWidth( 2.0F );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glEnable( 2929 );
		GL11.glDepthMask( true );
		GL11.glDisable( 3042 );
		GL11.glPopMatrix( );
		Minecraft.getMinecraft( ).entityRenderer.enableLightmap( 0 );
	}
	
	public static void drawLines( final double x1, final double y1, final double z1, final double x2,
			final double y2, final double z2, final double r, final double g, final double b ) {
		Minecraft.getMinecraft( ).entityRenderer.disableLightmap( 0 );
		GL11.glPushMatrix( );
		GL11.glEnable( 3042 );
		GL11.glBlendFunc( 770, 771 );
		GL11.glLineWidth( 1.5F );
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glDisable( 2929 );
		GL11.glDepthMask( false );
		GL11.glColor4d( r, g, b, 1.0F );
		final Tessellator t = Tessellator.instance;
		t.startDrawing( GL11.GL_LINES );
		t.addVertex( x1, y1, z1 );
		t.addVertex( x2, y2, z2 );
		t.draw( );
		GL11.glLineWidth( 2.0F );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glEnable( 2929 );
		GL11.glDepthMask( true );
		GL11.glDisable( 3042 );
		GL11.glPopMatrix( );
		Minecraft.getMinecraft( ).entityRenderer.enableLightmap( 0 );
	}
	
	public static void drawBoundingBox( final AxisAlignedBB axisalignedbb ) {
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads( ); // starts x
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.draw( ); // ends x
		tessellator.startDrawingQuads( ); // starts y
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.draw( ); // ends y
		tessellator.startDrawingQuads( ); // starts z
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		tessellator.draw( ); // ends z
	}
	
	/**
	 * Draws lines for the edges of the bounding box.
	 */
	public static void drawOutlinedBoundingBox( final AxisAlignedBB axisalignedbb ) {
		final Tessellator var2 = Tessellator.instance;
		var2.startDrawing( 3 );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.draw( );
		var2.startDrawing( 3 );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.draw( );
		var2.startDrawing( 1 );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.minZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.maxX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.minY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.addVertex( axisalignedbb.minX - RenderManager.renderPosX, axisalignedbb.maxY
				- RenderManager.renderPosY, axisalignedbb.maxZ - RenderManager.renderPosZ );
		var2.draw( );
	}
}

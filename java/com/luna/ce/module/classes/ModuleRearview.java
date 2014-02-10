package com.luna.ce.module.classes;

import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.ce.render.FrameBufferObject;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleRearview extends Module {
	
	private FrameBufferObject	fbo;
	
	public ModuleRearview( ) {
		super( "Rearview", "Renders a rearview camera on the GUI", EnumModuleType.MISC );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onGuiRender( ) {
		fbo = new FrameBufferObject( getMinecraft( ).displayWidth, getMinecraft( ).displayHeight, true );
		fbo.bindFramebuffer( true );
		getEntityRenderer( ).updateCameraAndRender( 0 );
		fbo.unbindFramebuffer( );
		
		GL11.glPushMatrix( );
		{
			GL11.glEnable( GL11.GL_TEXTURE_2D );
			fbo.bindFramebufferTexture( );
			new Gui( ).drawTexturedModalRect( getMinecraft( ).displayWidth - 256, 0, 0, 0, 256, 256 );
			fbo.unbindFramebufferTexture( );
			GL11.glDisable( GL11.GL_TEXTURE_2D );
		}
		GL11.glPopMatrix( );
	}
	
	@Override
	public void onWorldRender( ) {
	}
	
	@Override
	public void onWorldTick( ) {
	}
	
}

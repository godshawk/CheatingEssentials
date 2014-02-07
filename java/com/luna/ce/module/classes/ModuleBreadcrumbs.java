package com.luna.ce.module.classes;

import java.util.LinkedList;

import net.minecraft.client.renderer.entity.RenderManager;

import org.lwjgl.opengl.GL11;

import com.luna.ce.module.EnumModuleType;
import com.luna.ce.module.Module;
import com.luna.lib.annotations.Loadable;

@Loadable
public class ModuleBreadcrumbs extends Module {
	private final LinkedList< double[ ] >	positions	= new LinkedList<>( );
	
	public ModuleBreadcrumbs( ) {
		super( "Breadcrumbs", "Leaves a trail of breadcrumbs behind you", EnumModuleType.RENDER );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onWorldRender( ) {
		synchronized( positions ) {
			GL11.glPushMatrix( );
			
			getEntityRenderer( ).disableLightmap( 0 );
			GL11.glEnable( GL11.GL_BLEND );
			GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
			GL11.glLineWidth( 1.5F );
			GL11.glDisable( GL11.GL_LIGHTING );
			GL11.glDisable( GL11.GL_TEXTURE_2D );
			GL11.glEnable( GL11.GL_LINE_SMOOTH );
			GL11.glDisable( GL11.GL_DEPTH_TEST );
			GL11.glDepthMask( false );
			GL11.glBegin( GL11.GL_LINE_STRIP );
			GL11.glColor4d( 0, 0.7D, 0.7D, 1 );
			
			for( final double[ ] e : positions ) {
				GL11.glVertex3d( e[ 0 ] - RenderManager.renderPosX, e[ 1 ] - RenderManager.renderPosY, e[ 2 ]
						- RenderManager.renderPosZ );
			}
			GL11.glColor4d( 1, 1, 1, 1 );
			GL11.glEnd( );
			
			GL11.glDisable( GL11.GL_LINE_SMOOTH );
			GL11.glEnable( GL11.GL_TEXTURE_2D );
			GL11.glEnable( GL11.GL_LIGHTING );
			GL11.glEnable( GL11.GL_DEPTH_TEST );
			GL11.glDepthMask( true );
			GL11.glDisable( GL11.GL_BLEND );
			getEntityRenderer( ).enableLightmap( 0 );
			
			GL11.glPopMatrix( );
		}
	}
	
	@Override
	public void onWorldTick( ) {
		synchronized( positions ) {
			positions.add( new double[ ] {
					getPlayer( ).posX, getPlayer( ).posY, getPlayer( ).posZ
			} );
		}
	}
	
}

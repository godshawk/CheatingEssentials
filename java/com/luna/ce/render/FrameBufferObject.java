package com.luna.ce.render;

import java.nio.ByteBuffer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;

import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class FrameBufferObject {
	public int		framebufferTextureWidth;
	public int		framebufferTextureHeight;
	public int		framebufferWidth;
	public int		framebufferHeight;
	public boolean	useDepth;
	public int		framebufferObject;
	public int		framebufferTexture;
	public int		depthBuffer;
	public float[ ]	framebufferColor;
	public int		framebufferFilter;
	
	public FrameBufferObject( final int p_i45078_1_, final int p_i45078_2_, final boolean p_i45078_3_ ) {
		useDepth = p_i45078_3_;
		framebufferObject = -1;
		framebufferTexture = -1;
		depthBuffer = -1;
		framebufferColor = new float[ 4 ];
		framebufferColor[ 0 ] = 1.0F;
		framebufferColor[ 1 ] = 1.0F;
		framebufferColor[ 2 ] = 1.0F;
		framebufferColor[ 3 ] = 0.0F;
		createBindFramebuffer( p_i45078_1_, p_i45078_2_ );
	}
	
	public void createBindFramebuffer( final int p_147613_1_, final int p_147613_2_ ) {
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		
		if( framebufferObject >= 0 ) {
			deleteFramebuffer( );
		}
		
		createFramebuffer( p_147613_1_, p_147613_2_ );
		checkFramebufferComplete( );
		EXTFramebufferObject.glBindFramebufferEXT( 36160, 0 );
	}
	
	public void deleteFramebuffer( ) {
		unbindFramebufferTexture( );
		unbindFramebuffer( );
		
		if( depthBuffer > -1 ) {
			EXTFramebufferObject.glDeleteRenderbuffersEXT( depthBuffer );
			depthBuffer = -1;
		}
		
		if( framebufferTexture > -1 ) {
			TextureUtil.deleteTexture( framebufferTexture );
			framebufferTexture = -1;
		}
		
		if( framebufferObject > -1 ) {
			EXTFramebufferObject.glBindFramebufferEXT( 36160, 0 );
			EXTFramebufferObject.glDeleteFramebuffersEXT( framebufferObject );
			framebufferObject = -1;
		}
	}
	
	public void createFramebuffer( final int p_147605_1_, final int p_147605_2_ ) {
		framebufferWidth = p_147605_1_;
		framebufferHeight = p_147605_2_;
		framebufferTextureWidth = p_147605_1_;
		framebufferTextureHeight = p_147605_2_;
		
		framebufferObject = EXTFramebufferObject.glGenFramebuffersEXT( );
		framebufferTexture = TextureUtil.glGenTextures( );
		
		if( useDepth ) {
			depthBuffer = EXTFramebufferObject.glGenRenderbuffersEXT( );
		}
		
		setFramebufferFilter( 9729 );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, framebufferTexture );
		GL11.glTexImage2D( GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, framebufferTextureWidth,
				framebufferTextureHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, ( ByteBuffer ) null );
		EXTFramebufferObject.glBindFramebufferEXT( 36160, framebufferObject );
		EXTFramebufferObject.glFramebufferTexture2DEXT( 36160, 36064, 3553, framebufferTexture, 0 );
		
		if( useDepth ) {
			EXTFramebufferObject.glBindRenderbufferEXT( 36161, depthBuffer );
			EXTFramebufferObject.glRenderbufferStorageEXT( 36161, 33190, framebufferTextureWidth,
					framebufferTextureHeight );
			EXTFramebufferObject.glFramebufferRenderbufferEXT( 36160, 36096, 36161, depthBuffer );
		}
		
		framebufferClear( );
		unbindFramebufferTexture( );
	}
	
	public void setFramebufferFilter( final int p_147607_1_ ) {
		framebufferFilter = p_147607_1_;
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, framebufferTexture );
		GL11.glTexParameterf( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, p_147607_1_ );
		GL11.glTexParameterf( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, p_147607_1_ );
		GL11.glTexParameterf( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10496.0F );
		GL11.glTexParameterf( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10496.0F );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, 0 );
	}
	
	public void checkFramebufferComplete( ) {
		final int var1 = EXTFramebufferObject.glCheckFramebufferStatusEXT( 36160 );
		
		switch( var1 ) {
			case 36053:
				return;
				
			case 36054:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT" );
				
			case 36055:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT" );
				
			case 36056:
			default:
				throw new RuntimeException( "glCheckFramebufferStatusEXT returned unknown status:" + var1 );
				
			case 36057:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT" );
				
			case 36058:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT" );
				
			case 36059:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT" );
				
			case 36060:
				throw new RuntimeException( "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT" );
		}
	}
	
	public void bindFramebufferTexture( ) {
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, framebufferTexture );
	}
	
	public void unbindFramebufferTexture( ) {
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, 0 );
	}
	
	public void bindFramebuffer( final boolean p_147610_1_ ) {
		EXTFramebufferObject.glBindFramebufferEXT( 36160, framebufferObject );
		
		if( p_147610_1_ ) {
			GL11.glViewport( 0, 0, framebufferWidth, framebufferHeight );
		}
	}
	
	public void unbindFramebuffer( ) {
		EXTFramebufferObject.glBindFramebufferEXT( 36160, 0 );
	}
	
	public void setFramebufferColor( final float p_147604_1_, final float p_147604_2_,
			final float p_147604_3_, final float p_147604_4_ ) {
		framebufferColor[ 0 ] = p_147604_1_;
		framebufferColor[ 1 ] = p_147604_2_;
		framebufferColor[ 2 ] = p_147604_3_;
		framebufferColor[ 3 ] = p_147604_4_;
	}
	
	public void framebufferRender( final int p_147615_1_, final int p_147615_2_ ) {
		GL11.glColorMask( true, true, true, false );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glDepthMask( false );
		GL11.glMatrixMode( GL11.GL_PROJECTION );
		GL11.glLoadIdentity( );
		GL11.glOrtho( 0.0D, p_147615_1_, p_147615_2_, 0.0D, 1000.0D, 3000.0D );
		GL11.glMatrixMode( GL11.GL_MODELVIEW );
		GL11.glLoadIdentity( );
		GL11.glTranslatef( 0.0F, 0.0F, -2000.0F );
		GL11.glViewport( 0, 0, p_147615_1_, p_147615_2_ );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_ALPHA_TEST );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		GL11.glEnable( GL11.GL_COLOR_MATERIAL );
		bindFramebufferTexture( );
		final float var3 = p_147615_1_;
		final float var4 = p_147615_2_;
		final float var5 = ( float ) framebufferWidth / ( float ) framebufferTextureWidth;
		final float var6 = ( float ) framebufferHeight / ( float ) framebufferTextureHeight;
		final Tessellator var7 = Tessellator.instance;
		var7.startDrawingQuads( );
		var7.setColorOpaque_I( -1 );
		var7.addVertexWithUV( 0.0D, var4, 0.0D, 0.0D, 0.0D );
		var7.addVertexWithUV( var3, var4, 0.0D, var5, 0.0D );
		var7.addVertexWithUV( var3, 0.0D, 0.0D, var5, var6 );
		var7.addVertexWithUV( 0.0D, 0.0D, 0.0D, 0.0D, var6 );
		var7.draw( );
		unbindFramebufferTexture( );
		GL11.glDepthMask( true );
		GL11.glColorMask( true, true, true, true );
	}
	
	public void framebufferClear( ) {
		bindFramebuffer( true );
		GL11.glClearColor( framebufferColor[ 0 ], framebufferColor[ 1 ], framebufferColor[ 2 ],
				framebufferColor[ 3 ] );
		int var1 = 16384;
		
		if( useDepth ) {
			GL11.glClearDepth( 1.0D );
			var1 |= 256;
		}
		
		GL11.glClear( var1 );
		unbindFramebuffer( );
	}
}

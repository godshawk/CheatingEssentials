package com.luna.ce.gui.widget.components;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.luna.ce.gui.GuiUtils;
import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;

/**
 * Radar~
 * 
 * @author godshawk
 * 
 */
public class Radar extends Component {
	
	public Radar( ) {
		setHeight( 128 );
		setWidth( 128 );
	}
	
	@SuppressWarnings( {
			"unchecked", "deprecation"
	} )
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		final List< Entity > entities = Minecraft.getMinecraft( ).theWorld.loadedEntityList;
		
		GL11.glPushMatrix( );
		GL11.glTranslatef( getX( ) + ( getWidth( ) / 2 ), getY( ) + ( getHeight( ) / 2 ), 0 );
		
		GuiUtils.drawFilledCircle( 0, 0, 1.5, 0xff000000 );
		GuiUtils.drawFilledCircle( 0, 0, 1.25, 0xffffffff );
		GL11.glPopMatrix( );
		
		for( final Entity e : entities ) {
			final double xdis = Minecraft.getMinecraft( ).thePlayer.posX - e.posX;
			final double zdis = Minecraft.getMinecraft( ).thePlayer.posZ - e.posZ;
			final double tdis = Math.sqrt( ( xdis * xdis ) + ( zdis * zdis ) );
			final double difInAng = MathHelper
					.wrapAngleTo180_double( Minecraft.getMinecraft( ).thePlayer.rotationYaw
							- ( ( Math.atan2( zdis, xdis ) * 180.0D ) / Math.PI ) );
			final double finalX = Math.cos( Math.toRadians( difInAng ) ) * tdis * 2;
			final double finalY = -Math.sin( Math.toRadians( difInAng ) ) * tdis * 2;
			GL11.glPushMatrix( );
			GL11.glTranslatef( getX( ) + ( getWidth( ) / 2 ), getY( ) + ( getHeight( ) / 2 ), 0 );
			
			if( !( e instanceof EntityClientPlayerMP ) ) {
				if( e instanceof EntityPlayer ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xff0000ff );
					GL11.glScalef( 0.5F, 0.5F, 0.5F );
					final EntityPlayer p = ( EntityPlayer ) e;
					final String u = p.getDisplayName( );
					final int color = 0xffffff;
					
					getFontRenderer( ).drawString( u,
							( int ) ( finalX ) - ( getFontRenderer( ).getStringWidth( u ) / 2 ),
							( int ) finalY - 10, color );
					
					GL11.glScalef( 2F, 2F, 2F );
				}
				if( e instanceof EntityAnimal ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xff00ff00 );
				}
				if( e instanceof EntityMob ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xffff0000 );
				}
				if( e instanceof EntitySlime ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xffff88cc );
				}
				if( e instanceof EntityVillager ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xff8b4513 );
				}
				if( e instanceof EntityBat ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xfff4a460 );
				}
				if( e instanceof EntitySquid ) {
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 1, 0xff000000 );
					GuiUtils.drawFilledCircle( ( int ) finalX / 2, ( int ) finalY / 2, 0.75, 0xff003399 );
				}
			}
			GL11.glPopMatrix( );
		}
		
		GuiUtils.drawBorderedRect( getX( ) - 1, getY( ) - 2, getWidth( ) + 4, getHeight( ) + 16, 1.5F,
				0xaa000000, 0x0 );
	}
	
	@Override
	public void update( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void drawExtra( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked( final Window window, final int mouseX, final int mouseY, final int button ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
	
}

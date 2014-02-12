package com.luna.ce.gui.widget.base;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;

import com.luna.ce.gui.GuiUtils;
import com.luna.ce.gui.widget.components.Scrollbar;
import com.luna.lib.util.string.StringUtil;

public abstract class Window {
	/*
	 * Variable declarations
	 */
	private final ArrayList< Component >	componentList		= new ArrayList< Component >( );
	private int								x, y, w, h, xOffset, yOffset, prevX, prevY, prevWidth,
			prevHeight, titleHeight, spacerHeight, windowHeight;
	private String							text;
	private boolean							visible, expanded, pinned, dragging, scaling, expandable,
			pinnable, draggable, scalable, spacer, scrollable, ghettoResizeFix, resizable = true;
	private ISkin							skin;
	
	private double							scrollOffset		= 0;
	
	private final int						SCROLLBAR_MAGICK	= 8;
	
	/*
	 * Constructors
	 * 
	 * Overloading/daisychaining makes life easier.
	 */
	public Window( final ISkin skin, final int x, final int y ) {
		this( skin, x, y, 100, 20 );
	}
	
	public Window( final ISkin skin, final int x, final int y, final int w, final int h ) {
		this( "", skin, x, y, w, h );
	}
	
	public Window( final String text, final ISkin skin, final int x, final int y, final int w, final int h ) {
		this( text, skin, x, y, w, h, true );
	}
	
	public Window( final String text, final ISkin skin, final int x, final int y, final int w, final int h,
			final boolean visible ) {
		setText( text );
		setSkin( skin );
		setX( x );
		setY( y );
		setWidth( w );
		setHeight( h );
		setVisible( visible );
		setTitleHeight( 14 );
		setSpacerHeight( 4 );
		setSpacer( true );
		setExpandable( true );
		setPinnable( true );
		setDraggable( true );
		addComponents( );
		resizeWindow( );
		setScrollable( true );
	}
	
	/*
	 * Abstract methods
	 */
	
	/**
	 * Adds the components to be drawn on the window
	 */
	public abstract void addComponents( );
	
	/*
	 * Other methods
	 */
	public void drawWindow( final int mouseX, final int mouseY ) {
		if( getVisible( ) ) {
			// drag window changes
			dragWindow( mouseX, mouseY );
			// Organize children
			organizeChildren( );
			
			// Resize if needed
			resizeWindow( );
			
			// draw title rect
			getSkin( ).drawWindow( getX( ), getY( ), getWidth( ), getTitleHeight( ), true );
			// draw title
			getFontRenderer( ).drawString( getText( ), getX( ) + 3, getY( ) + 3,
					getSkin( ).getTextColor( true ) );
			// draw expanded button
			if( getExpandable( ) ) {
				getSkin( ).drawControls( ( getX( ) + getWidth( ) ) - 13, getY( ) + 1, 12, 12,
						getExpanded( ) || isOverExpanding( mouseX, mouseY ) );
				if( isOverExpanding( mouseX, mouseY ) ) {
					renderTooltip( mouseX, mouseY, !getExpanded( ) ? StringUtil.addED( "Minimize" )
							: "Minimize" );
				}
			}
			// draw pinned button
			if( getPinnable( ) ) {
				getSkin( ).drawControls( ( getX( ) + getWidth( ) ) - 26, getY( ) + 1, 12, 12,
						getPinned( ) || isOverPinned( mouseX, mouseY ) );
				if( isOverPinned( mouseX, mouseY ) ) {
					renderTooltip( mouseX, mouseY, getPinned( ) ? StringUtil.addED( "Pin" ) : "Pin" );
				}
			}
			// Scaling/resizing
			if( getScalable( ) && getExpanded( ) ) {
				getSkin( ).drawControls( getX( ) + getWidth( ), getY( ) + getHeight( ), 9, 9,
						isOverScaling( mouseX, mouseY ) );
			}
			// if expanded
			if( getExpanded( ) ) {
				if( !componentList.isEmpty( ) ) {
					if( getSpacer( ) ) {
						getSkin( ).drawControls(
								getX( ),
								( getY( ) + getTitleHeight( ) + getSpacerHeight( ) )
										- ( getSpacerHeight( ) - 1 ), getWidth( ),
								( getSpacerHeight( ) - 1 ), false );
					}
					
					GuiUtils.enableScissoring( );
					GuiUtils.scissor(
							getX( ),
							getY( ) + getTitleHeight( ) + ( getSpacer( ) ? getSpacerHeight( ) + 1 : 0 ),
							getX( ) + getWidth( ) + 1,
							( getY( ) + getTitleHeight( ) + getWindowHeight( )
									+ ( getSpacer( ) ? getSpacerHeight( ) : 0 ) + ( getSpacer( ) ? 20 : 15 ) ) - 1 );
					
					getSkin( )
							.drawWindow(
									getX( ),
									getY( ) + getTitleHeight( )
											+ ( getSpacer( ) ? getSpacerHeight( ) + 1 : 0 ),
									getWidth( ),
									getTitleHeight( ) + getWindowHeight( )
											+ ( getSpacer( ) ? getSpacerHeight( ) : 0 ), false );
					for( final Component e : componentList ) {
						
						if( getScrollable( ) && getScrollbarExists( ) ) {
							// Should prevent scissored items from being clicked
							if( !( ( ( e.getY( ) + e.getHeight( ) ) > ( getY( ) + getTitleHeight( ) + ( getSpacer( ) ? getSpacerHeight( ) + 1
									: 0 ) ) ) && ( e.getY( ) < ( getY( ) + getTitleHeight( )
									+ getWindowHeight( ) + ( getSpacer( ) ? getSpacerHeight( ) : 0 ) + ( getSpacer( ) ? 20
										: 15 ) ) ) ) ) {
								continue;
							}
						}
						
						e.update( ); // Some components will use this
						e.draw( this, getSkin( ), mouseX, mouseY );
					}
					
					GuiUtils.disableScissoring( );
				}
			}
		}
	}
	
	public boolean mouseClicked( final int mouseX, final int mouseY, final int button ) {
		if( getVisible( ) ) {
			if( getExpandable( ) && isOverExpanding( mouseX, mouseY ) ) {
				playSound( "random.click" );
				setExpanded( !expanded );
				return true;
			}
			if( getPinnable( ) && isOverPinned( mouseX, mouseY ) ) {
				playSound( "random.click" );
				setPinned( !pinned );
				return true;
			}
			if( getDraggable( ) && isOverDragging( mouseX, mouseY ) ) {
				if( isOverExpanding( mouseX, mouseY ) ) {
					if( getExpandable( ) ) {
						return false;
					}
				}
				if( isOverPinned( mouseX, mouseY ) ) {
					if( getPinnable( ) ) {
						return false;
					}
				}
				playSound( "random.click" );
				setXOffset( mouseX );
				setYOffset( mouseY );
				setPrevX( getX( ) );
				setPrevY( getY( ) );
				setDragging( true );
				return true;
			} else {
				setDragging( false );
			}
			if( getScalable( ) && isOverScaling( mouseX, mouseY ) ) {
				playSound( "random.click" );
				setXOffset( mouseX );
				setYOffset( mouseY );
				setPrevWidth( getWidth( ) );
				setPrevHeight( getHeight( ) );
				setScaling( true );
				return true;
			} else {
				setScaling( false );
			}
			if( getExpanded( ) ) {
				for( final Component component : componentList ) {
					
					if( getScrollable( ) && getScrollbarExists( ) ) {
						// Should prevent scissored items from being clicked
						if( !( ( ( component.getY( ) + component.getHeight( ) ) > ( getY( )
								+ getTitleHeight( ) + ( getSpacer( ) ? getSpacerHeight( ) + 1 : 0 ) ) ) && ( component
								.getY( ) < ( getY( ) + getTitleHeight( ) + getWindowHeight( )
								+ ( getSpacer( ) ? getSpacerHeight( ) : 0 ) + ( getSpacer( ) ? 20 : 15 ) ) ) ) ) {
							continue;
						}
					}
					
					if( component.mouseOver( ) ) {
						component.mouseClicked( this, mouseX, mouseY, button );
						return true;
					}
					
				}
				if( !isOverDragging( mouseX, mouseY ) && isOverWindow( mouseX, mouseY ) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Organizes the x/y of children, sets child/parent relations.
	 */
	@SuppressWarnings( "unused" )
	private void organizeChildren( ) {
		int i = 0;
		int prevy = ( int ) ( ( getY( ) + 14 + 2 + ( getSpacer( ) ? getSpacerHeight( ) + 1 : 0 ) ) - ( ( 14 * componentList
				.size( ) ) * scrollOffset ) );
		for( final Component e : componentList ) {
			e.setParent( this );
			
			if( e instanceof Scrollbar ) {
				e.setX( ( getX( ) + getWidth( ) ) - e.getWidth( ) - 1 );
				e.setY( getY( ) + 14 + 2 + ( getSpacer( ) ? getSpacerHeight( ) + 1 : 0 ) );
			} else {
				e.setX( getX( ) + 2 );
				e.setY( /* i == 0 ? prevy : */prevy + 1 );
				prevy = e.getY( ) + e.getHeight( );
			}
			i++;
		}
	}
	
	/**
	 * Returns true if this contains a scrollbar
	 * 
	 * @return
	 */
	private boolean getScrollbarExists( ) {
		if( !getScrollable( ) ) {
			return false;
		}
		for( final Component e : componentList ) {
			if( e instanceof Scrollbar ) {
				return true;
			}
		}
		return false;
	}
	
	public void keyTyped( final int key, final char c ) {
		for( final Component component : componentList ) {
			component.keyTyped( this, key, c );
		}
	}
	
	public void resizeWindow( ) {
		if( componentList.size( ) > SCROLLBAR_MAGICK ) {
			if( !getScrollbarExists( ) && getScrollable( ) ) {
				addChild( new Scrollbar( ) );
			}
		}
		
		if( getShouldResize( ) ) {
			int magick = 0;
			
			for( final Component component : componentList ) {
				if( component instanceof Scrollbar ) {
					magick += 8;
					continue;
				}
				/*
				 * if (getWidth() < (component.getWidth() + 12) || getWidth() >
				 * (component.getWidth() + 12)) { setWidth(component.getWidth()
				 * + 12); }
				 */
				
				if( ( component.getWidth( ) + 2 ) > magick ) {
					magick = component.getWidth( ) + 2;
				}
			}
			final int title = getFontRenderer( ).getStringWidth( getText( ) ) + 29;
			if( magick < title ) {
				magick = title;
			} else {
				magick += 4;
			}
			setWidth( magick );
			
			if( getScrollbarExists( ) ) {
				if( !ghettoResizeFix ) {
					ghettoResizeFix = true;
					setWidth( getWidth( ) + 10 );
				}
			}
		}
		
		/*
		 * if (getWidth_TB() < ((((HookFontRenderer)
		 * getFontRenderer()).stringCache .getStringWidth(getText())) + 55)) {
		 * setWidth(((HookFontRenderer) getFontRenderer()).stringCache
		 * .getStringWidth(getText() + 55)); }
		 */
		// setWindowHeight( ( ( 13 * Math.min( componentList.size( ),
		// SCROLLBAR_MAGICK ) ) ) );
		if( componentList.size( ) > SCROLLBAR_MAGICK ) {
			setWindowHeight( 13 * SCROLLBAR_MAGICK );
		} else {
			int q = 0;
			for( int i = 0; i < componentList.size( ); i++ ) {
				q += componentList.get( i ).getHeight( );
			}
			q -= Math.round( Math.pow( componentList.size( ), -1D ) * ( componentList.size( ) / 2 ) * 16 );
			setWindowHeight( q );
		}
		
		if( componentList.size( ) == 1 ) {
			if( getWindowHeight( ) < componentList.get( 0 ).getHeight( ) ) {
				setWindowHeight( componentList.get( 0 ).getHeight( ) - 14 );
			}
			if( getShouldResize( ) ) {
				if( getWidth( ) < ( componentList.get( 0 ).getWidth( ) + 6 ) ) {
					setWidth( componentList.get( 0 ).getWidth( ) + 6 );
				}
				
				if( getWidth( ) > componentList.get( 0 ).getWidth( ) ) {
					setWidth( componentList.get( 0 ).getWidth( ) + 6 );
				}
			}
		}
		
	}
	
	public void dragWindow( final int mouseX, final int mouseY ) {
		if( getDragging( ) && Mouse.isButtonDown( 0 ) ) {
			setX( mouseX - ( xOffset - prevX ) );
			setY( mouseY - ( yOffset - prevY ) );
			// TODO Out of bounds GUI?
			if( ( getX( ) + getWidth( ) ) > GuiUtils.getWidth( ) ) {
				setX( GuiUtils.getWidth( ) - getWidth( ) );
			}
			if( getX( ) < 0 ) {
				setX( 0 );
			}
			if( ( getY( ) + getTitleHeight( ) + ( getExpanded( ) ? getWindowHeight( ) + 20
					+ ( getSpacer( ) ? getSpacerHeight( ) : 0 ) : 0 ) ) > GuiUtils.getHeight( ) ) {
				
				setY( ( GuiUtils.getHeight( ) )
						- ( getTitleHeight( ) + ( ( getExpanded( ) ) ? getWindowHeight( ) + 20
								+ ( getSpacer( ) ? getSpacerHeight( ) : 0 ) : 0 ) ) );
			}
			if( getY( ) < 0 ) {
				setY( 0 );
			}
		} else {
			setDragging( false );
		}
	}
	
	public void scaleWindow( final int mouseX, final int mouseY ) {
		if( getScaling( ) && Mouse.isButtonDown( 0 ) ) {
			setWidth( mouseX - ( xOffset - prevWidth ) );
			setHeight( mouseY - ( yOffset - prevHeight ) );
		} else {
			setScaling( false );
		}
	}
	
	/*
	 * Check methods
	 */
	public boolean isOverWindow( final int mouseX, final int mouseY ) {
		return ( mouseX >= getX( ) ) && ( mouseY >= getY( ) ) && ( mouseX <= ( getX( ) + getWidth( ) ) )
				&& ( mouseY <= ( getY( ) + getHeight( ) ) );
	}
	
	public boolean isOverDragging( final int mouseX, final int mouseY ) {
		return ( mouseX >= getX( ) ) && ( mouseY >= getY( ) ) && ( mouseX <= ( getX( ) + getWidth( ) ) )
				&& ( mouseY <= ( getY( ) + 14 ) );
	}
	
	public boolean isOverExpanding( final int mouseX, final int mouseY ) {
		return ( mouseX >= ( ( getX( ) + getWidth( ) ) - 13 ) ) && ( mouseY >= ( getY( ) + 2 ) )
				&& ( mouseX <= ( ( getX( ) + getWidth( ) ) - 2 ) ) && ( mouseY <= ( getY( ) + 13 ) );
	}
	
	public boolean isOverPinned( final int mouseX, final int mouseY ) {
		return ( mouseX >= ( ( getX( ) + getWidth( ) ) - 26 ) ) && ( mouseY >= ( getY( ) + 2 ) )
				&& ( mouseX <= ( ( getX( ) + getWidth( ) ) - 16 ) ) && ( mouseY <= ( getY( ) + 12 ) );
	}
	
	public boolean isOverScaling( final int mouseX, final int mouseY ) {
		return ( mouseX >= ( getX( ) + getWidth( ) ) ) && ( mouseY >= ( getY( ) + getHeight( ) ) )
				&& ( mouseX <= ( getX( ) + getWidth( ) + 9 ) ) && ( mouseY <= ( getY( ) + getHeight( ) + 9 ) );
	}
	
	/*
	 * Getters
	 */
	public ArrayList< Component > getComponentList( ) {
		return componentList;
	}
	
	public String getText( ) {
		return text;
	}
	
	public int getX( ) {
		return x;
	}
	
	public int getY( ) {
		return y;
	}
	
	public int getWidth( ) {
		return w;
	}
	
	public int getHeight( ) {
		return h;
	}
	
	public int getXOffset( ) {
		return xOffset;
	}
	
	public int getYOffset( ) {
		return yOffset;
	}
	
	public int getPrevX( ) {
		return prevX;
	}
	
	public int getPrevY( ) {
		return prevY;
	}
	
	public int getPrevWidth( ) {
		return prevWidth;
	}
	
	public int getPrevHeight( ) {
		return prevHeight;
	}
	
	public boolean getVisible( ) {
		return visible;
	}
	
	public boolean getExpanded( ) {
		return expanded;
	}
	
	public boolean getPinned( ) {
		return pinned;
	}
	
	public boolean getDragging( ) {
		return dragging;
	}
	
	public boolean getScaling( ) {
		return scaling;
	}
	
	public boolean getExpandable( ) {
		return expandable;
	}
	
	public boolean getPinnable( ) {
		return pinnable;
	}
	
	public boolean getDraggable( ) {
		return draggable;
	}
	
	public boolean getScalable( ) {
		return scalable;
	}
	
	public ISkin getSkin( ) {
		return skin;
	}
	
	public int getTitleHeight( ) {
		return titleHeight;
	}
	
	public int getSpacerHeight( ) {
		return spacerHeight;
	}
	
	public boolean getSpacer( ) {
		return spacer;
	}
	
	public int getWindowHeight( ) {
		return windowHeight;
	}
	
	public double getScrollOffset( ) {
		return scrollOffset;
	}
	
	public boolean getScrollable( ) {
		return scrollable;
	}
	
	public boolean getShouldResize( ) {
		return resizable;
	}
	
	/*
	 * Setters
	 */
	public void setText( final String e ) {
		text = e;
	}
	
	public void setX( final int e ) {
		x = e;
	}
	
	public void setY( final int e ) {
		y = e;
	}
	
	public void setWidth( final int e ) {
		w = e;
	}
	
	public void setHeight( final int e ) {
		h = e;
	}
	
	public void setXOffset( final int e ) {
		xOffset = e;
	}
	
	public void setYOffset( final int e ) {
		yOffset = e;
	}
	
	public void setPrevX( final int e ) {
		prevX = e;
	}
	
	public void setPrevY( final int e ) {
		prevY = e;
	}
	
	public void setPrevWidth( final int e ) {
		prevWidth = e;
	}
	
	public void setPrevHeight( final int e ) {
		prevHeight = e;
	}
	
	public void setVisible( final boolean e ) {
		visible = e;
	}
	
	public void setExpanded( final boolean e ) {
		expanded = e;
	}
	
	public void setPinned( final boolean e ) {
		pinned = e;
	}
	
	public void setDragging( final boolean e ) {
		dragging = e;
	}
	
	public void setScaling( final boolean e ) {
		scaling = e;
	}
	
	public void setExpandable( final boolean e ) {
		expandable = e;
	}
	
	public void setPinnable( final boolean e ) {
		pinnable = e;
	}
	
	public void setDraggable( final boolean e ) {
		draggable = e;
	}
	
	public void setScalable( final boolean e ) {
		scalable = e;
	}
	
	public void setSkin( final ISkin e ) {
		skin = e;
	}
	
	public void setTitleHeight( final int e ) {
		titleHeight = e;
	}
	
	public void setSpacerHeight( final int e ) {
		spacerHeight = e;
	}
	
	public void setSpacer( final boolean e ) {
		spacer = e;
	}
	
	public void setWindowHeight( final int e ) {
		windowHeight = e;
	}
	
	public void setScrollOffset( final double e ) {
		scrollOffset = e;
	}
	
	public void setScrollable( final boolean e ) {
		scrollable = e;
	}
	
	public void setShouldResize( final boolean e ) {
		resizable = e;
	}
	
	/*
	 * General util
	 */
	
	/**
	 * Add a child to this window
	 */
	public void addChild( final Component e ) {
		componentList.add( e );
	}
	
	protected void playSound( final String sound ) {
		Minecraft.getMinecraft( ).getSoundHandler( )
				.playSound( PositionedSoundRecord.func_147674_a( new ResourceLocation( sound ), 1.0F ) );
	}
	
	protected FontRenderer getFontRenderer( ) {
		return Minecraft.getMinecraft( ).fontRenderer;
	}
	
	private void renderTooltip( final int mouseX, final int mouseY, final String text ) {
		final int w = getFontRenderer( ).getStringWidth( text );
		GuiUtils.drawRect( mouseX, mouseY - 10, w + 1, 10, 0x77000000 );
		getFontRenderer( ).drawString( text, mouseX + 1, mouseY - 9, 0xffffffff );
	}
}

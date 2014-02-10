package com.luna.ce.gui.widget.components;

import java.text.DecimalFormat;

import org.lwjgl.input.Mouse;

import com.luna.ce.gui.widget.base.Component;
import com.luna.ce.gui.widget.base.ISkin;
import com.luna.ce.gui.widget.base.Window;
import com.luna.lib.datastructures.Value;

public class Slider extends Component {
	
	private static final float	SLIDER_BUTTON_SIZE	= 7F;
	
	private float				sliderPercentage, incrementValue;
	private Value				value;
	private boolean				dragging;
	private final DecimalFormat	format;
	
	public Slider( final Value value, final int width, final float incrementValue ) {
		this.value = value;
		setWidth( width );
		setHeight( 12 );
		this.incrementValue = incrementValue;
		format = new DecimalFormat( "#.#" );
		sliderPercentage = value.getMax( ) / value.getValue( );
	}
	
	@Override
	public void draw( final Window window, final ISkin skin, final int mouseX, final int mouseY ) {
		drag( window, mouseX, mouseY );
		
		skin.drawSlider( getX( ), getY( ), getWidth( ), getHeight( ), false );
		final String formattedValue = format.format( getValue( ).getValue( ) );
		skin.drawSlider( getX( ), getY( ), ( int ) ( getWidth( ) * sliderPercentage ), getHeight( ), true );
		
		getFontRenderer( ).drawString( getValue( ).getName( ) + ": " + formattedValue, getX( ) + 3,
				getY( ) + 3, skin.getTextColor( false ) );
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
		dragging = true;
	}
	
	@Override
	public void keyTyped( final Window window, final int key, final char c ) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Handles the dragging of the slider's point. Will calculate percentage of
	 * the slider and pass that into the value object.
	 * */
	public void drag( final Window window, final int mouseX, final int mouseY ) {
		if( Mouse.isButtonDown( 0 ) && dragging ) {
			
			// get the difference (in pixels) from the cursor and the beginning
			// of the slider boundaries ( (mouse X) / (slider X) ) and subtract
			// the width of the slider.
			final float differenceWithMouseAndSliderBase = mouseX - getX( ) - ( SLIDER_BUTTON_SIZE / 2F );
			
			// converted into 0.0F ~ 1.0F percentage
			sliderPercentage = differenceWithMouseAndSliderBase / ( getWidth( ) - 4 );
			
			if( sliderPercentage < 0.0F ) {
				sliderPercentage = 0.0F;
			}
			
			if( sliderPercentage > 1.0F ) {
				sliderPercentage = 1.0F;
			}
			
			// set the value of the value object to the percentage of the
			// maximum allowed value, minus the minimum. Add the minimum.
			// percent * ((max value) - (min value)) + min value.
			setValue( );
		} else {
			sliderPercentage = ( value.getValue( ) - value.getMin( ) ) / ( value.getMax( ) - value.getMin( ) );
			dragging = false;
		}
	}
	
	/**
	 * Sets our value object to the calculated value of the slider.
	 * */
	public void setValue( ) {
		incrementValue = value.getIncrementValue( );
		final float calculatedValue = ( sliderPercentage * ( value.getMax( ) - value.getMin( ) ) );
		
		if( ( ( calculatedValue % incrementValue ) != 0 ) && ( incrementValue != -1 ) ) {
			value.setValue( ( ( calculatedValue ) - ( ( calculatedValue ) % incrementValue ) )
					+ value.getMin( ) );
		} else {
			value.setValue( calculatedValue + value.getMin( ) );
		}
	}
	
	/**
	 * @return the rendering position for the slider's point.
	 * */
	public float getPositionForPoint( final Window window ) {
		return( sliderPercentage * getWidthForPoint( window ) );
	}
	
	/**
	 * @return Width the slider point can be inside of. <br>
	 *         NOT including the actual pixel positions.
	 * */
	private float getWidthForPoint( final Window window ) {
		final float maxPointForRendering = ( window.getWidth( ) - getX( ) - SLIDER_BUTTON_SIZE - 1 ), beginPoint = ( getX( ) + 1 );
		return maxPointForRendering - beginPoint;
	}
	
	public float getSliderPercentage( ) {
		return sliderPercentage;
	}
	
	public void setSliderPercentage( final float sliderValue ) {
		sliderPercentage = sliderValue;
	}
	
	public Value getValue( ) {
		return value;
	}
	
	public void setValue( final Value value ) {
		this.value = value;
	}
	
	public boolean isDragging( ) {
		return dragging;
	}
	
	public void setDraggning( final boolean dragging ) {
		this.dragging = dragging;
	}
}

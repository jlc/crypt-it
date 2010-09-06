/*
 * OutputDialogBox.java
 *
 */

package net.kernub.cryptit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class OutputDialogBox extends DialogBox {

	public interface Strings extends Constants {
		public static final Strings inst = GWT.create( Strings.class );

		@DefaultStringValue( "Output" )
		String title();

		@DefaultStringValue( "Close" )
		String close();
	};

	final TextArea outputArea = new TextArea();

	final Button closeButton = new Button( Strings.inst.close() );

	final VerticalPanel panel = new VerticalPanel();

	public OutputDialogBox() {
	
		outputArea.setVisibleLines( 20 );
		outputArea.setCharacterWidth( 100 );
		outputArea.addStyleName( "OutputDialogBox_outputArea" );

		closeButton.addStyleName( "OutputDialogBox_closeButton" );
		closeButton.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event ) {
				outputArea.setText( "" );
				OutputDialogBox.this.hide();
			}
		});

		panel.add( outputArea );
		panel.add( closeButton );

		setText( Strings.inst.title() );
		setAnimationEnabled( true );
		setGlassEnabled( true );
		setWidget( panel );
	}

	public void showOutput( final String output ) {
		outputArea.setText( output );
		center();
	}
};


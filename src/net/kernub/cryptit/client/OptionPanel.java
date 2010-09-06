/*
 * OptionPanel.java
 *
 */

package net.kernub.cryptit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.FlowPanel;

public class OptionPanel extends LazyPanel {
	
	public interface  Strings extends Constants {
		public static final Strings inst = GWT.create( Strings.class );

		@DefaultStringValue( "Options" )
		String panelTitle();

		@DefaultStringValue( "Choose a cipher" )
		String cipherTitle();
		@DefaultStringValue( "Choose an output encoding" )
		String outputTitle();
		@DefaultStringValue( "AES" )
		String cipherAES();
		@DefaultStringValue( "DES" )
		String cipherDES();
		@DefaultStringValue( "Blowfish" )
		String cipherBlowfish();
		@DefaultStringValue( "Serpent" )
		String cipherSerpent();
		@DefaultStringValue( "Base 64" )
		String outputB64();
		@DefaultStringValue( "Alternative base 64" )
		String outputB64Alt();
	};

	public OptionPanel() {
	}

	@Override
	protected Widget createWidget() {
		
		final Label cipherTitleLabel = new Label( Strings.inst.cipherTitle() );
		final Label outputTitleLabel = new Label( Strings.inst.outputTitle() );

		final RadioButton cipherAESRadio = new RadioButton( "cipher-grp", Strings.inst.cipherAES() );
		final RadioButton cipherDESRadio = new RadioButton( "cipher-grp", Strings.inst.cipherDES() );
		final RadioButton cipherBlowfishRadio = new RadioButton( "cipher-grp", Strings.inst.cipherBlowfish() );
		final RadioButton cipherSerpentRadio = new RadioButton( "cipher-grp", Strings.inst.cipherSerpent() );

		final RadioButton outputB64Radio = new RadioButton( "output-grp", Strings.inst.outputB64() );
		final RadioButton outputB64AltRadio = new RadioButton( "output-grp", Strings.inst.outputB64Alt() );

		final FlowPanel cipherPanel = new FlowPanel();
		final FlowPanel outputPanel = new FlowPanel();
		final FlowPanel panel = new FlowPanel();

		cipherAESRadio.addStyleName( "OptionPanel_block" );
		cipherDESRadio.addStyleName( "OptionPanel_block" );
		cipherBlowfishRadio.addStyleName( "OptionPanel_block" );
		cipherSerpentRadio.addStyleName( "OptionPanel_block" );

		cipherAESRadio.setChecked( true );

		cipherPanel.add( cipherAESRadio );
		cipherPanel.add( cipherDESRadio );
		cipherPanel.add( cipherBlowfishRadio );
		cipherPanel.add( cipherSerpentRadio );
		cipherPanel.addStyleName( "OptionPanel_cipherPanel" );

		outputB64Radio.addStyleName( "OptionPanel_block" );
		outputB64AltRadio.addStyleName( "OptionPanel_block" );

		outputB64Radio.setChecked( true );

		outputPanel.add( outputB64Radio );
		outputPanel.add( outputB64AltRadio );
		outputPanel.addStyleName( "OptionPanel_outputPanel" );

		panel.add( cipherTitleLabel );
		panel.add( cipherPanel );
		panel.add( outputTitleLabel );
		panel.add( outputPanel );

		return panel;
	}

};

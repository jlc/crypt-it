/*
 * CipherPanel.java
 *
 */

package net.kernub.cryptit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class CipherPanel extends LazyPanel {

	public interface Strings extends Constants {
		public static final Strings inst = GWT.create( Strings.class );

		@DefaultStringValue( "Encrypt / Decrypt" )
		String panelTitle();

		@DefaultStringValue( "Password" )
		String password();

		@DefaultStringValue( "Confirm password" )
		String passwordConfirm();

		@DefaultStringValue( "Enter your input" )
		String inputTitle();

		@DefaultStringValue( "Encrypt" )
		String encrypt();

		@DefaultStringValue( "Decrypt" )
		String decrypt();

		@DefaultStringValue( "Password do not match" )
		String errorPasswordMismatch();
	};


	public CipherPanel() {
	}

	@Override
	protected Widget createWidget() {
		
		final Label pwdLabel = new Label( Strings.inst.password() );
		final Label pwdConfirmLabel = new Label( Strings.inst.passwordConfirm() );
		final Label pwdInfoError = new Label();
		final Label inputTitleLabel = new Label( Strings.inst.inputTitle() );

		final Button encryptButton = new Button( Strings.inst.encrypt() );
		final Button decryptButton = new Button( Strings.inst.decrypt() );

		final PasswordTextBox pwdTextBox = new PasswordTextBox();
		final PasswordTextBox pwdConfirmTextBox = new PasswordTextBox();

		final TextArea inputArea = new TextArea();

		//final FlowPanel pwdPanel = new FlowPanel();
		final HorizontalPanel pwdPanel = new HorizontalPanel();
		final FlowPanel inputPanel = new FlowPanel();
		final FlowPanel buttonPanel = new FlowPanel();
		final FlowPanel panel = new FlowPanel();

		pwdInfoError.addStyleName( "CipherPanel_pwdInfoError" );
		pwdLabel.addStyleName( "CipherPanel_pwdWidget" );
		pwdTextBox.addStyleName( "CipherPanel_pwdWidget" );
		pwdConfirmLabel.addStyleName( "CipherPanel_pwdWidget" );
		pwdConfirmTextBox.addStyleName( "CipherPanel_pwdWidget" );

		pwdPanel.add( pwdLabel );
		pwdPanel.add( pwdTextBox );
		pwdPanel.add( pwdConfirmLabel );
		pwdPanel.add( pwdConfirmTextBox );
		pwdPanel.add( pwdInfoError );
		pwdPanel.addStyleName( "CipherPanel_pwdPanel" );

		buttonPanel.add( encryptButton );
		buttonPanel.add( decryptButton );
		buttonPanel.addStyleName( "CipherPanel_buttonPanel" );

		inputPanel.add( inputTitleLabel );
		inputPanel.add( inputArea );
		inputPanel.addStyleName( "CipherPanel_inputPanel" );

		encryptButton.addStyleName( "CipherPanel_button" );
		decryptButton.addStyleName( "CipherPanel_button" );

		inputTitleLabel.addStyleName( "CipherPanel_inputWidget" );

		inputArea.setVisibleLines( 15 );
		inputArea.setCharacterWidth( 80 );
		inputArea.addStyleName( "CipherPanel_inputWidget" );

		panel.add( pwdPanel );
		panel.add( inputPanel );
		panel.add( buttonPanel );
		panel.addStyleName( "CipherPanel_panel" );

		class Handler implements ClickHandler {
			public void onClick( ClickEvent event ) {

				if( !pwdTextBox.getText().equals( pwdConfirmTextBox.getText() ) ) {
					pwdInfoError.setText( Strings.inst.errorPasswordMismatch() );
					return;
				} else {
					pwdInfoError.setText( "" );
				}

				if( event.getSource() == encryptButton ) {
					Application.inst.encrypt( pwdTextBox.getText(), inputArea.getText() );
				}
				else if( event.getSource() == decryptButton ) {
					Application.inst.decrypt( pwdTextBox.getText(), inputArea.getText() );
				}
			}
		};

		Handler handler = new Handler();
		encryptButton.addClickHandler( handler );
		decryptButton.addClickHandler( handler );

		return panel;
	}

};


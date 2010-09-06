/*
 * Application.java
 *
 */

package net.kernub.cryptit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;

import net.kernub.cryptit.client.CipherPanel;
import net.kernub.cryptit.client.OptionPanel;
import net.kernub.cryptit.client.AboutPanel;

import net.kernub.gwtcipher.client.provider.AES;

public class Application extends Composite implements ResizeHandler {

	public interface Strings extends Constants {
		public static final Strings inst = GWT.create( Strings.class );
		
		// TODO: change with jeanluc [img @] kernub.net
		@DefaultStringValue( "jeanluc@kernub.net" )
		String contactEmail();
	};

	public interface Resources extends ClientBundle {
		public static final Resources inst = GWT.create( Resources.class );

		@Source( "padlock.png" )
		ImageResource padlock();

		@Source( "title_cryptit.png" )
		ImageResource title();

		@Source( "flag_uk.png" )
		ImageResource flagUK();

		@Source( "flag_fr.png" )
		ImageResource flagFR();

		@Source( "flag_es.png" )
		ImageResource flagES();
	};

	final OutputDialogBox outputDialog = new OutputDialogBox();

	final FlowPanel logoPanel = new FlowPanel();
	final FlowPanel flagPanel = new FlowPanel();
	final FlowPanel headerPanel = new FlowPanel();
	//final SimplePanel headerWrapperPanel = new SimplePanel();

	final CipherPanel cipherPanel = new CipherPanel();
	final OptionPanel optionPanel = new OptionPanel();
	final AboutPanel aboutPanel = new AboutPanel();

	final ScrollPanel scrollCipherPanel = new ScrollPanel();
	final ScrollPanel scrollOptionPanel = new ScrollPanel();
	final ScrollPanel scrollAboutPanel = new ScrollPanel();

	final TabLayoutPanel tabPanel = new TabLayoutPanel( 3, Unit.EM );
	//final TabPanel tabPanel = new TabPanel();

	final FlowPanel footerPanel = new FlowPanel();

	final DockLayoutPanel panel = new DockLayoutPanel( Unit.PX );
	//final FlowPanel panel = new FlowPanel();

	public static Application inst;

	public Application() {

		inst = this;

		final Image padlockImg = new Image( Resources.inst.padlock() );
		final Image titleImg = new Image( Resources.inst.title() );
		final Image flagUKImg = new Image( Resources.inst.flagUK() );
		final Image flagFRImg = new Image( Resources.inst.flagFR() );
		final Image flagESImg = new Image( Resources.inst.flagES() );
		final Label emailLabel = new Label( Strings.inst.contactEmail() );

		flagUKImg.addStyleName( "App_flagImg" );
		flagFRImg.addStyleName( "App_flagImg" );
		flagESImg.addStyleName( "App_flagImg" );

		logoPanel.add( padlockImg );
		logoPanel.add( titleImg );
		logoPanel.addStyleName( "App_logoPanel" );

		flagPanel.add( flagFRImg );
		flagPanel.add( flagUKImg );
		flagPanel.add( flagESImg );
		flagPanel.addStyleName( "App_flagPanel" );

		headerPanel.add( logoPanel );
		headerPanel.add( flagPanel );

		// TODO: put that in a tab selectionHandler
		cipherPanel.ensureWidget();
		optionPanel.ensureWidget();
		aboutPanel.ensureWidget();

		scrollCipherPanel.setWidget( cipherPanel );
		scrollOptionPanel.setWidget( optionPanel );
		scrollAboutPanel.setWidget( aboutPanel );

		tabPanel.add( scrollCipherPanel, CipherPanel.Strings.inst.panelTitle() );
		tabPanel.add( scrollOptionPanel, OptionPanel.Strings.inst.panelTitle() );
		tabPanel.add( scrollAboutPanel, AboutPanel.Strings.inst.panelTitle() );
		tabPanel.addStyleName( "App_tabPanel" );
		tabPanel.setSize( "80%", "80%" );

		emailLabel.addStyleName( "App_emailLabel" );

		footerPanel.add( emailLabel );

		/*
		panel.add( headerPanel );
		panel.add( tabPanel );
		panel.add( footerPanel );
		*/
		panel.addNorth( headerPanel, 170 );
		panel.addSouth( footerPanel, 30 );
		panel.add( tabPanel );
		panel.addStyleName( "App_panel" );
		panel.getWidgetContainerElement( tabPanel ).setAttribute( "align", "center" );
		//panel.getElement().setAttribute( "align", "center" );

		initWidget( panel );
	}

	protected void onLoad() {
		super.onLoad();
	}

	public void onResize( final ResizeEvent event ) {
	}

	public void setCipher( final String cipherName ) {
		if( cipherName == "AES" ) {
		}
		else if( cipherName == "Blowfish" ) {
		}
		else {
		}
	}

	public void setOutput( final String outputType ) {
		if( outputType == "Base64" ) {
		}
		else if( outputType == "AltBase64" ) {
		}
		else {
		}
	}

	final AES aes = new AES();

	public void encrypt( final String pwd, final String data ) {
		String out = "";
		try {
			aes.setKey( pwd );
			out = aes.encryptBase64( data );
		} catch( Exception e ) {
			Window.alert( "Encryption error: " + e.getMessage() );
		}

		outputDialog.showOutput( out );
	}

	public void decrypt( final String pwd, final String data ) {
		String out = "";
		try {
			aes.setKey( pwd );
			out = aes.decryptBase64( data );
		} catch( Exception e ) {
			Window.alert( "Decryption error: " + e.getMessage() );
		}

		outputDialog.showOutput( out );
	}

};


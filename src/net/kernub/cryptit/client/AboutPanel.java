/*
 * AboutPanel.java
 *
 */

package net.kernub.cryptit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlowPanel;

public class AboutPanel extends LazyPanel {

	public interface Strings extends Constants {
		public static final Strings inst = GWT.create( Strings.class );

		@DefaultStringValue( "About" )
		String panelTitle();
	};

	public AboutPanel() {
	}

	@Override
	protected Widget createWidget() {
		final Label toImplementLabel = new Label( "To implement!" );
		final FlowPanel panel = new FlowPanel();

		panel.add( toImplementLabel );

		return panel;
	}

};


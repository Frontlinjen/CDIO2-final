package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;

public class NewRaavareClickHandler extends Composite {

	public void onLoad(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents"); 
		panel.clear();
		
		Label inputText = new Label("Inds\u00E6t Råvarens ID:");
		panel.add(inputText);
		final TextBox input = new TextBox();
		input.setMaxLength(8);
		panel.add(input);
		
		Label navnText = new Label("Inds\u00E6t Råvarens navn:");
		final TextBox navn = new TextBox();
		panel.add(navnText);
		panel.add(navn);
		

		
}

}

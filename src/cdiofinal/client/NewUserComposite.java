package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewUserComposite extends Composite{
	interface NewUserUIBinder extends UiBinder<Widget, NewUserComposite> {}
	private static NewUserUIBinder newUserUiBinder = GWT.create(NewUserUIBinder.class);
	@UiField TextBox cprBox;
	@UiField TextBox nameBox;
	@UiField TextBox iniBox;
	@UiField ListBox rankBox;
	@UiField PasswordTextBox passBox;
	@UiField Label statusField;
	public NewUserComposite() {
		initWidget(newUserUiBinder.createAndBindUi(this));
	}
	
	@UiHandler("cprBox")
	public void onCprChanged(KeyPressEvent e)
	{
		statusField.setText(Character.toString(e.getCharCode()));
	}
}

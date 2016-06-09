package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginScreen extends Composite{
	interface LoginUIBinder extends UiBinder<Widget, LoginScreen> {}
	private static LoginUIBinder loginUiBinder = GWT.create(LoginUIBinder.class);
	@UiField Button submit;
	@UiField TextBox username;
	@UiField TextBox password;
	
	public LoginScreen(){
		initWidget(loginUiBinder.createAndBindUi(this));
	}
	
	@UiHandler("submit")
	public void onSubmit(ClickEvent e)
	{
		String pass = password.getText();
		String usr = username.getText();
		//Verify login here
		RootPanel.get("contents").clear();
		RootPanel.get("contents").add(new MainPage());
	}

}


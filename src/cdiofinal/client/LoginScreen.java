package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.TokenRank;

public class LoginScreen extends Composite  implements AsyncCallback<TokenRank>{
	interface LoginUIBinder extends UiBinder<Widget, LoginScreen>{}
	private static LoginUIBinder loginUiBinder = GWT.create(LoginUIBinder.class);
	final LoginRPCInterfaceAsync database = (LoginRPCInterfaceAsync)GWT.create(LoginRPCInterface.class);
	@UiField Button submit;
	@UiField TextBox username;
	@UiField TextBox password;
	@UiField Label feedback;
	
	public LoginScreen(){
		initWidget(loginUiBinder.createAndBindUi(this));
	}
	
	@UiHandler("submit")
	public void onSubmit(ClickEvent e)
	{
		feedback.setText("Waiting for server..");
		String pass = password.getText();
		String usr = username.getText();
		try{
			int cpr = Integer.parseInt(usr);
			if(!FieldVerifier.isValidCpr(cpr))
			{
				feedback.setText("CPR skal v\u00E6re 10 langt.");
				return;
			}
			database.getLoginToken(cpr, pass, this);
		} 
		catch(NumberFormatException ex)
		{
			feedback.setText("CPR skal v\u00E6re et tal.");
		}
	}

	@Override
	public void onFailure(Throwable caught) {
		System.out.println("Failed to login");
		feedback.setText("Brugernavn eller password er forkert! Pr\u00F8v igen.");
		
	}

	@Override
	public void onSuccess(TokenRank tr) {
		System.err.println(tr);
		Token.setToken(tr.getToken());
		feedback.setText("Success!");
		RootPanel.get("contents").clear();
		RootPanel.get("contents").add(new MainPage());
	}

}


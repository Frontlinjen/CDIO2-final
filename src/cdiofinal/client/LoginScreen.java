package cdiofinal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import cdiofinal.server.DALException;
import cdiofinal.server.MySQLAnsatDAO;

public class LoginScreen implements EntryPoint{

	@Override
	public void onModuleLoad() {
		RootPanel base = RootPanel.get("LoginBase");
		Label idLabel = new Label("User ID:");
		TextBox idTB = new TextBox();
		Label passwordLabel = new Label("Password:");
		TextBox passwordTB = new TextBox();
		PushButton loginB = new PushButton("Login");
		MySQLAnsatDAO database = new MySQLAnsatDAO();
		
		base.clear();
		
		loginB.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(!idTB.getText().isEmpty() || !passwordTB.getText().isEmpty()){
					try{
						if(database.getAnsat(idTB.getText()).getPassword() == passwordTB.getText()){
							//login success
							//Load CDIO_FINAL.java
						}
						else{
							Window.alert("Wrong ID or password");
						}
						
					}
					catch(DALException e){
						Window.alert("Wrong ID or password");
					}
				}
				else{
					Window.alert("Please enter ID and password");
				}
			}
		});
		
		base.add(idLabel);
		base.add(idTB);
		base.add(passwordLabel);
		base.add(passwordTB);
		base.add(loginB);
		
	}

}


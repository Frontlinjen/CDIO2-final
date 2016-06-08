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

public class Central implements EntryPoint{
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

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
							//Successfully logged in, loading main page
							RootPanel container = RootPanel.get("options");
							String[] buttons = {"List users", "Create new user", "Tilføj råvare"};
							final Composite[] compositeWidgets = {new ListUsersClickHandler(), new NewUserClickhandler(), new NewUserClickhandler(), new NewRaavareClickHandler(), new NewRaavareBatchClickHandler()};
							ClickHandler[] clickHandlers = new ClickHandler[compositeWidgets.length];
							for (int i = 0; i < compositeWidgets.length; i++) {
								final int constant = i;
								clickHandlers[i] = new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										RootPanel panel = RootPanel.get("contents");
										panel.clear();
										Composite widget = compositeWidgets[constant];
										panel.add(widget);
									}
									
								};
							}
							for(int i=0;i<buttons.length;++i)
							{
								PushButton t = new PushButton(buttons[i]);
								t.addClickHandler(clickHandlers[i]);
								container.add(t);
							}
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


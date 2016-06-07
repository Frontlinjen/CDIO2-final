package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;


public class NewProductBatchClickHandler implements ClickHandler{
	AnsatRPCInterfaceAsync Productbatch = GWT.create(AnsatRPCInterface.class);
	@Override
	public void onClick(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents");
		panel.clear();
		Label inputText =  new Label("Inds\u00E6t CPR:"); 
		panel.add(inputText);
		final TextBox input = new TextBox();
		input.setMaxLength(10);
		panel.add(input);
		
		Label navnText = new Label("Inds\u00E6t navn:");
		final TextBox navn = new TextBox();
		panel.add(navnText);
		panel.add(navn);
		
		Label iniText = new Label("Inds\u00E6t Ini:");
		final TextBox ini = new TextBox();
		panel.add(iniText);
		panel.add(ini);
		
		
		Label rankText = new Label("Rank nr:");
		final TextBox rank = new TextBox();
		rank.setMaxLength(1);
		panel.add(rankText);
		panel.add(rank);
		
		Label passText = new Label("Inds\u00E6t password:");
		final PasswordTextBox pass = new PasswordTextBox();
		panel.add(passText);
		panel.add(pass);
		
		final Label status = new Label("Enter fields");
		Button button = new Button("Create");
		panel.add(status);
		panel.add(button);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(navn.getValue().length()==0)
				{
					status.setText("Name not long enough");
				}
				else if(!FieldVerifier.isValidName(input.getValue()))
				{
					status.setText("CPR invalid");
				}
				else if(!Character.isDigit(rank.getValue().charAt(0)))
					{
						status.setText("Rank must be a digit");
					}
				else if(ini.getText().length() < 3)
				{
					status.setText("Ini much be 3 characters");
				}
				else
				{
					
					AnsatRPC.createAnsat(new AnsatDTO(input.getValue(), navn.getText(), ini.getText(), pass.getText(), Integer.parseInt(rank.getValue())), new AsyncCallback<Integer>()
							{

								@Override
								public void onFailure(Throwable caught) {
									status.setText("Failed to create user");
									
								}

								@Override
								public void onSuccess(Integer result) {
									status.setText("Successfully created user");		
									input.setValue("");
									navn.setValue("");
									ini.setValue("");
									pass.setValue("");
									rank.setValue("");
								}
						
							});
				}
			}
			
		});
	}


}

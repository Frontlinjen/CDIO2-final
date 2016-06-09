package cdiofinal.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import cdiofinal.client.RaaLevRPCInterfaceAsync;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareDTO;

public class NewRaaLevClickHandler extends Composite {

	public void onLoad(ClickEvent event) {
	final RaaLevRPCInterfaceAsync RaavareRPC = GWT.create(RaaLevRPCInterface.class);
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
		
		final Label status = new Label("Enter fields");
		Button button = new Button("Create");
		panel.add(status);
		panel.add(button);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(navn.getValue().length()==0)
				{
					status.setText("Intet navn indtastet");
				}
				else if(!FieldVerifier.isValidName(input.getValue()))
				{
					status.setText("Name invalid");
				}
				else if(input.getMaxLength()==0)
				{	
					status.setText("ID'et må ikke være tomt");
				}
				else if(input.getMaxLength()>8)
				{
					status.setText("Id'et overskrider den maksimale værdi");
				}
				else
				{
				
					RaavareRPC.createRaavare(new RaavareDTO(Integer.parseInt(input.getValue()), navn.getText()), new AsyncCallback<Integer>()

							{

								@Override
								public void onFailure(Throwable caught) {
									status.setText("Kunne ikke skabe raavaren");
									
								}

								@Override
								public void onSuccess(Integer result) {
									status.setText("Successfuld skabelse af raavare");		
									input.setValue("");
									navn.setValue("");
								}
						
							});
				}
			}
			
		});
	}

}

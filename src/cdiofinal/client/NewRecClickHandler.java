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

import cdiofinal.shared.ReceptDTO;
import cdiofinal.client.RecRPCInterfaceAsync;
import cdiofinal.shared.FieldVerifier;

public class NewRecClickHandler extends Composite {
	
	FieldVerifier fieldV = new FieldVerifier();
	RecRPCInterfaceAsync ReceptRPC = GWT.create(RecRPCInterface.class);
	public void onClick(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents");
		panel.clear();
		
		Label idText = new Label("Inds\u00E6t Receptens ID");
		panel.add(idText);
		final TextBox id = new TextBox();
		id.setMaxLength(8);
		panel.add(id);
				
		Label navnText = new Label("Inds\u00E6t Receptens navn");
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
				if(fieldV.isValidId(Integer.parseInt(id.getValue()))==false)
				{
					status.setText("Id'et må ikke være tomt");
				}
				else if(navn.getValue().length()==0)
				{
					status.setText("Intet navn indtastet");
				}
				else if(!FieldVerifier.isValidName(navn.getValue()))
				{
					status.setText("Name invalid");
				}
				else
				{
				
					ReceptRPC.createRecept(new ReceptDTO(Integer.parseInt(id.getValue()), navn.getText()), new AsyncCallback<Integer>()
						{
						
						public void onFailure(Throwable caught) 
						{
							status.setText("Kunne ikke skabe raavaren");
						}

						public void onSuccess(Integer result) 
						{
							status.setText("Successfuld skabelse af raavare");		
							id.setValue("");
							navn.setValue("");
						}
		
				});
			}
		}
	});
}
}

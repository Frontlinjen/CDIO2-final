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

import cdiofinal.shared.ProduktBatchDTO;
import cdiofinal.shared.FieldVerifier;


public class NewProduktBatchClickHandler extends Composite{
	ProduktBatchRPCInterfaceAsync ProduktbatchRPC = GWT.create(ProduktBatchRPCInterface.class);
	public void onClick(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents");
		panel.clear();
		Label textPbID =  new Label("Inds\u00E6t pb_id:"); 
		panel.add(textPbID);
		final TextBox inputPbID = new TextBox();
		inputPbID.setMaxLength(8);
		panel.add(inputPbID);

		Label textStatus = new Label("Inds\u00E6t status");
		final TextBox inputStatus = new TextBox();
		inputStatus.setMaxLength(1);
		panel.add(textStatus);
		panel.add(inputStatus);

		Label textReceptID = new Label("Inds\u00E6t recept_id:");
		final TextBox inputReceptID = new TextBox();
		inputReceptID.setMaxLength(8);
		panel.add(textReceptID);
		panel.add(inputReceptID);

		final Label status = new Label("Enter fields");
		Button button = new Button("Create");
		panel.add(status);
		panel.add(button);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ProduktbatchRPC.createProduktBatch(new ProduktBatchDTO(inputPbID.getTabIndex(), inputStatus.getTabIndex(), inputReceptID.getTabIndex()), new AsyncCallback<Integer>()
				{

					@Override
					public void onFailure(Throwable caught) {
						status.setText("Failed to create productbatch");

					}

					@Override
					public void onSuccess(Integer result) {
						status.setText("Successfully created productbatch");		
						inputPbID.setValue("");
						inputStatus.setValue("");
						inputReceptID.setValue("");
					}
				});

			}
		});
	}
};



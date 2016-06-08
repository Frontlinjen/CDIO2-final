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
import cdiofinal.shared.RaavareBatchDTO;
import cdiofinal.shared.FieldVerifier;


public class NewRaavareBatchClickHandler extends Composite{
	RaavareBatchRPCInterfaceAsync RaavarebatchRPC = GWT.create(RaavareBatchRPCInterface.class);
	public void onClick(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents");
		panel.clear();
		Label textRbID =  new Label("Inds\u00E6t pb_id:"); 
		panel.add(textRbID);
		final TextBox inputRbID = new TextBox();
		inputRbID.setMaxLength(8);
		panel.add(inputRbID);

		Label textRaavareID = new Label("Inds\u00E6t raavare_id");
		final TextBox inputRaavareID = new TextBox();
		inputRaavareID.setMaxLength(1);
		panel.add(textRaavareID);
		panel.add(inputRaavareID);

		Label textLeverandoerID = new Label("Inds\u00E6t leverandoer_id:");
		final TextBox inputLeverandoerID = new TextBox();
		inputLeverandoerID.setMaxLength(8);
		panel.add(textLeverandoerID);
		panel.add(inputLeverandoerID);
		
		Label textMaengde = new Label("Inds\u00E6t maengde:");
		final TextBox inputMaengde = new TextBox();
		inputMaengde.setMaxLength(8);
		panel.add(textMaengde);
		panel.add(inputMaengde);
		

		final Label status = new Label("Enter fields");
		Button button = new Button("Create");
		panel.add(status);
		panel.add(button);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RaavarebatchRPC.createRaavareBatch(new RaavareBatchDTO(inputRbID.getTabIndex(), inputRaavareID.getTabIndex(), inputLeverandoerID.getTabIndex(), inputMaengde.getTabIndex()), new AsyncCallback<Integer>()
				{

					@Override
					public void onFailure(Throwable caught) {
						status.setText("Failed to create raavarebatch");

					}

					@Override
					public void onSuccess(Integer result) {
						status.setText("Successfully created raavarebatch");		
						inputRbID.setValue("");
						inputRaavareID.setValue("");
						inputLeverandoerID.setValue("");
						inputMaengde.setValue("");
					}
				});

			}
		});
	}
};



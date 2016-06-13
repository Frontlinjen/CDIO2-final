package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareDTO;

public class NewRaavareComposite extends Composite implements AsyncCallback<RaavareDTO>{
	final RaavareRPCInterfaceAsync database = (RaavareRPCInterfaceAsync)GWT.create(RaavareRPCInterface.class);
	interface NewRaavareUIBinder extends UiBinder<Widget, NewRaavareComposite>{}
	private static NewRaavareUIBinder newRaavareUIBinder = GWT.create(NewRaavareUIBinder.class);
	
	private NewElementCreatedCallback<RaavareDTO> callback;
	
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField Label statusField;
	
	public NewRaavareComposite(NewElementCreatedCallback<RaavareDTO> callback) {
		initWidget(newRaavareUIBinder.createAndBindUi(this));
		this.callback = callback;
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getValue()))==true)
		{
			statusField.setText("Id'et er ugyldigt. (1-99999999");
		}
		if(!FieldVerifier.isValidName(navnBox.getText())==true)
		{
			statusField.setText("Navnet er ugyldigt. Benyt kun bogstaver, med en l�ngde mellem 2-20 karaktere");
		}
		else
			
			database.createRaavare(new RaavareDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create Raavare");
		
	}

	@Override
	public void onSuccess(RaavareDTO result) {
		statusField.setText("Successfully created Raavare");
		idBox.setValue("");
		navnBox.setValue("");	
		callback.onElementCreated(result);
	}
}

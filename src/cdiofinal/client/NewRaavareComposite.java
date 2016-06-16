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
	private final RaavareRPCInterfaceAsync database = (RaavareRPCInterfaceAsync)GWT.create(RaavareRPCInterface.class);
	interface NewRaavareUIBinder extends UiBinder<Widget, NewRaavareComposite>{}
	private static NewRaavareUIBinder newRaavareUIBinder = GWT.create(NewRaavareUIBinder.class);
	
	private NewElementCreatedCallback<RaavareDTO> callback;
	
	@UiField public TextBox idBox;
	@UiField public TextBox navnBox;
	@UiField public Label statusField;
	
	public NewRaavareComposite(NewElementCreatedCallback<RaavareDTO> callback) {
		initWidget(newRaavareUIBinder.createAndBindUi(this));
		this.callback = callback;
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		int id;
		try
		{
			id = Integer.parseInt(idBox.getValue());
		}
		catch(NumberFormatException ex)
		{
			statusField.setText("ID skal v\u00E6re et tal!");
			return;
		}
		
		if(!FieldVerifier.isValidId(id)==true)
		{
			statusField.setText("Id'et er ugyldigt. (1-99999999");
		}
		else if(!FieldVerifier.isValidName(navnBox.getText())==true)
		{
			statusField.setText("Navnet er ugyldigt. Benyt kun bogstaver med en l\u00E6ngde mellem 2-20 karakterer");
		}
		else
			
			database.createRaavare(new RaavareDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Kunne ikke oprette r\u00E5vare." + ErrorHandling.getError(caught));;
		
	}

	@Override
	public void onSuccess(RaavareDTO result) {
		statusField.setText("R\u00E5vare oprettet.");
		idBox.setValue("");
		navnBox.setValue("");	
		callback.onElementCreated(result);
	}
}

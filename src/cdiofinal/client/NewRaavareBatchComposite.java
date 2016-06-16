package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.protobuf.UnknownFieldSet.Field;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.RaavareBatchDTO;


public class NewRaavareBatchComposite extends Composite implements AsyncCallback<RaavareBatchDTO>{
	private final RaavareBatchRPCInterfaceAsync database = (RaavareBatchRPCInterfaceAsync)GWT.create(RaavareBatchRPCInterface.class);
	interface NewRaavareBatchUIBinder extends UiBinder<Widget, NewRaavareBatchComposite> {}
	private static NewRaavareBatchUIBinder newRaavareBatchUiBinder = GWT.create(NewRaavareBatchUIBinder.class);
	
	private NewElementCreatedCallback<RaavareBatchDTO> callback;
	
	@UiField public IntegerBox batchNrBox;
	@UiField public IntegerBox raavareId;
	@UiField public IntegerBox supplierIdBox;
	@UiField public IntegerBox amountBox;
	@UiField public Label statusField;
	
	NewRaavareBatchComposite(NewElementCreatedCallback<RaavareBatchDTO> callback) {
		initWidget(newRaavareBatchUiBinder.createAndBindUi(this));
		this.callback = callback;
	}
	

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) 
	{		
		if(FieldVerifier.isNumber(batchNrBox.getText())==false || FieldVerifier.isValidId(Integer.parseInt(batchNrBox.getText()))==false)
		{
			statusField.setText("Batchnummeret skal v\u00E6re en integer mellem 1-99999999!");
		}		
		if (!FieldVerifier.isNumber(raavareId.getText()) == true  || !FieldVerifier.isNumber(supplierIdBox.getText()) == true)
		{	
			statusField.setText("Id'et m\u00E5 kun best\u00E5 af tal mellem 1-99999999");
		}
		
		else
			
			database.createRaavareBatch(new RaavareBatchDTO(Integer.parseInt(batchNrBox.getText()), Integer.parseInt(raavareId.getText()), Integer.parseInt(supplierIdBox.getText()), Double.parseDouble(amountBox.getText())), Token.getToken(), this);
			
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Kunne ikke oprette R\u00E5varebatch da " + ErrorHandling.getError(caught));
		
	}

	@Override
	public void onSuccess(RaavareBatchDTO result) {
		statusField.setText("Successfuldt oprettet R\u00E5varebatch!");
		batchNrBox.setText("");
		raavareId.setText("");
		supplierIdBox.setText("");
		amountBox.setText("");
		callback.onElementCreated(result);
		
	}

}

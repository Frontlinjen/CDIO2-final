package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareBatchDTO;


public class NewRaavareBatchComposite extends Composite implements AsyncCallback<Integer>{
	final RaavareBatchRPCInterfaceAsync database = (RaavareBatchRPCInterfaceAsync)GWT.create(RaavareBatchRPCInterface.class);
	interface NewRaavareBatchUIBinder extends UiBinder<Widget, NewRaavareBatchComposite> {}
	private static NewRaavareBatchUIBinder newRaavareBatchUiBinder = GWT.create(NewRaavareBatchUIBinder.class);
	@UiField IntegerBox batchNrBox;
	@UiField IntegerBox raavareId;
	@UiField IntegerBox supplierIdBox;
	@UiField IntegerBox amountBox;
	@UiField Label statusField;
	NewRaavareBatchComposite() {
		initWidget(newRaavareBatchUiBinder.createAndBindUi(this));
	}
	
	FieldVerifier f = new FieldVerifier();

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws DALException
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(batchNrBox.getText()))==true)
		{
			System.out.println("Batch ID'en eksisterer ikke gyldig. (1-99999999");
		}
		else
			
			database.createRaavareBatch(new RaavareBatchDTO(Integer.parseInt(batchNrBox.getText()), Integer.parseInt(raavareId.getText()), Integer.parseInt(supplierIdBox.getText()), Double.parseDouble(amountBox.getText())), this);
			
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create RaavareBatch");
		
	}

	@Override
	public void onSuccess(Integer result) {
		statusField.setText("Successful to create RaavareBatch");
		batchNrBox.setText("");
		raavareId.setText("");
		supplierIdBox.setText("");
		amountBox.setText("");
		
	}

}

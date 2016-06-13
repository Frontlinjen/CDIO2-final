package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
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
import cdiofinal.shared.ProduktBatchDTO;
import cdiofinal.shared.ReceptDTO;

public class NewReceptComposite extends Composite implements AsyncCallback<ReceptDTO>{
	final ReceptRPCInterfaceAsync database = (ReceptRPCInterfaceAsync)GWT.create(ReceptRPCInterface.class);
	interface NewReceptCompositeUiBinder extends UiBinder<Widget, NewReceptComposite> {}
	private static NewReceptCompositeUiBinder newReceptUiBinder = GWT.create(NewReceptCompositeUiBinder.class);
	
	private NewElementCreatedCallback<ReceptDTO> callback;
	
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField Label statusField;
	public NewReceptComposite(NewElementCreatedCallback<ReceptDTO> callback) {
		initWidget(newReceptUiBinder.createAndBindUi(this));
		this.callback = callback;
	}

	FieldVerifier f = new FieldVerifier();
		
	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getText()))==true)
		{
			statusField.setText("Ikke et gyldigt id (1-99999999)");
		}
		else if(!FieldVerifier.isValidName(navnBox.getText())==true)
		{
			statusField.setText("Navnet er ugyldigt. Benyt kun bogstaver, med en l�ngde mellem 2-20");
		}
		else
			
			database.createRecept(new ReceptDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create Recept");
		
	}

	@Override
	public void onSuccess(ReceptDTO result) {
		statusField.setText("Succesfully created Recept");
		idBox.setValue("");
		navnBox.setValue("");
		callback.onElementCreated(result);
	}
}

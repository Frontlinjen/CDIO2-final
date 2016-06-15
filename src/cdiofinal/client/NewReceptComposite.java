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
		if(FieldVerifier.isNumber(idBox.getText())==false || FieldVerifier.isValidId(Integer.parseInt(idBox.getText()))==false)
		{
			statusField.setText("Id'et skal v\u00E6re en integer mellem 1-99999999!");
		}		
		if(FieldVerifier.isValidName(navnBox.getText())==false)
		{
			statusField.setText("Navnet er ugyldigt. Benyt kun bogstaver, med en l\u00E6ngde mellem 2-20");
		}
		else
			
			database.createRecept(new ReceptDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Kunne ikke oprette Recept da " + ErrorHandling.getError(caught));
		
	}

	@Override
	public void onSuccess(ReceptDTO result) {
		statusField.setText("Recepten oprettet!");
		idBox.setValue("");
		navnBox.setValue("");
		callback.onElementCreated(result);
	}
}

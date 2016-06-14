package cdiofinal.client;

import org.eclipse.jetty.server.handler.ErrorHandler;

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

import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.ProduktBatchDTO;
import cdiofinal.shared.RaavareDTO;

public class NewLeverandoerComposite extends Composite implements AsyncCallback<LeverandoerDTO>{
	final LeverandoerRPCInterfaceAsync database = (LeverandoerRPCInterfaceAsync)GWT.create(LeverandoerRPCInterface.class);
	interface NewLeverandoerUIBinder extends UiBinder<Widget, NewLeverandoerComposite> {}
	private static NewLeverandoerUIBinder newLeverandoerUiBinder = GWT.create(NewLeverandoerUIBinder.class);

	private NewElementCreatedCallback<LeverandoerDTO> callback;
	
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField Label statusField;
	
	public NewLeverandoerComposite(NewElementCreatedCallback<LeverandoerDTO> callback) {
		initWidget(newLeverandoerUiBinder.createAndBindUi(this));
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
			statusField.setText("ID skal v�re en integer!");
			return;
		}
		if(!FieldVerifier.isValidId(id)==true)
		{
			statusField.setText("Id'et er ikke inde for intervallet 1-99999999");
		}
		else if (!FieldVerifier.isValidName(navnBox.getValue())==true)
		{
			statusField.setText("Navnet skal v�re mellem 2-20 karaktere. Benyt kun bogstaver");
		}
		else

			database.createLeverandoer(new LeverandoerDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		
		statusField.setText("Failed to create leverandoer " + ErrorHandling.getError(caught));
		
	}
	@Override
	public void onSuccess(LeverandoerDTO result) {
		statusField.setText("Successfully created Leverandoer");		
		idBox.setValue("");
		navnBox.setValue("");
		callback.onElementCreated(result);

		
	}
	
}
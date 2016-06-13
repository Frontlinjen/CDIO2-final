package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.ProduktBatchDTO;


public class NewProduktbatchComposite extends Composite implements AsyncCallback<ProduktBatchDTO> {
	final ProduktBatchRPCInterfaceAsync database = (ProduktBatchRPCInterfaceAsync)GWT.create(ProduktBatchRPCInterface.class);
	interface NewProduktbatchUIBinder extends UiBinder<Widget, NewProduktbatchComposite> {}
	private static NewProduktbatchUIBinder newProduktBatchUiBinder = GWT.create(NewProduktbatchUIBinder.class);
	
	private NewElementCreatedCallback<ProduktBatchDTO> callback;
	
	@UiField TextBox idBox;
	@UiField ListBox statusBox;
	@UiField TextBox recIdBox;
	@UiField Label statusField;
	public NewProduktbatchComposite(NewElementCreatedCallback<ProduktBatchDTO> callback) {
		initWidget(newProduktBatchUiBinder.createAndBindUi(this));
		this.callback = callback;
		
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if (!FieldVerifier.isValidId(Integer.parseInt(idBox.getText()))) 
		{
			statusField.setText("Id'et er ugyldigt. (1-99999999");
		}
		else 
			
			database.createProduktBatch(new ProduktBatchDTO(Integer.parseInt(idBox.getText()), statusBox.getTabIndex(), Integer.parseInt(recIdBox.getText())), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create leverandoer" + ErrorHandling.getError(caught));
		
	}

	@Override
	public void onSuccess(ProduktBatchDTO result) {
		idBox.setText("");
		statusBox.setTabIndex(0);
		recIdBox.setText("");
		callback.onElementCreated(result);
	}

}

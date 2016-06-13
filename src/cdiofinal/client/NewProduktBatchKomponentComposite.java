package cdiofinal.client;



import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.ProduktBatchKompDTO;


public class NewProduktBatchKomponentComposite extends Composite implements AsyncCallback<ProduktBatchKompDTO>{
	final ProduktBatchKompRPCInterfaceAsync database = (ProduktBatchKompRPCInterfaceAsync)GWT.create(ProduktBatchKompRPCInterface.class);
	interface NewProduktBatchKomponentUIBinder extends UiBinder<Widget, NewProduktBatchKomponentComposite>{}
	private static NewProduktBatchKomponentUIBinder newProduktBatchKomponentUIBinder = GWT.create(NewProduktBatchKomponentUIBinder.class);
	
	private NewElementCreatedCallback<ProduktBatchKompDTO> callback;
	
	@UiField IntegerBox pb_idBox;
	@UiField TextBox rb_id;
	@UiField TextBox tara;
	@UiField TextBox netto;
	@UiField TextBox cprBox;
	@UiField Label statusField;
	
	public NewProduktBatchKomponentComposite(NewElementCreatedCallback<ProduktBatchKompDTO> callback) {
		initWidget(newProduktBatchKomponentUIBinder.createAndBindUi(this));
		this.callback = callback;
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws NumberFormatException, DALException
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(pb_idBox.getText()))==true)
		{
			statusField.setText("Id'et er ugyldigt. (1-99999999");
		}
		else

			database.createProduktBatchKomp(new ProduktBatchKompDTO(Integer.parseInt(pb_idBox.getText()), Integer.parseInt(rb_id.getText()), Double.parseDouble(tara.getText()), Double.parseDouble(netto.getText()), cprBox.getText()), Token.getToken(), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create ProduktBatchKomponent" + ErrorHandling.getError(caught));
		
	}

	@Override
	public void onSuccess(ProduktBatchKompDTO result) {
		statusField.setText("Successfully created ProduktBatchKomponent");
		pb_idBox.setText("");
		rb_id.setValue("");
		tara.setValue("");
		netto.setValue("");
		cprBox.setValue("");
		callback.onElementCreated(result);
	}
	
}

package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.AnsatDTO;


public class pbRapportRaavareComposite extends Composite {
	private final AnsatRPCInterfaceAsync database = (AnsatRPCInterfaceAsync)GWT.create(AnsatRPCInterface.class);
	interface pbRapportRaavareUIBinder extends UiBinder<Widget, pbRapportRaavareComposite> {}
	private static pbRapportRaavareUIBinder pbRapportRaavareUIBinder = GWT.create(pbRapportRaavareUIBinder.class);
	
	private NewElementCreatedCallback<AnsatDTO> callback;
	
	@UiField TextBox del;
	@UiField TextBox amount;
	@UiField TextBox tolerance;
	@UiField TextBox tare;
	@UiField TextBox netto;
	@UiField TextBox batch;
	@UiField TextBox opr;
	@UiField TextBox terminal;
	public pbRapportRaavareComposite(NewElementCreatedCallback<AnsatDTO> callback) {
		initWidget(pbRapportRaavareUIBinder.createAndBindUi(this));
		this.callback = callback;
	}
	
}
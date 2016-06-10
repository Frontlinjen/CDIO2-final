package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;

public class NewLeverandoerComposite extends Composite implements AsyncCallback<Integer>{
	final LeverandoerRPCInterfaceAsync database = (LeverandoerRPCInterfaceAsync)GWT.create(LeverandoerRPCInterface.class);
	interface NewLeverandoerUIBinder extends UiBinder<Widget, NewLeverandoerComposite> {}
	private static NewLeverandoerUIBinder newLeverandoerUiBinder = GWT.create(NewLeverandoerUIBinder.class);
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField Label statusField;
	public NewLeverandoerComposite() {
		initWidget(newLeverandoerUiBinder.createAndBindUi(this));
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getValue())))
		{
			System.out.println("Id'et er ikke inde for intervallet 1-99999999");
		}
		else if (!FieldVerifier.isValidName(navnBox.getValue()))
		{
			System.out.println("Navnet skal være mellem 2-20 karaktere. Benyt kun bogstaver");
		}
		else

			database.createLeverandoer(new LeverandoerDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create leverandoer");
		
	}

	@Override
	public void onSuccess(Integer result) {
		statusField.setText("Successfully created Leverandoer");		
		idBox.setValue("");
		navnBox.setValue("");

		
	}
	
}
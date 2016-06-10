package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ReceptDTO;

public class NewReceptComposite extends Composite implements AsyncCallback<Integer>{
	final ReceptRPCInterfaceAsync database = (ReceptRPCInterfaceAsync)GWT.create(ReceptRPCInterface.class);
	interface NewReceptCompositeUiBinder extends UiBinder<Widget, NewReceptComposite> {}
	private static NewReceptCompositeUiBinder newReceptUiBinder = GWT.create(NewReceptCompositeUiBinder.class);
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField TextBox statusField;
	public NewReceptComposite() {
		initWidget(newReceptUiBinder.createAndBindUi(this));
	}

	FieldVerifier f = new FieldVerifier();
		
	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws DALException
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getText()))==true)
		{
			System.out.println("Ikke et gyldigt id (1-99999999");
		}
		else if(!FieldVerifier.isValidName(navnBox.getText())==true)
		{
			System.out.println("Navnet er ugyldigt. Benyt kun bogstaver, med en l�ngde mellem 2-20");
		}
		else
			
			database.createRecept(new ReceptDTO(Integer.parseInt(idBox.getText()), navnBox.getText()), this);
	}

	@Override
	public void onFailure(Throwable caught) {
		statusField.setText("Failed to create Recept");
		
	}

	@Override
	public void onSuccess(Integer result) {
		statusField.setText("Succesfully created Recept");
		idBox.setValue("");
		navnBox.setValue("");
	}
}

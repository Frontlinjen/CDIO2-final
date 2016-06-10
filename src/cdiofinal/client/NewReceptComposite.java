package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.server.DALException;
import cdiofinal.shared.FieldVerifier;

public class NewReceptComposite extends Composite {
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
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getText())))
		{
			System.out.println("Ikke et gyldigt id (1-99999999");
		}
		else if(!FieldVerifier.isValidName(navnBox.getText()))
		{
			System.out.println("Navnet er ugyldigt. Benyt kun bogstaver, med en længde mellem 2-20");
		}
//		else
//		tilføj recept
	}
}

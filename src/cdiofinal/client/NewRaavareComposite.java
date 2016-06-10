package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.protobuf.UnknownFieldSet.Field;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.FieldVerifier;

public class NewRaavareComposite extends Composite{
	interface NewRaavareUIBinder extends UiBinder<Widget, NewRaavareComposite>{}
	private static NewRaavareUIBinder newRaavareUIBinder = GWT.create(NewRaavareUIBinder.class);
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField Label statusField;
	
	public NewRaavareComposite() {
		initWidget(newRaavareUIBinder.createAndBindUi(this));
	}

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(idBox.getValue())))
		{
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		if(!FieldVerifier.isValidName(navnBox.getText()))
		{
			System.out.println("Navnet er ugyldigt. Benyt kun bogstaver, med en længde mellem 2-20 karaktere");
		}
//		else
//		tilføj råvare
	}
}

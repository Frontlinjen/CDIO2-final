package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.client.AnsatRPCInterface;
import cdiofinal.client.AnsatRPCInterfaceAsync;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;

public class NewUserComposite extends Composite implements AsyncCallback<AnsatDTO>{
	final AnsatRPCInterfaceAsync database = (AnsatRPCInterfaceAsync)GWT.create(AnsatRPCInterface.class);
	interface NewUserUIBinder extends UiBinder<Widget, NewUserComposite> {}
	private static NewUserUIBinder newUserUiBinder = GWT.create(NewUserUIBinder.class);
	
	private NewElementCreatedCallback<AnsatDTO> callback;
	
	@UiField TextBox cprBox;
	@UiField TextBox nameBox;
	@UiField TextBox iniBox;
	@UiField ListBox rankBox;
	@UiField PasswordTextBox passBox;
	@UiField Label statusField;
	public NewUserComposite(NewElementCreatedCallback<AnsatDTO> callback) {
		initWidget(newUserUiBinder.createAndBindUi(this));
		this.callback = callback;
	}
	
	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e)
	{
		if(!FieldVerifier.isValidCpr(Integer.parseInt(cprBox.getText())))
		{
			statusField.setText("CPR nummeret er ugyldigt.");
		}
		else if(!FieldVerifier.isValidName(nameBox.getValue()))
		{
			statusField.setText("Navnet er ugyldigt. Benyt kun bogstaver, med en l�ngde mellem 2-20 karaktere");
		}
		else if(!FieldVerifier.isValidIni(iniBox.getValue()))
		{
			statusField.setText("Initialerne er ugyldige. Benyt kun bogstaver, med en l�ngde mellem 2-20 karaktere");
		}
		else if(!FieldVerifier.isValidPassword(passBox.getValue()))
		{
			statusField.setText("Passwordet er ugyldigt, hold det mellem 3-8 karakterer");
		}
		else
		{
			AnsatDTO newDTO = new AnsatDTO(cprBox.getText(), nameBox.getText(), iniBox.getText(), passBox.getText(), rankBox.getSelectedIndex());
			database.createAnsat(newDTO, Token.getToken(), this);
		}
		
	}
		@Override
		public void onFailure(Throwable caught) {
			statusField.setText("Failed to create user" + ErrorHandling.getError(caught));
			
		}
	
		@Override
		public void onSuccess(AnsatDTO result) {
			statusField.setText("Successfully created user");		
			cprBox.setValue("");
			nameBox.setValue("");
			iniBox.setValue("");
			passBox.setValue("");
			rankBox.setItemSelected(0, true);;
			callback.onElementCreated(result);
			
		}

}

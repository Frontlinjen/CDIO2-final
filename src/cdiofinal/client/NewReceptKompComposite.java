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
import cdiofinal.shared.ReceptKompDTO;

public class NewReceptKompComposite extends Composite implements AsyncCallback<ReceptKompDTO>{
	
		final ReceptKomponentRPCInterfaceAsync database = (ReceptKomponentRPCInterfaceAsync)GWT.create(ReceptKomponentRPCInterface.class);
		interface NewReceptKompCompositeUiBinder extends UiBinder<Widget, NewReceptKompComposite> {}
		private static NewReceptKompCompositeUiBinder NewReceptKompUiBinder = GWT.create(NewReceptKompCompositeUiBinder.class);
		
		private NewElementCreatedCallback<ReceptKompDTO> callback;
		
		@UiField TextBox re_idBox;
		@UiField TextBox raa_idBox;
		@UiField TextBox nom_nettoBox;
		@UiField TextBox tolBox;
		@UiField Label statusField;
		public NewReceptKompComposite(NewElementCreatedCallback<ReceptKompDTO> callback) {
			initWidget(NewReceptKompUiBinder.createAndBindUi(this));
			this.callback = callback;
		}

		FieldVerifier FV = new FieldVerifier();
			
		@UiHandler("submitButton")
		public void onSubmitPressed(ClickEvent e)
		{
			if(!FieldVerifier.isValidId(Integer.parseInt(re_idBox.getText()))==true)
			{
				statusField.setText("Ikke et gyldigt id (1-99999999)");
			}
			else if(!FieldVerifier.isValidId(Integer.parseInt(raa_idBox.getText()))==true)
			{
				statusField.setText("Ikke et gyldigt id (1-99999999)");
			}
			else if(!FieldVerifier.isValidNomNetto(Double.parseDouble(nom_nettoBox.getText()))==true)
			{
				statusField.setText("Ikke en gyldig nettovaegt (0.05-20.0)");
			}
			else if(!FieldVerifier.isValidTolerance(Double.parseDouble(tolBox.getText()))==true)
			{
				statusField.setText("Ikke en gyld tolerance (0.1-10)");
			}
			else
				
				database.createReceptKomp(new ReceptKompDTO(Integer.parseInt(re_idBox.getText()),Integer.parseInt(raa_idBox.getText()), Double.parseDouble(nom_nettoBox.getText()), Double.parseDouble(tolBox.getText())), Token.getToken(), this);
		}

		@Override
		public void onFailure(Throwable caught) {
			statusField.setText("Fejlede i at oprette ReceptKomp");
			
		}

		@Override
		public void onSuccess(ReceptKompDTO result) {
			statusField.setText("ReceptKomp oprettet");
			re_idBox.setValue("");
			raa_idBox.setValue("");
			nom_nettoBox.setValue("");
			tolBox.setValue("");
			callback.onElementCreated(result);
		}

}

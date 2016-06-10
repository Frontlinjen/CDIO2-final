package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.server.DALException;
import cdiofinal.server.MySQLLeverandoerDAO;
import cdiofinal.server.MySQLRaavareDAO;

import cdiofinal.shared.FieldVerifier;


public class NewRaavareBatchComposite extends Composite{
	interface NewRaavareBatchUIBinder extends UiBinder<Widget, NewRaavareBatchComposite> {}
	private static NewRaavareBatchUIBinder newRaavareBatchUiBinder = GWT.create(NewRaavareBatchUIBinder.class);
	@UiField IntegerBox batchNrBox;
	@UiField IntegerBox raavareId;
	@UiField IntegerBox supplierIdBox;
	@UiField IntegerBox amountBox;
	@UiField Label statusField;
	NewRaavareBatchComposite() {
		initWidget(newRaavareBatchUiBinder.createAndBindUi(this));
	}
	
	FieldVerifier f = new FieldVerifier();

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws DALException
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(batchNrBox.getText())))
		{
			System.out.println("Batch ID'en eksisterer ikke gyldig. (1-99999999");
		}
//		else
//		tilføj raavareBatch			
	}

}

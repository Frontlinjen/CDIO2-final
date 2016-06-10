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
	MySQLRaavareDAO rdao = new MySQLRaavareDAO();
	MySQLLeverandoerDAO ldao = new MySQLLeverandoerDAO();
	
	
	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws DALException
	{
		if(rdao.getRaavare(raavareId.getValue()).getRaavareId() != Integer.parseInt(raavareId.getText()))
		{
			System.out.println("Råvaren eksisterer ikke i databasen");
		}
		else if(ldao.getLeverandoer(supplierIdBox.getValue()).getLeverandoerId() != Integer.parseInt(supplierIdBox.getText()))
		{
			System.out.println("Leverandøren eksisterer ikke i databasen");
		}
		else if(!FieldVerifier.isValidId(Integer.parseInt(batchNrBox.getText())))
		{
			System.out.println("Batch ID'en eksisterer ikke gyldig. (1-99999999");
		}
//		else
//		tilføj raavareBatch			
	}

}

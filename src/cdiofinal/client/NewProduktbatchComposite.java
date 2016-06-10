package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.server.MySQLReceptDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;


public class NewProduktbatchComposite extends Composite{
	final ProduktBatchRPCInterfaceAsync database = (ProduktBatchRPCInterfaceAsync)GWT.create(ProduktBatchRPCInterface.class);
	interface NewProduktbatchUIBinder extends UiBinder<Widget, NewProduktbatchComposite> {}
	private static NewProduktbatchUIBinder newProduktBatchUiBinder = GWT.create(NewProduktbatchUIBinder.class);
	@UiField TextBox idBox;
	@UiField ListBox statusBox;
	@UiField TextBox recIdBox;
	@UiField Label statusField;
	public NewProduktbatchComposite() {
		initWidget(newProduktBatchUiBinder.createAndBindUi(this));
	}

	MySQLReceptDAO rdao = new MySQLReceptDAO();	
	
	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws NumberFormatException, DALException
	{
		if (!FieldVerifier.isValidId(Integer.parseInt(idBox.getText()))) {
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}

//		else
//		tilf�j produktbatch
	}

}

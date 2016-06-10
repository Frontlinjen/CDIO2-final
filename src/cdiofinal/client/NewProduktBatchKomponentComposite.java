package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.server.DALException;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.server.MySQLRaavareBatchDAO;
import cdiofinal.shared.FieldVerifier;

public class NewProduktBatchKomponentComposite extends Composite{
	interface NewProduktBatchKomponentUIBinder extends UiBinder<Widget, NewProduktBatchKomponentComposite>{}
	private static NewProduktBatchKomponentUIBinder newProduktBatchKomponentUIBinder = GWT.create(NewProduktBatchKomponentUIBinder.class);
	@UiField IntegerBox pb_idBox;
	@UiField TextBox rb_id;
	@UiField TextBox tara;
	@UiField TextBox netto;
	@UiField TextBox cprBox;
	public NewProduktBatchKomponentComposite() {
		initWidget(newProduktBatchKomponentUIBinder.createAndBindUi(this));
	}
	
	MySQLRaavareBatchDAO rdao = new MySQLRaavareBatchDAO();
	MySQLAnsatDAO adao = new MySQLAnsatDAO();

	@UiHandler("submitButton")
	public void onSubmitPressed(ClickEvent e) throws NumberFormatException, DALException
	{
		if(!FieldVerifier.isValidId(Integer.parseInt(pb_idBox.getText())))
		{
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}

//		else
//		tilføj ProduktBatchKomponent
	}
	
}

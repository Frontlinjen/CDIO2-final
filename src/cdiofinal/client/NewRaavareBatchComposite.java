package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


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
	

}

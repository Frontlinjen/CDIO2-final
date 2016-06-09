package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewRaavareComposite extends Composite{
	interface NewRaavareUIBinder extends UiBinder<Widget, NewRaavareComposite>{}
	private static NewRaavareUIBinder newRaavareUIBinder = GWT.create(NewRaavareUIBinder.class);
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	
	public NewRaavareComposite() {
		initWidget(newRaavareUIBinder.createAndBindUi(this));
	}
}

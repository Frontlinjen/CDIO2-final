package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewReceptComposite extends Composite {
	interface NewReceptCompositeUiBinder extends UiBinder<Widget, NewReceptComposite> {}
	private static NewReceptCompositeUiBinder newReceptUiBinder = GWT.create(NewReceptCompositeUiBinder.class);
	@UiField TextBox idBox;
	@UiField TextBox navnBox;
	@UiField TextBox statusField;
	public NewReceptComposite() {
		initWidget(newReceptUiBinder.createAndBindUi(this));
	}
	@UiHandler("Create")
	public void onButtonPressed(ClickEvent e)
	{
		
	}
}

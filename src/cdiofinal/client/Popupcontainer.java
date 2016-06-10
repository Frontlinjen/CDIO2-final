package cdiofinal.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Popupcontainer extends Composite {

	
	@UiField SimplePanel container;

	public Popupcontainer(Widget w)
	{
		container.add(w);
	}
	@UiHandler("CloseButton")
	public void onClose(ClickEvent e)
	{
		container.clear();
	}
}

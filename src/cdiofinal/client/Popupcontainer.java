package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Popupcontainer extends Composite {
	interface PopupContainerUiBinder extends UiBinder<Widget, Popupcontainer> {}
	private static PopupContainerUiBinder popupContainerUiBinder = GWT.create(PopupContainerUiBinder.class);
	DialogBox d = new DialogBox();
	@UiField SimplePanel container;

	public Popupcontainer(Widget w)
	{
		initWidget(popupContainerUiBinder.createAndBindUi(this));
		container.add(w);
		d.setPopupPosition(650 , 200);
		d.add(this);
	}
	public void show()
	{
		d.show();
	}
	public void hide()
	{
		d.hide();
	}
	@UiHandler("CloseButton")
	public void onClose(ClickEvent e)
	{
		hide();
	}
}

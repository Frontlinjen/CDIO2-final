package cdiofinal.client;

import cdiofinal.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CDIO_FINAL implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		RootPanel container = RootPanel.get("options");
		String[] buttons = {"List users", "Create new user", "Tilføj råvare"};
		final Composite[] compositeWidgets = {new ListUsersClickHandler(), new NewUserClickhandler(), new NewUserClickhandler(), new NewRaavareClickHandler(), new NewProductBatchClickHandler(), new NewRaavareBatchClickHandler()};
		ClickHandler[] clickHandlers = new ClickHandler[compositeWidgets.length];
		for (int i = 0; i < compositeWidgets.length; i++) {
			final int constant = i;
			clickHandlers[i] = new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					RootPanel panel = RootPanel.get("contents");
					panel.clear();
					Composite widget = compositeWidgets[constant];
					panel.add(widget);
				}
				
			};
		}
		for(int i=0;i<buttons.length;++i)
		{
			PushButton t = new PushButton(buttons[i]);
			t.addClickHandler(clickHandlers[i]);
			container.add(t);
		}
	}
}

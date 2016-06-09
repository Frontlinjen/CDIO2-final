package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.client.LoginScreen.LoginUIBinder;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainPage extends Composite{
	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {}
	private static MainPageUiBinder mainPageUiBinder = GWT.create(MainPageUiBinder.class);
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	public MainPage()
	{
		initWidget(mainPageUiBinder.createAndBindUi(this));
	}
	public void onLoad() 
	{
		RootPanel container = RootPanel.get("options");
		String[] buttons = {"List users", "Create new user", "Tilf�j r�vare"};
		final Composite[] compositeWidgets = {new ListUsersClickHandler(), 
				new NewUserComposite(), 
				new NewUserComposite(), 
				new NewRaaLevClickHandler(), 
				new NewRaavareBatchClickHandler()};
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
package cdiofinal.client;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;

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

	int rank;
	
	public int getRank(){
		return this.rank;
	}
	
	public MainPage(int rank)
	{
		this.rank = rank;
		initWidget(mainPageUiBinder.createAndBindUi(this));
	}
	@Override
	public void onLoad() 
	{
		final Composite[] compositeWidgets = new Composite[6];
		switch(rank){
			case 3: 
			{
				compositeWidgets[0] = new ListUsersComposite();
			}
			case 2:
			{
				compositeWidgets[1] = new ListRaavareComposite();
				compositeWidgets[2] = new ListLeverandoerComposite();
				compositeWidgets[3] = new ListReceptComposite();
			}
			case 1:
			{
				compositeWidgets[4] = new ListProduktBatchComposite();
				compositeWidgets[5] = new ListRaavareBatchComposite();
			}
		}
		RootPanel container = RootPanel.get("options");
		String[] buttons = {
				"List Users", 
				"List R\u00E5varer", 
				"List Leverand\u00F8erer",
				"List Recepter",
				"List Produktbatches", 
				"List R\u00E5varerbatches"};
		ClickHandler[] clickHandlers = new ClickHandler[compositeWidgets.length];
		for (int i = 0; i < compositeWidgets.length; i++) {
			if(compositeWidgets[i] == null){
				continue;
			}
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
			PushButton t = new PushButton(buttons[i]);
			t.addClickHandler(clickHandlers[i]);
			container.add(t);
		}
	}
}
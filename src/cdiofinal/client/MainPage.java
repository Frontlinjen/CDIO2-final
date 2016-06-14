package cdiofinal.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

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

	private int rank;
	private String name;
	@UiField
	public Label greeter;
	public int getRank(){
		return this.rank;
	}
	
	public MainPage(int rank, String name)
	{
		this.rank = rank;
		initWidget(mainPageUiBinder.createAndBindUi(this));
		this.name = name;
		
		
	}
	@Override
	public void onLoad() 
	{
		greeter.setText("Greetings, " + name + "!");
		
		
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
		final RootPanel container = RootPanel.get("options");
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
		//Sets up the logout button:
				PushButton logout = new PushButton("Logout");
				logout.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Token.setToken("");
						container.clear();
						RootPanel.get("contents").clear();
						RootPanel.get("contents").add(new LoginScreen());
						
					}
				});
				container.add(logout);
				logout.getElement().getStyle().setProperty("position", "absolute");
				logout.getElement().getStyle().setProperty("bottom", "0px");
				logout.getElement().getStyle().setProperty("left", "0px");
				logout.getElement().getStyle().setProperty("padding", "2px");
	}
}
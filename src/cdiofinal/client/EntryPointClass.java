package cdiofinal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class EntryPointClass implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get("contents").add(new LoginScreen());
	}

}

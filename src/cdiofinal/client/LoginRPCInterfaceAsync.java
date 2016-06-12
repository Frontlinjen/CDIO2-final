package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginRPCInterfaceAsync {
	public void getLoginToken(int cpr, String password, AsyncCallback<String> callback);
}

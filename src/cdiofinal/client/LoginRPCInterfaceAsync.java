package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.TokenRank;

public interface LoginRPCInterfaceAsync {
	public void getLoginToken(String cpr, String password, AsyncCallback<TokenRank> callback);
}

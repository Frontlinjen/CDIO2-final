package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.TokenRank;

public interface LoginRPCInterfaceAsync {
	public void getLoginToken(long cpr, String password, AsyncCallback<TokenRank> callback);
}

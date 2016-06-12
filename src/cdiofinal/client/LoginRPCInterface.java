package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.DALException;

@RemoteServiceRelativePath("login")
public interface LoginRPCInterface {
	String getLoginToken(int cpr, String password) throws DALException, Exception;
}

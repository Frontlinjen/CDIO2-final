package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.lang.Exception;

import cdiofinal.shared.DALException;
import cdiofinal.shared.TokenRank;

@RemoteServiceRelativePath("login")
public interface LoginRPCInterface extends RemoteService{
	TokenRank getLoginToken(String cpr, String password) throws DALException, Exception;
}

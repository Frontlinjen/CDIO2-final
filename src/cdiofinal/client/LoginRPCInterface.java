package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.lang.Exception;
import cdiofinal.shared.DALException;

@RemoteServiceRelativePath("login")
public interface LoginRPCInterface extends RemoteService{
	String getLoginToken(int cpr, String password) throws DALException, Exception;
}

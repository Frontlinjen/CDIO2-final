package cdiofinal.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.LoginRPCInterface;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

public class LoginRPCServlet extends RemoteServiceServlet implements LoginRPCInterface{

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	@Override
	public String getLoginToken(int cpr, String password) throws Exception{
		AnsatDTO user;
		try{
			user = database.getAnsat(Integer.toString(cpr));
		} catch(DALException e){
			throw new DALException("Brugeren kunne ikke findes");
		}
		if(user.getPassword().equals(password))
		{
			return TokenHandler.getInstance().createToken(user.getCpr());
		}
		else
		{
			throw new Exception("Brugernavn eller password forkert!");
		}	
	}

}

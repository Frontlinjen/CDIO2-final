package cdiofinal.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.LoginRPCInterface;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.TokenRank;

public class LoginRPCServlet extends RemoteServiceServlet implements LoginRPCInterface{

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	@Override
	public TokenRank getLoginToken(long cpr, String password) throws Exception{
		AnsatDTO user;
		try{
			user = database.getAnsat(Long.toString(cpr));
		} catch(DALException e){
			throw new DALException("Brugeren kunne ikke findes");
		}
		if(user.getPassword().equals(password))
		{
			TokenRank tr = new TokenRank();
			tr.setToken(TokenHandler.getInstance().createToken(user.getCpr()));
			tr.setRank(user.getTitel());
			return tr;
		}
		else
		{
			throw new Exception("Brugernavn eller password forkert!");
		}	
	}

}

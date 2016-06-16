package cdiofinal.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.LoginRPCInterface;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.InsufficientAccessException;
import cdiofinal.shared.TokenRank;

public class LoginRPCServlet extends RemoteServiceServlet implements LoginRPCInterface{

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	@Override
	public TokenRank getLoginToken(String cpr, String password) throws Exception{
		AnsatDTO user;
		try{
			user = database.getAnsat(cpr);
			if(user==null)
			{
				throw new DALException();
			}
		} catch(DALException e){
			throw new DALException("Brugeren kunne ikke findes");
		}
		if(user.getTitel() < 0) //Inactive users
		{
			throw new InsufficientAccessException("Denne konto er ikke længere aktiv");
		}
		if(user.getPassword().equals(password))
		{
			TokenRank tr = new TokenRank();
			tr.setToken(TokenHandler.getInstance().createToken(user.getCpr()));
			tr.setRank(user.getTitel());
			tr.setName(user.getOprNavn());
			return tr;
		}
		else
		{
			throw new InsufficientAccessException("Brugernavn eller password forkert!");
		}	
	}

}

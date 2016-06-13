package cdiofinal.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.InsufficientAccessException;

public class ValidationServlet extends RemoteServiceServlet{

	private static final long serialVersionUID = 1L;
	private MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	public boolean isValid(String token, int rank) throws Exception{
		if(TokenHandler.getInstance().validateToken(token)==null)
			throw new InsufficientAccessException("Invalid token. Please refresh the page and login again.");
		else if(database.getAnsat(TokenHandler.getInstance().getUserID(token)).getTitel() < 3)
		{
			throw new InsufficientAccessException("You dont have the required access to view this page");
		}
		return true;
	}
	
	public String gettingError(String word) throws DALException{
		throw new DALException("An error occoured when getting a " + word + " Please contact your sysadmin");
	}
	
	public String gettingListError(String word) throws DALException{
		throw new DALException("An error occoured when getting " + word + "list. Please contact your sysadmin.");
	}
	
	public String creatingError(String word) throws DALException{
		throw new DALException("An error occoured when creating a new " + word + ". Please contact your sysadmin.");
	}
	
	public String updatingError(String word) throws DALException{
		throw new DALException("An error occoured when updating a " + word + ". Please contact your sysadmin.");
	}
	
	public String deletingError(String word) throws DALException{
		throw new DALException("An error occoured when deleting a " + word + ". Please contact your sysadmin.");
	}
}

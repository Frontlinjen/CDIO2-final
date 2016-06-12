package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.DALException;
import cdiofinal.client.AnsatRPCInterface;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.InsufficientAccessException;

public class AnsatRPCServlet extends RemoteServiceServlet implements AnsatRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	
	@Override
	public AnsatDTO getAnsat(String cpr, String token) throws Exception {
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new InsufficientAccessException("Invalid token");
			return database.getAnsat(cpr);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a user. Please contact your sysadmin.");
		}
	}

	@Override
	public AnsatDTO[] getAnsatList(String token) throws DALException{
					try {
						if(TokenHandler.getInstance().validateToken(token)==null)
							throw new DALException("Invalid token");
						List<AnsatDTO> ansatte = database.getAnsatList();
						AnsatDTO[] ansatteArray = new AnsatDTO[ansatte.size()];
						return ansatte.toArray(ansatteArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting ansat list. Please contact your sysadmin.");
					}			
	}

	@Override
	public AnsatDTO createAnsat(AnsatDTO ans, String token) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
		if(database.createAnsat(ans)!=0)
			return ans;
		} catch (DALException e){
			throw new DALException("An error occoured when creating a new user. Please contact your sysadmin.");
		}
		return null;
	}

	@Override
	public Integer updateAnsat(AnsatDTO ans, String token) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			return database.updateAnsat(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a user. Please contact your sysadmin.");
			}
			return 0;
		
	}

	@Override
	public Integer deleteAnsat(AnsatDTO ans, String token) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			return database.deleteAnsat(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when deleting a user. Please contact your sysadmin.");
			}
			return 0;
		
	}
	
}

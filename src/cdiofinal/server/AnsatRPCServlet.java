package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.DALException;
import cdiofinal.client.AnsatRPCInterface;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;

public class AnsatRPCServlet extends RemoteServiceServlet implements AnsatRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	
	@Override
	public AnsatDTO getAnsat(String cpr) throws DALException {
		try {
			return database.getAnsat(cpr);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a user. Please contact your sysadmin.");
		}
	}

	@Override
	public AnsatDTO[] getAnsatList() throws DALException{
					try {
						List<AnsatDTO> ansatte = database.getAnsatList();
						AnsatDTO[] ansatteArray = new AnsatDTO[ansatte.size()];
						return ansatte.toArray(ansatteArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting ansat list. Please contact your sysadmin.");
					}			
	}

	@Override
	public Integer createAnsat(AnsatDTO ans) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
		return database.createAnsat(ans);
		} catch (DALException e){
			throw new DALException("An error occoured when creating a new user. Please contact your sysadmin.");
		}
		return 0;
	}

	@Override
	public Integer updateAnsat(AnsatDTO ans) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
			return database.updateAnsat(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a user. Please contact your sysadmin.");
			}
			return 0;
		
	}

	@Override
	public Integer deleteAnsat(AnsatDTO ans) throws DALException{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
		try {
			return database.deleteAnsat(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when deleting a user. Please contact your sysadmin.");
			}
			return 0;
		
	}
	
}

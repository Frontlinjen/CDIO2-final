package cdiofinal.server;
import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.client.AnsatRPCInterface;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.server.ValidationServlet;

public class AnsatRPCServlet extends ValidationServlet implements AnsatRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();


	@Override
	public AnsatDTO getAnsat(String cpr, String token) throws Exception {
		try {
			if(isValid(token, 3))
				return database.getAnsat(cpr);
		} catch (DALException e) {
			throw new DALException(gettingError("user"));
		}
		return null;
	}

	@Override
	public AnsatDTO[] getAnsatList(String token) throws Exception{
		try {
			if(isValid(token, 3)){
				List<AnsatDTO> ansatte = database.getAnsatList();
				AnsatDTO[] ansatteArray = new AnsatDTO[ansatte.size()];
				return ansatte.toArray(ansatteArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("user"));
		}
		return null;			
	}

	@Override
	public AnsatDTO createAnsat(AnsatDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
			try {
				if(isValid(token, 3)){
					if(database.createAnsat(ans)!=0)
						return ans;
				}
			} catch (DALException e){
				throw new DALException(creatingError("user"));
			}
		return null;
	}

	@Override
	public Integer updateAnsat(AnsatDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
			try {
				if(isValid(token, 3))
					return database.updateAnsat(ans);
			} catch (DALException e){
				throw new DALException(updatingError("user"));
			}
		return null ;

	}

	@Override
	public Integer deleteAnsat(AnsatDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidCpr(Integer.parseInt(ans.getCpr()))==true || FieldVerifier.isValidName(ans.getOprNavn())==true || FieldVerifier.isValidIni(ans.getIni())==true || FieldVerifier.isValidPassword(ans.getPassword()))
			try {
				if(TokenHandler.getInstance().validateToken(token)==null)
					throw new DALException("Invalid token");
				return database.deleteAnsat(ans);
			} catch (DALException e){
				throw new DALException(deletingError("user"));
			}
		return 0;

	}

}

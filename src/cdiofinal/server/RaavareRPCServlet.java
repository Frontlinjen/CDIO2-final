package cdiofinal.server;
import java.util.List;

import cdiofinal.client.RaavareRPCInterface;
import cdiofinal.server.MySQLRaavareDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareDTO;

public class RaavareRPCServlet extends ValidationServlet implements RaavareRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareDAO database = new MySQLRaavareDAO();


	@Override
	public RaavareDTO getRaavare(int raavare_id, String token) throws Exception{
		try {
			if(isValid(token, 2))
				return database.getRaavare(raavare_id);
		} catch (DALException e) {
			throw new DALException(gettingError("raavare"));
		}
		return null;
	}

	@Override
	public RaavareDTO[] getRaavareList(String token) throws Exception{

		try {
			if(isValid(token, 2)){
				List<RaavareDTO> raavare = database.getRaavareList();
				RaavareDTO[] raavareArray = new RaavareDTO[raavare.size()];
				return raavare.toArray(raavareArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("raavare"));
		}
		return null;			
	}

	@Override
	public RaavareDTO createRaavare(RaavareDTO ans, String token) throws Exception {
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidName(ans.getRaavareNavn())==true)
			try {
				if(isValid(token, 2)){
					if(database.createRaavare(ans)!=0){
						return ans;
					}
				}
			} catch (DALException e){
				throw new DALException(creatingError("raavare"));
			}
		return null;
	}

	@Override
	public Integer updateRaavare(RaavareDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidName(ans.getRaavareNavn())==true)
			try {
				if(isValid(token, 2))
					return database.updateRaavare(ans);
			} catch (DALException e){
				throw new DALException(updatingError("raavare"));
			}
		return null;

	}

}
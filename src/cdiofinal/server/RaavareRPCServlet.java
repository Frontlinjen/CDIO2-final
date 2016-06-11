package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RaavareRPCInterface;
import cdiofinal.server.MySQLRaavareDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareDTO;

public class RaavareRPCServlet extends RemoteServiceServlet implements RaavareRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareDAO database = new MySQLRaavareDAO();
	
	
	@Override
	public RaavareDTO getRaavare(int raavare_id) throws DALException{
		try {
			return database.getRaavare(raavare_id);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a raavare. Please contact your sysadmin.");
		}
	}

	@Override
	public RaavareDTO[] getRaavareList() throws DALException{
					
					try {
						List<RaavareDTO> raavare = database.getRaavareList();
						RaavareDTO[] raavareArray = new RaavareDTO[raavare.size()];
						return raavare.toArray(raavareArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting raavare list Please contact your sysadmin.");
					}			
	}

	@Override
	public Integer createRaavare(RaavareDTO ans) throws DALException {
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidName(ans.getRaavareNavn())==true)
		try {
		return database.createRaavare(ans);
		} catch (DALException e){
			throw new DALException("An error occoured when creating a raavare. Please contact your sysadmin.");
		}
		return 0;
	}

	@Override
	public Integer updateRaavare(RaavareDTO ans) throws DALException{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidName(ans.getRaavareNavn())==true)
		try {
			return database.updateRaavare(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a raavare. Please contact your sysadmin.");
			}
			return 0;
		
	}

}
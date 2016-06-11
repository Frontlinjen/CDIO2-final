package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.ReceptRPCInterface;
import cdiofinal.server.MySQLReceptDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ReceptDTO;

public class ReceptRPCServlet extends RemoteServiceServlet implements ReceptRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLReceptDAO receptDAO = new MySQLReceptDAO();
	
	
	@Override
	public ReceptDTO getRecept(int recept_id) throws DALException{
		try {
			return receptDAO.getRecept(recept_id);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a recept. Please contact your sysadmin.");
		}
	}

	@Override
	public ReceptDTO[] getReceptList() throws DALException{
					
					try {
						List<ReceptDTO> recept = receptDAO.getReceptList();
						ReceptDTO[] receptArray = new ReceptDTO[recept.size()];
						return recept.toArray(receptArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting recept list. Please contact your sysadmin.");
					}			
	}

	@Override
	public Integer createRecept(ReceptDTO ans) throws DALException{
		if(FieldVerifier.isValidId(ans.getReceptId())==true || FieldVerifier.isValidName(ans.getReceptNavn()) == true)
		try {
		return receptDAO.createRecept(ans);
		} catch (DALException e){
			throw new DALException("An error occoured when creating a recept. Please contact your sysadmin.");
		}
		return 0;
	}

	@Override
	public Integer updateRecept(ReceptDTO ans) throws DALException{
		try {
			return receptDAO.updateRecept(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a recept. Please contact your sysadmin.");
			}
	}
}

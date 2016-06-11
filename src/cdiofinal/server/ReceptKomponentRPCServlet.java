package cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.ReceptKomponentRPCInterface;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

public class ReceptKomponentRPCServlet extends RemoteServiceServlet implements ReceptKomponentRPCInterface{
	private static final long serialVersionUID = 1L;
	MySQLReceptKompDAO receptKompDAO = new MySQLReceptKompDAO();
	
	@Override
	public ReceptKompDTO getReceptKomp(int recId, int raavareId) throws DALException{
		try {
			return receptKompDAO.getReceptKomp(recId, raavareId);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a receptkomp. Please contact your sysadmin.");
		}
	}

	@Override
	public ReceptKompDTO[] getReceptKompList() throws DALException{
		try {
			List<ReceptKompDTO> receptkomp = receptKompDAO.getReceptKompList();
			ReceptKompDTO[] receptKompArray = new ReceptKompDTO[receptkomp.size()];
			return receptkomp.toArray(receptKompArray);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting receptkomp list. Please contact your sysadmin.");
		}	
	}

	@Override
	public Integer createReceptKomp(ReceptKompDTO recKomp) throws DALException{
		try {
			return receptKompDAO.createReceptKomp(recKomp);
			} catch (DALException e){
				throw new DALException("An error occoured when creating a receptkomp. Please contact your sysadmin.");
			}
	}

	@Override
	public Integer updateReceptKomp(ReceptKompDTO recKomp) throws DALException{
		try {
			return receptKompDAO.updateReceptKomp(recKomp);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a receptkomp. Please contact your sysadmin.");
			}
	}
}

package cdiofinal.server;

import java.util.List;

import cdiofinal.client.ReceptKomponentRPCInterface;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

public class ReceptKomponentRPCServlet implements ReceptKomponentRPCInterface{
	private static final long serialVersionUID = 1L;
	MySQLReceptKompDAO receptKompDAO = new MySQLReceptKompDAO();
	
	@Override
	public ReceptKompDTO getReceptKomp(int recId, int raavareId) throws DALException{
		try {
			return receptKompDAO.getReceptKomp(recId, raavareId);
		} catch (DALException e) {
			throw e;
		}
	}

	@Override
	public ReceptKompDTO[] getReceptKompList(){
		try {
			List<ReceptKompDTO> receptkomp = receptKompDAO.getReceptKompList();
			ReceptKompDTO[] receptKompArray = new ReceptKompDTO[receptkomp.size()];
			return receptkomp.toArray(receptKompArray);
		} catch (DALException e) {
			return null;
		}	
	}

	@Override
	public Integer createReceptKomp(ReceptKompDTO recKomp) throws DALException{
		try {
			return receptKompDAO.createReceptKomp(recKomp);
			} catch (DALException e){
				throw e;
			}
	}

	@Override
	public Integer updateReceptKomp(ReceptKompDTO recKomp) throws DALException{
		try {
			return receptKompDAO.updateReceptKomp(recKomp);
			} catch (DALException e){
				throw e;
			}
	}
}

package cdiofinal.server;

import java.util.List;

import cdiofinal.client.ReceptKomponentRPCInterface;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

public class ReceptKomponentRPCServlet extends ValidationServlet implements ReceptKomponentRPCInterface{
	private static final long serialVersionUID = 1L;
	MySQLReceptKompDAO receptKompDAO = new MySQLReceptKompDAO();

	@Override
	public ReceptKompDTO getReceptKomp(int recId, int raavareId, String token) throws Exception{
		try {
			if(isValid(token, 2))
				return receptKompDAO.getReceptKomp(recId, raavareId);
		} catch (DALException e) {
			throw new DALException(gettingError("recept component"));
		}
		return null;
	}

	@Override
	public ReceptKompDTO[] getReceptKompList(String token) throws Exception{
		try {
			if(isValid(token, 2)){
				List<ReceptKompDTO> receptkomp = receptKompDAO.getReceptKompList();
				ReceptKompDTO[] receptKompArray = new ReceptKompDTO[receptkomp.size()];
				return receptkomp.toArray(receptKompArray);
			}
		} catch (DALException e) {
			throw new DALException("recept component");
		}
		return null;	
	}

	@Override
	public ReceptKompDTO createReceptKomp(ReceptKompDTO recKomp, String token) throws Exception{
		try {
			if(isValid(token, 2)){
				if(receptKompDAO.createReceptKomp(recKomp)!=0){
					return recKomp;
				}
			}
		} catch (DALException e){
			throw new DALException(creatingError("recept component"));
		}
		return null;
	}

	@Override
	public Integer updateReceptKomp(ReceptKompDTO recKomp, String token) throws Exception{
		try {
			if(isValid(token, 2))
				return receptKompDAO.updateReceptKomp(recKomp);
		} catch (DALException e){
			throw new DALException(updatingError("recept component"));
		}
		return null;
	}
}

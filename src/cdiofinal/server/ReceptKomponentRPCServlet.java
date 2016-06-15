package cdiofinal.server;

import java.util.List;

import cdiofinal.client.ReceptKomponentRPCInterface;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

public class ReceptKomponentRPCServlet extends ValidationServlet implements ReceptKomponentRPCInterface{
	private static final long serialVersionUID = 1L;
	MySQLReceptKompDAO database = new MySQLReceptKompDAO();

	@Override
	public ReceptKompDTO getReceptKomp(int recId, int raavareId, String token) throws Exception{
			if(isValid(token, 2))
				return database.getReceptKomp(recId, raavareId);
		return null;
	}

	@Override
	public ReceptKompDTO[] getReceptKompList(int recept, String token) throws Exception{
			if(isValid(token, 2)){
				List<ReceptKompDTO> receptkomp = database.getReceptKompList(recept);
				ReceptKompDTO[] receptKompArray = new ReceptKompDTO[receptkomp.size()];
				return receptkomp.toArray(receptKompArray);
			}
		return null;	
	}

	@Override
	public ReceptKompDTO createReceptKomp(ReceptKompDTO recKomp, String token) throws Exception{
		if(isValid(token, 2)){
				database.createReceptKomp(recKomp);
				return recKomp;
				}
		return null;
	}

	@Override
	public Integer updateReceptKomp(ReceptKompDTO recKomp, String token) throws Exception{
		if(isValid(token, 2)){
			return database.updateReceptKomp(recKomp);
		}
		return 0;
	}
}

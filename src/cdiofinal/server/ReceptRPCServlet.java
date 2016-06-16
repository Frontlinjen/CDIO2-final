package cdiofinal.server;
import java.util.List;

import cdiofinal.client.ReceptRPCInterface;
import cdiofinal.server.MySQLReceptDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ReceptDTO;

public class ReceptRPCServlet extends ValidationServlet implements ReceptRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLReceptDAO receptDAO = new MySQLReceptDAO();
	final String fail ="Kunne ikke %s recepten, tjek informationerne igen.";


	@Override
	public ReceptDTO getRecept(int recept_id, String token) throws Exception{
			if(isValid(token, 2))
				return receptDAO.getRecept(recept_id);
		return null;
	}

	@Override
	public ReceptDTO[] getReceptList(String token) throws Exception{
			if(isValid(token, 2)){
				List<ReceptDTO> recept = receptDAO.getReceptList();
				ReceptDTO[] receptArray = new ReceptDTO[recept.size()];
				return recept.toArray(receptArray);
			}
		return null;			
	}

	@Override
	public ReceptDTO createRecept(ReceptDTO rec, String token) throws Exception{
		if(rec.isValid())
		{
				if(isValid(token, 2)){
					receptDAO.createRecept(rec);	
					return rec;
				}
		}
		else{
			throw new DALException(String.format(fail, "oprette"));
		}
		return null;
	}

	@Override
	public Integer updateRecept(ReceptDTO rec, String token) throws Exception{
		if(rec.isValid()){
	
			if(isValid(token, 2))
				{
					return receptDAO.updateRecept(rec);
				}
		}
		else{
			throw new DALException(String.format(fail, "opdatere"));
		}
		return 0;
	}
}

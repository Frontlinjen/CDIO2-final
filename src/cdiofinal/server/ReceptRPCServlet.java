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
	public ReceptDTO createRecept(ReceptDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getReceptId())==true 
		&& FieldVerifier.isValidName(ans.getReceptNavn()) == true)
		{
				if(isValid(token, 2)){
					receptDAO.createRecept(ans);					
				}
			}
		return null;
	}

	@Override
	public Integer updateRecept(ReceptDTO ans, String token) throws Exception{
			if(isValid(token, 2))
			{
				receptDAO.updateRecept(ans);
			}
		return null;
	}
}

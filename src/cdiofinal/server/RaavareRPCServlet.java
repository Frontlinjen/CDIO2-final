package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.client.Window;

import cdiofinal.client.RaavareRPCInterface;
import cdiofinal.server.MySQLRaavareDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareDTO;

public class RaavareRPCServlet extends ValidationServlet implements RaavareRPCInterface {

	private static final long serialVersionUID = 1L;
	private MySQLRaavareDAO database = new MySQLRaavareDAO();
	final private String fail ="Kunne ikke %s , r\u00E5varen tjek informationerne igen.";


	@Override
	public RaavareDTO getRaavare(int raavare_id, String token) throws Exception{
		if(isValid(token, 2)){
			return database.getRaavare(raavare_id);
		}
		return null;
	}

	@Override
	public RaavareDTO[] getRaavareList(String token) throws Exception{
		if(isValid(token, 2)){
			List<RaavareDTO> raavare = database.getRaavareList();
			RaavareDTO[] raavareArray = new RaavareDTO[raavare.size()];
			return raavare.toArray(raavareArray);
		}
		return null;			
	}

	@Override
	public RaavareDTO createRaavare(RaavareDTO raa, String token) throws Exception {
		if(raa.isValid())
		{
			if(isValid(token, 2)){
				database.createRaavare(raa);
				return raa;
				}
			else
			{
				throw new DALException(String.format(fail, "oprette"));
			}
		}	
		return null;
	}

	@Override
	public Integer updateRaavare(RaavareDTO raa, String token) throws Exception{
		if(raa.isValid())
		{
			if(isValid(token, 2)){
				return database.updateRaavare(raa);
			}
		}
		else{
				throw new DALException(String.format(fail, "opdatere"));
			}
		return 0;

	}

}
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
	MySQLRaavareDAO database = new MySQLRaavareDAO();


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
	public RaavareDTO createRaavare(RaavareDTO ans, String token) throws Exception {
		if(FieldVerifier.isValidId(ans.getRaavareId())==true 
		&& FieldVerifier.isValidName(ans.getRaavareNavn())==true)
		{
			if(isValid(token, 2)){
				database.createRaavare(ans);
				}
			else
			{
				Window.alert("Kunne ikke oprette raavaren, tjek informationerne igen.");
			}
		}	
		return null;
	}

	@Override
	public Integer updateRaavare(RaavareDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true
		&& FieldVerifier.isValidName(ans.getRaavareNavn())==true)
			
			if(isValid(token, 2)){
				database.updateRaavare(ans);
			}
			else{
				Window.alert("Kunne ikke opdatere raavaren, tjek oplysningerne igen.");
			}
		return null;

	}

}
package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.client.Window;

import cdiofinal.shared.DALException;
import cdiofinal.client.LeverandoerRPCInterface;
import cdiofinal.server.MySQLLeverandoerDAO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;

public class LeverandoerRPCServlet extends ValidationServlet implements LeverandoerRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLLeverandoerDAO database = new MySQLLeverandoerDAO();


	@Override
	public LeverandoerDTO getLeverandoer(int id, String token) throws Exception{
		if(isValid(token, 2)){
			return database.getLeverandoer(id);
		}
		return null;
	}

	@Override
	public LeverandoerDTO[] getLeverandoerList(String token) throws Exception{	
		if(isValid(token, 2)){
			List<LeverandoerDTO> leverandoerer = database.getLeverandoerList();
			LeverandoerDTO[] leverandoerArray = new LeverandoerDTO[leverandoerer.size()];
			return leverandoerer.toArray(leverandoerArray);
		}
		return null;			
	}

	@Override
	public LeverandoerDTO createLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true 
		&& FieldVerifier.isValidName(lev.getLeverandoerNavn())==true){
	
			if(isValid(token, 2)){
				database.createLeverandoer(lev);
				return lev;
				}
		else
		{
			Window.alert("Kunne ikke lave ny Leverandoer, tjek oplysningerne igen");
		}
			}
		return null;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(isValid(token, 2)){
			return database.updateLeverandoer(lev);
		}
		return 0;

	}

}
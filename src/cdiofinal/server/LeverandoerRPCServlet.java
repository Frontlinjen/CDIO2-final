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
	final String fail ="Kunne ikke %s leverand\u00F8ren, tjek informationerne igen.";


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
		if(lev.isValid()){
	
			if(isValid(token, 2)){
				database.createLeverandoer(lev);
				return lev;
				}
		else
		{
			throw new DALException(String.format(fail, "oprette"));
		}
			}
		return null;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(lev.isValid()){
			
			if(isValid(token, 2)){
				return database.updateLeverandoer(lev);
			}
		}
		else {
			throw new DALException(String.format(fail, "opdatere"));
		}
		return 0;

	}

}
package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.DALException;
import cdiofinal.client.LeverandoerRPCInterface;
import cdiofinal.server.MySQLLeverandoerDAO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.InsufficientAccessException;
import cdiofinal.shared.LeverandoerDTO;

public class LeverandoerRPCServlet extends RemoteServiceServlet implements LeverandoerRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLLeverandoerDAO database = new MySQLLeverandoerDAO();
	
	
	@Override
	public LeverandoerDTO getLeverandoer(int id, String token) throws Exception{
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new InsufficientAccessException("Invalid token. Please refresh the page and login again.");
			return database.getLeverandoer(id);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a leverandoer. Please contact your sysadmin.");
		}
	}

	@Override
	public LeverandoerDTO[] getLeverandoerList(String token) throws Exception{
					
					try {
						if(TokenHandler.getInstance().validateToken(token)==null)
							throw new DALException("Invalid token");
						List<LeverandoerDTO> leverandoerer = database.getLeverandoerList();
						LeverandoerDTO[] leverandoerArray = new LeverandoerDTO[leverandoerer.size()];
						return leverandoerer.toArray(leverandoerArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting leverandoer list. Please contact your sysadmin.");
					}			
	}

	@Override
	public LeverandoerDTO createLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true || FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
		try {
			if(database.createLeverandoer(lev)!=0){
				if(TokenHandler.getInstance().validateToken(token)==null)
					throw new DALException("Invalid token");
				return lev;
			}
		} catch (DALException e){
			throw new DALException("An error occoured when creating a leverandoer. Please contact your sysadmin.");
		}
		return null;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true || FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			return database.updateLeverandoer(lev);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a leverandoer. Please contact your sysadmin.");
			}
			return 0;
		
	}

}
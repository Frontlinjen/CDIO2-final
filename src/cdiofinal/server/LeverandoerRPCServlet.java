package cdiofinal.server;
import java.util.List;

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
		try {
			if(isValid(token, 2))
				return database.getLeverandoer(id);
		} catch (DALException e) {
			throw new DALException(gettingError("leverandoer"));
		}
		return null;
	}

	@Override
	public LeverandoerDTO[] getLeverandoerList(String token) throws Exception{	
		try {
			if(isValid(token, 2)){
				List<LeverandoerDTO> leverandoerer = database.getLeverandoerList();
				LeverandoerDTO[] leverandoerArray = new LeverandoerDTO[leverandoerer.size()];
				return leverandoerer.toArray(leverandoerArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("leverandoer"));
		}
		return null;			
	}

	@Override
	public LeverandoerDTO createLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true 
		&& FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
			try {
				if(isValid(token, 2)){
					if(database.createLeverandoer(lev)!=0){
						return lev;
					}
				}
			} catch (DALException e){
				throw new DALException(creatingError("leverandoer"));
			}
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev, String token) throws Exception{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true || FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
			try {
				if(isValid(token, 2))
					return database.updateLeverandoer(lev);
			} catch (DALException e){
				if(database.getLeverandoer(lev.getLeverandoerId()).getLeverandoerId() == lev.getLeverandoerId())
				{
					throw new DALException("Leverandoer Id'et eksisterer allerede");
				}
				else
				throw new DALException(updatingError("leverandoer"));
			}
		return null;

	}

}
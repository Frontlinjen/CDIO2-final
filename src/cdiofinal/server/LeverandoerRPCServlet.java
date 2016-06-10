package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.DALException;
import cdiofinal.client.LeverandoerRPCInterface;
import cdiofinal.server.MySQLLeverandoerDAO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;

public class LeverandoerRPCServlet extends RemoteServiceServlet implements LeverandoerRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLLeverandoerDAO database = new MySQLLeverandoerDAO();
	
	
	@Override
	public LeverandoerDTO getLeverandoer(int id) throws DALException{
		try {
			return database.getLeverandoer(id);
		} catch (DALException e) {
			throw e;
		}
	}

	@Override
	public LeverandoerDTO[] getLeverandoerList() {
					
					try {
						List<LeverandoerDTO> leverandoerer = database.getLeverandoerList();
						LeverandoerDTO[] leverandoerArray = new LeverandoerDTO[leverandoerer.size()];
						return leverandoerer.toArray(leverandoerArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createLeverandoer(LeverandoerDTO lev) throws DALException{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true || FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
		try {
		return database.createLeverandoer(lev);
		} catch (DALException e){
			throw e;
		}
		return 0;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev) throws DALException{
		if(FieldVerifier.isValidId(lev.getLeverandoerId())==true || FieldVerifier.isValidName(lev.getLeverandoerNavn())==true)
		try {
			return database.updateLeverandoer(lev);
			} catch (DALException e){
				throw e;
			}
			return 0;
		
	}
	
	public static void main(String[] args) {
		LeverandoerRPCServlet servlet = new LeverandoerRPCServlet();
		for (LeverandoerDTO string : servlet.getLeverandoerList()) {
			System.out.println(string);
		}
		
    }
	
}
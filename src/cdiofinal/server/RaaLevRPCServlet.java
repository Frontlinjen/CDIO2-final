package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RaaLevRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLRaavareDAO;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.RaavareDTO;

public class RaaLevRPCServlet extends RemoteServiceServlet implements RaaLevRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareDAO raaDAO = new MySQLRaavareDAO();
	MySQLLeverandoerDAO levDAO = new MySQLLeverandoerDAO();
	
	@Override
	public RaavareDTO getRaavare(int raavare_id) {
		try {
			return raaDAO.getRaavare(raavare_id);
		} catch (DALException e) {
			System.out.println("Failed at get Raavare");
		}
		return null;
	}

	@Override
	public RaavareDTO[] getRaavareList() {
					
					try {
						List<RaavareDTO> raavare = raaDAO.getRaavareList();
						RaavareDTO[] raavareArray = new RaavareDTO[raavare.size()];
						return raavare.toArray(raavareArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createRaavare(RaavareDTO ans) {
		try {
		return raaDAO.createRaavare(ans);
		} catch (DALException e){
			System.out.println("Failed at create Raavare");
		}
		return 0;
	}

	@Override
	public Integer updateRaavare(RaavareDTO ans) {
		try {
			return raaDAO.updateRaavare(ans);
			} catch (DALException e){
				System.out.println("Failed at update Raavare");
			}
			return 0;
		
	}

	@Override
	public Integer deleteRaavare(RaavareDTO ans) {
		try {
			return raaDAO.deleteRaavare(ans);
			} catch (DALException e){
				System.out.println("Failed at delete Raavare");
			}
			return 0;
		
	}
	
	@Override
	public LeverandoerDTO getLeverandoer(int id) {
		try {
			return levDAO.getLeverandoer(id);
		} catch (DALException e) {
			System.out.println("Failed at getLeverandoer");
		}
		return null;
	}

	@Override
	public LeverandoerDTO[] getLeverandoerList() {
					
					try {
						List<LeverandoerDTO> leverandoerer = levDAO.getLeverandoerList();
						LeverandoerDTO[] leverandoerArray = new LeverandoerDTO[leverandoerer.size()];
						return leverandoerer.toArray(leverandoerArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createLeverandoer(LeverandoerDTO lev) {
		try {
		return levDAO.createLeverandoer(lev);
		} catch (DALException e){
			System.out.println("Failed at createLeverandoer");
		}
		return 0;
	}

	@Override
	public Integer updateLeverandoer(LeverandoerDTO lev) {
		try {
			return levDAO.updateLeverandoer(lev);
			} catch (DALException e){
				System.out.println("Failed at updateLeverandoer");
			}
			return 0;
		
	}

	@Override
	public Integer deleteLeverandoer(LeverandoerDTO lev) {
		try {
			return levDAO.deleteLeverandoer(lev);
			} catch (DALException e){
				System.out.println("Failed at deleteLeverandoer");
			}
			return 0;

	}
}

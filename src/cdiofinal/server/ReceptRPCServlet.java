package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RecRPCInterface;
import cdiofinal.client.ReceptRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLReceptDAO;
import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

public class RecRPCServlet extends RemoteServiceServlet implements RecRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLReceptDAO receptDAO = new MySQLReceptDAO();
	MySQLReceptKompDAO receptKompDAO = new MySQLReceptKompDAO();
	
	
	@Override
	public ReceptDTO getRecept(int recept_id) {
		try {
			return receptDAO.getRecept(recept_id);
		} catch (DALException e) {
			System.out.println("Failed at getRecept");
		}
		return null;
	}

	@Override
	public ReceptDTO[] getReceptList() {
					
					try {
						List<ReceptDTO> recept = receptDAO.getReceptList();
						ReceptDTO[] receptArray = new ReceptDTO[recept.size()];
						return recept.toArray(receptArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createRecept(ReceptDTO ans) {
		try {
		return receptDAO.createRecept(ans);
		} catch (DALException e){
			System.out.println("Failed at create Recept");
		}
		return 0;
	}

	@Override
	public Integer updateRecept(ReceptDTO ans) {
		try {
			return receptDAO.updateRecept(ans);
			} catch (DALException e){
				System.out.println("Failed at update Recept");
			}
			return 0;
		
	}

	@Override
	public Integer deleteRecept(ReceptDTO ans) {
		try {
			return receptDAO.deleteRecept(ans);
			} catch (DALException e){
				System.out.println("Failed at delete Recept");
			}
			return 0;
		
	}

	@Override
	public ReceptKompDTO getReceptKomp(int recId, int raavareId) {
		try {
			return receptKompDAO.getReceptKomp(recId, raavareId);
		} catch (DALException e) {
			System.out.println("Failed at getReceptKomp");
		}
		return null;
	}

	@Override
	public ReceptKompDTO[] getReceptKompList() {
		try {
			List<ReceptKompDTO> receptkomp = receptKompDAO.getReceptKompList();
			ReceptKompDTO[] receptKompArray = new ReceptKompDTO[receptkomp.size()];
			return receptkomp.toArray(receptKompArray);
		} catch (DALException e) {
			return null;
		}	
	}

	@Override
	public Integer createReceptKomp(ReceptKompDTO recKomp) {
		try {
			return receptKompDAO.createReceptKomp(recKomp);
			} catch (DALException e){
				System.out.println("Failed at create ReceptKomp");
			}
			return 0;
	}

	@Override
	public Integer updateReceptKomp(ReceptKompDTO recKomp) {
		try {
			return receptKompDAO.updateReceptKomp(recKomp);
			} catch (DALException e){
				System.out.println("Failed at update ReceptKomp");
			}
			return 0;
	}

	@Override
	public Integer deleteReceptKomp(ReceptKompDTO recKomp) {
		try {
			return receptKompDAO.deleteReceptKomp(recKomp);
			} catch (DALException e){
				System.out.println("Failed at delete ReceptKomp");
			}
			return 0;
	}
	
	
}

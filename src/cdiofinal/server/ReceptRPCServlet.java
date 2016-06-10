package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RecRPCInterface;
import cdiofinal.client.ReceptRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLReceptDAO;
import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

public class ReceptRPCServlet extends RemoteServiceServlet implements ReceptRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLReceptDAO receptDAO = new MySQLReceptDAO();
	
	
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
}

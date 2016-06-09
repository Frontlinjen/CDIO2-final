package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RaavareBatchRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLRaavareBatchDAO;
import cdiofinal.shared.RaavareBatchDTO;

public class RaavareBatchRPCServlet extends RemoteServiceServlet implements RaavareBatchRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareBatchDAO database = new MySQLRaavareBatchDAO();
	
	
	@Override
	public RaavareBatchDTO getRaavareBatch(int rb_id) {
		try {
			return database.getRaavareBatch(rb_id);
		} catch (DALException e) {
			System.out.println("Failed at get RaavareBatch");
		}
		return null;
	}

	@Override
	public RaavareBatchDTO[] getRaavareBatchList() {
					
					try {
						List<RaavareBatchDTO> raavarebatches = database.getRaavarebatchList();
						RaavareBatchDTO[] raavarebatchesArray = new RaavareBatchDTO[raavarebatches.size()];
						return raavarebatches.toArray(raavarebatchesArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createRaavareBatch(RaavareBatchDTO ans) {
		try {
		return database.createRaavareBatch(ans);
		} catch (DALException e){
			System.out.println("Failed at create RaavareBatch");
		}
		return 0;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO ans) {
		try {
			return database.updateRaavareBatch(ans);
			} catch (DALException e){
				System.out.println("Failed at update RaavareBatch");
			}
			return 0;
		
	}

	public static void main(String[] args) {
		RaavareBatchRPCServlet servlet = new RaavareBatchRPCServlet();
		for (RaavareBatchDTO string : servlet.getRaavareBatchList()) {
			System.out.println(string);
		}
		
    }
	
}

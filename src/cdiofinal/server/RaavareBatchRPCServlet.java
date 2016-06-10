package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RaavareBatchRPCInterface;
import cdiofinal.server.MySQLRaavareBatchDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareBatchDTO;

public class RaavareBatchRPCServlet extends RemoteServiceServlet implements RaavareBatchRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareBatchDAO database = new MySQLRaavareBatchDAO();
	
	
	@Override
	public RaavareBatchDTO getRaavareBatch(int rb_id) throws DALException{
		try {
			return database.getRaavareBatch(rb_id);
		} catch (DALException e) {
			throw e;
		}
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
	public Integer createRaavareBatch(RaavareBatchDTO ans) throws DALException{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidId(ans.getLeverandoerId())==true || FieldVerifier.isValidId(ans.getRaavarebatchId()))
		try {
		return database.createRaavareBatch(ans);
		} catch (DALException e){
			throw e;
		}
		return 0;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO ans) throws DALException{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidId(ans.getLeverandoerId())==true || FieldVerifier.isValidId(ans.getRaavarebatchId()))
		try {
			return database.updateRaavareBatch(ans);
			} catch (DALException e){
				throw e;
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

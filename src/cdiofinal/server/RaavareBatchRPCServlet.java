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
	public RaavareBatchDTO getRaavareBatch(int rb_id, String token) throws DALException{
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			return database.getRaavareBatch(rb_id);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a raavarebatch. Please contact your sysadmin.");
		}
	}

	@Override
	public RaavareBatchDTO[] getRaavareBatchList(String token) throws DALException{
					
					try {
						if(TokenHandler.getInstance().validateToken(token)==null)
							throw new DALException("Invalid token");
						List<RaavareBatchDTO> raavarebatches = database.getRaavarebatchList();
						RaavareBatchDTO[] raavarebatchesArray = new RaavareBatchDTO[raavarebatches.size()];
						return raavarebatches.toArray(raavarebatchesArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting raavarebatch list. Please contact your sysadmin.");
					}			
	}

	@Override
	public RaavareBatchDTO createRaavareBatch(RaavareBatchDTO ans, String token) throws DALException{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidId(ans.getLeverandoerId())==true || FieldVerifier.isValidId(ans.getRaavarebatchId()))
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			if(database.createRaavareBatch(ans)!=0){
				return ans;
			}
		} catch (DALException e){
			throw new DALException("An error occoured when creating a raavarebatch. Please contact your sysadmin.");
		}
		return null;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO ans, String token) throws DALException{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidId(ans.getLeverandoerId())==true || FieldVerifier.isValidId(ans.getRaavarebatchId()))
		try {
			if(TokenHandler.getInstance().validateToken(token)==null)
				throw new DALException("Invalid token");
			return database.updateRaavareBatch(ans);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a raavarebatch. Please contact your sysadmin.");
			}
			return 0;
		
	}

}

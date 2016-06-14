package cdiofinal.server;
import java.util.List;

import cdiofinal.client.RaavareBatchRPCInterface;
import cdiofinal.server.MySQLRaavareBatchDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.RaavareBatchDTO;

public class RaavareBatchRPCServlet extends ValidationServlet implements RaavareBatchRPCInterface {

	private static final long serialVersionUID = 1L;
	RaavareBatchDAO raavareBatches = new MySQLRaavareBatchDAO();
	RaavareDAO raavare = new MySQLRaavareDAO();
	LeverandoerDAO leverandorer = new MySQLLeverandoerDAO();

	@Override
	public RaavareBatchDTO getRaavareBatch(int rb_id, String token) throws Exception{
		try {
			if(isValid(token, 1))
				return raavareBatches.getRaavareBatch(rb_id);
		} catch (DALException e) {
			throw new DALException(gettingError("raavare batch"));
		}
		return null;
	}

	@Override
	public RaavareBatchDTO[] getRaavareBatchList(String token) throws Exception{

		try {
			if(isValid(token, 1)){
				List<RaavareBatchDTO> raavarebatches = raavareBatches.getRaavarebatchList();
				RaavareBatchDTO[] raavarebatchesArray = new RaavareBatchDTO[raavarebatches.size()];
				return raavarebatches.toArray(raavarebatchesArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("raavare batch"));
		}
		return null;			
	}

	@Override
	public RaavareBatchDTO createRaavareBatch(RaavareBatchDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavarebatchId()) || FieldVerifier.isValidId(ans.getRaavareId())==true || FieldVerifier.isValidId(ans.getLeverandoerId())==true)
			try {
				if(isValid(token, 1)){
					if(raavareBatches.createRaavareBatch(ans)!=0){
						return ans;
					}
				}
			} catch (DALException e){
				throw new DALException(creatingError("raavare batch"));
			}
		return null;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true
				&& FieldVerifier.isValidId(ans.getLeverandoerId())==true 
				&& FieldVerifier.isValidId(ans.getRaavarebatchId())==true)
			try {
				if(isValid(token, 1))
					return raavareBatches.updateRaavareBatch(ans);
			} catch (DALException e){
				throw new DALException(updatingError("raavare batch"));
			}
		return 0;

	}

}

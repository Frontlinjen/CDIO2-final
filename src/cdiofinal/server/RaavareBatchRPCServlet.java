package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.client.Window;

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
			if(isValid(token, 1)){
				return raavareBatches.getRaavareBatch(rb_id);
			}
		return null;
	}

	@Override
	public RaavareBatchDTO[] getRaavareBatchList(String token) throws Exception{
			if(isValid(token, 1)){
				List<RaavareBatchDTO> raavarebatches = raavareBatches.getRaavarebatchList();
				RaavareBatchDTO[] raavarebatchesArray = new RaavareBatchDTO[raavarebatches.size()];
				return raavarebatches.toArray(raavarebatchesArray);
			}
		return null;			
	}

	@Override
	public RaavareBatchDTO createRaavareBatch(RaavareBatchDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavarebatchId()) 
		&& FieldVerifier.isValidId(ans.getRaavareId())==true
		&& FieldVerifier.isValidId(ans.getLeverandoerId())==true){
				if(isValid(token, 1)){
					raavareBatches.createRaavareBatch(ans);
					return ans;
					}
				}
		else
		{
			Window.alert("Kunne ikke oprette ny RaavareBatch, tjek oplysningerne igen");
		}
		return null;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO ans, String token) throws Exception{
		if(FieldVerifier.isValidId(ans.getRaavareId())==true
		&& FieldVerifier.isValidId(ans.getLeverandoerId())==true 
		&& FieldVerifier.isValidId(ans.getRaavarebatchId())==true)
		{
				if(isValid(token, 1)){
					return raavareBatches.updateRaavareBatch(ans);
				}
		}
		else
		{
			Window.alert("Kunne ikke opdatere RaavareBatchen, tjek oplysningerne igen.");
		}
		return 0;
}

}

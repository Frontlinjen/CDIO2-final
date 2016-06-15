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
	public RaavareBatchDTO createRaavareBatch(RaavareBatchDTO recB, String token) throws Exception{
		if(FieldVerifier.isValidId(recB.getRaavarebatchId()) 
		&& FieldVerifier.isValidId(recB.getRaavareId())==true
		&& FieldVerifier.isValidId(recB.getLeverandoerId())==true){
				if(isValid(token, 1)){
					raavareBatches.createRaavareBatch(recB);
					return recB;
					}
				}
		else
		{
			Window.alert("Kunne ikke oprette ny RaavareBatch, tjek oplysningerne igen");
		}
		return null;
	}

	@Override
	public Integer updateRaavareBatch(RaavareBatchDTO recB, String token) throws Exception{
		if(FieldVerifier.isValidId(recB.getRaavareId())==true
		&& FieldVerifier.isValidId(recB.getLeverandoerId())==true 
		&& FieldVerifier.isValidId(recB.getRaavarebatchId())==true)
		{
				if(isValid(token, 1)){
					return raavareBatches.updateRaavareBatch(recB);
				}
		}
		else
		{
			Window.alert("Kunne ikke opdatere RaavareBatchen, tjek oplysningerne igen.");
		}
		return 0;
}

}

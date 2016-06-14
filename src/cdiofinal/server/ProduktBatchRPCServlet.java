package cdiofinal.server;
import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.client.ProduktBatchRPCInterface;
import cdiofinal.server.MySQLProduktbatchDAO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ProduktBatchDTO;

public class ProduktBatchRPCServlet extends ValidationServlet implements ProduktBatchRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktbatchDAO database = new MySQLProduktbatchDAO();
	MySQLReceptDAO rdao = new MySQLReceptDAO();



	@Override
	public ProduktBatchDTO getProduktBatch(int pbid, String token) throws Exception{
			if(isValid(token, 1))
			{
				return database.getProduktBatch(pbid);
			}
		return null;
	}

	@Override
	public ProduktBatchDTO[] getProduktBatchList(String token) throws Exception{

			if(isValid(token, 1)){
				List<ProduktBatchDTO> produktbatches = database.getProduktBatchList();
				ProduktBatchDTO[] produktbatchesArray = new ProduktBatchDTO[produktbatches.size()];
				return produktbatches.toArray(produktbatchesArray);
			}	
			return null;			
	}

	@Override
	public ProduktBatchDTO createProduktBatch(ProduktBatchDTO prba, String token) throws Exception{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true 
		|| !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
			{
				if(isValid(token, 1)){
					database.createProduktBatch(prba);
					return prba;
					}
			}
		return null;
	}

	@Override
	public Integer updateProduktBatch(ProduktBatchDTO prba, String token) throws Exception{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true 
		|| !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
				if(isValid(token, 1)) {
					return database.updateProduktBatch(prba);
				}
		return 0;
	}
}
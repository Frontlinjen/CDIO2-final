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
		try {
			if(isValid(token, 1))
				return database.getProduktBatch(pbid);
		} catch (DALException e) {
			throw new DALException(gettingError("product batch"));
		}
		return null;
	}

	@Override
	public ProduktBatchDTO[] getProduktBatchList(String token) throws Exception{

		try {
			if(isValid(token, 1)){
				List<ProduktBatchDTO> produktbatches = database.getProduktBatchList();
				ProduktBatchDTO[] produktbatchesArray = new ProduktBatchDTO[produktbatches.size()];
				return produktbatches.toArray(produktbatchesArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("product batch"));
		}
		return null;			
	}

	@Override
	public ProduktBatchDTO createProduktBatch(ProduktBatchDTO prba, String token) throws Exception{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true 
		&& !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
			try {
				if(isValid(token, 1)){
					if(database.createProduktBatch(prba)!=0){
						return prba;
					}
				}
			} catch (DALException e){
				if (database.getProduktBatch(prba.getPbId()).getPbId()==prba.getPbId()) {
					throw new DALException("En produktbatch med dette ID eksisterer allerede");					
				}
				else if(database.getProduktBatch(prba.getPbId()).getReceptId()!=prba.getReceptId())
				{
					throw new DALException("Denne recept, eksisterer ikke i systemet");
				}
				else
				throw new DALException(creatingError("product batch"));
			}
		return null;

	}

	@Override
	public Integer updateProduktBatch(ProduktBatchDTO prba, String token) throws Exception{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true || !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
			try {
				if(isValid(token, 1))
					return database.updateProduktBatch(prba);
			} catch (DALException e){
				if (database.getProduktBatch(prba.getPbId()).getPbId()==prba.getPbId()) {
					throw new DALException("En produktbatch med dette ID eksisterer allerede");					
				}
				else if(database.getProduktBatch(prba.getPbId()).getReceptId()!=prba.getReceptId())
				{
					throw new DALException("Denne recept, eksisterer ikke i systemet");
				}
				else
				throw new DALException(deletingError("product batch"));
			}
		return null;

	}

}
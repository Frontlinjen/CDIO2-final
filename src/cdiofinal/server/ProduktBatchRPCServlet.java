package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.DALException;
import cdiofinal.client.ProduktBatchRPCInterface;
import cdiofinal.server.MySQLProduktbatchDAO;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ProduktBatchDTO;

public class ProduktBatchRPCServlet extends RemoteServiceServlet implements ProduktBatchRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktbatchDAO database = new MySQLProduktbatchDAO();
	MySQLReceptDAO rdao = new MySQLReceptDAO();



	@Override
	public ProduktBatchDTO getProduktBatch(int pbid) throws DALException{
		try {
			return database.getProduktBatch(pbid);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a produktbatch. Please contact your sysadmin.");
		}
	}

	@Override
	public ProduktBatchDTO[] getProduktBatchList() throws DALException{

		try {
			List<ProduktBatchDTO> produktbatches = database.getProduktBatchList();
			ProduktBatchDTO[] produktbatchesArray = new ProduktBatchDTO[produktbatches.size()];
			return produktbatches.toArray(produktbatchesArray);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting the produktbatch list. Please contact your sysadmin.");
		}			
	}

	@Override
	public Integer createProduktBatch(ProduktBatchDTO prba) throws DALException{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true || !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
			try {
				return database.createProduktBatch(prba);
			} catch (DALException e){
				throw new DALException("An error occoured when creating a produktbatch. Please contact your sysadmin.");
			}
		return 0;

	}

	@Override
	public Integer updateProduktBatch(ProduktBatchDTO prba) throws DALException{
		if(!FieldVerifier.isValidId((prba.getReceptId()))==true || !FieldVerifier.isValidId(prba.getPbId())==true){
			System.out.println("Id'et er ugyldigt. (1-99999999");
		}
		else
			try {
				return database.updateProduktBatch(prba);
			} catch (DALException e){
				throw new DALException("An error occoured when updating a produktbatch. Please contact your sysadmin.");
			}
		return 0;

	}

}
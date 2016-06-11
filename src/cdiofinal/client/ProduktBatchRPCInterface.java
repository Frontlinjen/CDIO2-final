package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatches")
public interface ProduktBatchRPCInterface extends RemoteService{
	
	ProduktBatchDTO getProduktBatch(int pb_id, String token) throws DALException;
	ProduktBatchDTO[] getProduktBatchList(String token) throws DALException;
	ProduktBatchDTO createProduktBatch(ProduktBatchDTO ans, String token) throws DALException;
	Integer updateProduktBatch(ProduktBatchDTO ans, String token) throws DALException;
}
